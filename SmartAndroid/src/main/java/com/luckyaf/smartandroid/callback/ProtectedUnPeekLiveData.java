package com.luckyaf.smartandroid.callback;


import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * TODO：UnPeekLiveData 的存在是为了在 "重回二级页面" 的场景下，解决 "数据倒灌" 的问题。
 * 对 "数据倒灌" 的状况不理解的小伙伴，可参考《jetpack MVVM 精讲》的解析
 * <p>
 * https://juejin.im/post/5dafc49b6fb9a04e17209922
 * <p>
 * 本类参考了官方 SingleEventLive 的非入侵设计，
 * <p>
 * TODO：并创新性地引入了 "延迟清空消息" 的设计，
 * 如此可确保：
 * 1.一条消息能被多个观察者消费
 * 2.延迟期结束，不再能够收到旧消息的推送
 * 3.并且旧消息在延迟期结束时能从内存中释放，避免内存溢出等问题
 * 4.让非入侵设计成为可能，遵循开闭原则
 * <p>
 * TODO：增加一层 ProtectedUnPeekLiveData，
 * 用于限制从 Activity/Fragment 推送数据，推送数据务必通过唯一可信源来分发，
 * 如果这样说还不理解，详见：
 * https://xiaozhuanlan.com/topic/6719328450 和 https://xiaozhuanlan.com/topic/0168753249
 * <p>
 * Create by KunMinX at 19/9/23
 */
public class ProtectedUnPeekLiveData<T> extends LiveData<T> {
    private static final int START_VERSION = -1;
    private final AtomicInteger mCurrentVersion = new AtomicInteger(-1);
    protected boolean isAllowNullValue;

    public ProtectedUnPeekLiveData() {
    }

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner, this.createObserverWrapper(observer, this.mCurrentVersion.get()));
    }
    @Override
    public void observeForever(@NonNull Observer<? super T> observer) {
        super.observeForever(this.createObserverWrapper(observer, this.mCurrentVersion.get()));
    }

    public void observeSticky(@NonNull LifecycleOwner owner, @NonNull Observer<T> observer) {
        super.observe(owner, this.createObserverWrapper(observer, -1));
    }

    public void observeStickyForever(@NonNull Observer<? super T> observer) {
        super.observeForever(this.createObserverWrapper(observer, -1));
    }

    @Override
    protected void setValue(T value) {
        this.mCurrentVersion.getAndIncrement();
        super.setValue(value);
    }

    @Override
    public void removeObserver(@NonNull Observer<? super T> observer) {
        if (observer.getClass().isAssignableFrom(ObserverWrapper.class)) {
            super.removeObserver(observer);
        } else {
            super.removeObserver(this.createObserverWrapper(observer, -1));
        }

    }

    private ObserverWrapper createObserverWrapper(@NonNull Observer<? super T> observer, int version) {
        return new ObserverWrapper(observer, version);
    }

    public void clear() {
        super.setValue((T)null);
    }

    class ObserverWrapper implements Observer<T> {
        private final Observer<? super T> mObserver;
        private int mVersion = -1;

        public ObserverWrapper(@NonNull Observer<? super T> observer, int version) {
            this.mObserver = observer;
            this.mVersion = version;
        }

        @Override
        public void onChanged(T t) {
            if((t == null && !ProtectedUnPeekLiveData.this.isAllowNullValue)){
                return;
            }
            if (ProtectedUnPeekLiveData.this.mCurrentVersion.get() > this.mVersion) {
                this.mObserver.onChanged(t);
            }

        }
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else if (o != null && this.getClass() == o.getClass()) {
                ObserverWrapper that = (ObserverWrapper)o;
                return Objects.equals(this.mObserver, that.mObserver);
            } else {
                return false;
            }
        }
        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.mObserver});
        }
    }
}
