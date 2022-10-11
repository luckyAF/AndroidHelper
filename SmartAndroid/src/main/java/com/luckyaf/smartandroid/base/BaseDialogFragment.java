package com.luckyaf.smartandroid.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.luckyaf.smartandroid.R;
import com.luckyaf.smartandroid.mvi.BaseModel;
import com.luckyaf.smartandroid.mvi.IntentView;
import com.luckyaf.smartandroid.ui.widget.dialog.DialogFrameLayout;
import com.luckyaf.smartandroid.ui.widget.dialog.DialogLayoutInflater;
import com.luckyaf.smartandroid.utils.BarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * 类描述： 基础dialogFragment
 * 用于某些数据比较复杂的页面 可用作通用数据页面，全屏显示
 *
 * @author Created by luckyAF on 2021/7/11
 */

/**
 * 类描述： 基础dialogFragment
 * 用于某些数据比较复杂的页面 可用作通用数据页面，全屏显示
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseDialogFragment extends DialogFragment {

    public final String TAG = getClass().getSimpleName();
    private static final float DEFAULT_DIM_AMOUNT = 0.2F;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

    protected Bundle savedBundle;
    protected View mContentView;
    public Boolean dialogIsShowing = true;


    public static <T extends BaseDialogFragment> T newInstance(Class clazz, Bundle args) {
        T fragment = null;
        try {
            fragment = (T) clazz.newInstance();
            fragment.setArguments(args);
        } catch (java.lang.InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }


    /**
     * 获取布局id
     *
     * @return id
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化数据
     *
     * @param bundle 数据
     */
    public abstract void initData(@NonNull Bundle bundle);

    /**
     * 初始化UI
     *
     * @param savedInstanceState 数据
     * @param contentView        布局
     */
    public abstract void initView(@NonNull Bundle savedInstanceState, @NonNull View contentView);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //initStyle();
        mContentView = super.onCreateView(inflater, container, savedInstanceState);
        if (null == mContentView && getLayoutRes() != 0) {
            mContentView = inflater.inflate(getLayoutRes(), null);
        }
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(savedBundle, mContentView);

    }


    private void setStyle() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(isCancelableOutside());
            if (dialog.getWindow() != null && getDialogAnimationRes() > 0) {
                dialog.getWindow().setWindowAnimations(getDialogAnimationRes());
            }
            if (getOnKeyListener() != null) {
                dialog.setOnKeyListener(getOnKeyListener());
            }
            Window window = getDialog().getWindow();
            if (window != null) {
                //设置窗体背景色透明
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //设置宽高
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                if (getDialogWidth() > 0) {
                    layoutParams.width = getDialogWidth();
                } else {
                    layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
                }
                if (getDialogHeight() > 0) {
                    layoutParams.height = getDialogHeight();
                } else {
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                }
                //透明度
                layoutParams.dimAmount = getDimAmount();
                //位置
                layoutParams.gravity = getGravity();
                window.setAttributes(layoutParams);
            }
        }
    }

    protected DialogInterface.OnKeyListener getOnKeyListener() {
        return null;
    }


    public void closeFromRight() {
        if (dialogIsShowing) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.anim.base_left_in_window, R.anim.base_right_out_window)
                    .hide(this).commitNow();
            dismiss();
        }
    }

    public void closeFromBottom() {
        if (dialogIsShowing) {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction
                    .setCustomAnimations(R.anim.base_bottom_in_window, R.anim.base_bottom_out_window)
                    .hide(this).commitNow();
            dismiss();
        }

    }


    //默认弹窗位置为中心
    public int getGravity() {
        return Gravity.CENTER;
    }

    //默认宽高为包裹内容
    public int getDialogHeight() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    public int getDialogWidth() {
        return WindowManager.LayoutParams.WRAP_CONTENT;
    }

    /**
     * 默认透明度为0.2
     *
     * @return 透明度
     */
    public float getDimAmount() {
        return DEFAULT_DIM_AMOUNT;
    }

    public String getFragmentTag() {
        return TAG;
    }


    protected boolean isCancelableOutside() {
        return false;
    }

    protected boolean canBack() {
        return false;
    }

    protected boolean fullScreen() {
        return false;
    }

    public void showDialogFragment(FragmentActivity activity) {
        showDialogFragment(activity.getSupportFragmentManager(), TAG);
    }

    public void showDialogFragment(Fragment fragment) {
        showDialogFragment(fragment.getChildFragmentManager(), TAG);

    }

    public void showDialogFragment(FragmentManager fragmentManager) {
        showDialogFragment(fragmentManager, getFragmentTag());
    }

    public void showDialogFragment(FragmentManager fragmentManager, String tag) {
        if (this.isAdded()) {
            getDialog().show();
        } else {
            show(fragmentManager, tag);
        }
        dialogIsShowing = true;
    }

    @NonNull
    @Override
    public LayoutInflater onGetLayoutInflater(@Nullable Bundle savedInstanceState) {
        //设置窗口以对话框样式显示
        if (fullScreen()) {
            setStyle(DialogFragment.STYLE_NO_TITLE, getStyle());
        } else {
            setStyle(DialogFragment.STYLE_NORMAL, getStyle());
        }
        super.onGetLayoutInflater(savedInstanceState);
        // 换成 Activity 的 inflater, 解决 fragment 样式 bug
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        //if (!getDialog().getWindow().isFloating()) {
        initStyle();
        layoutInflater = new DialogLayoutInflater(requireContext(), layoutInflater,
                new DialogFrameLayout.OnTouchOutsideListener() {
                    @Override
                    public void onTouchOutside() {
                        if (isCancelable()) {
                            dismiss();
                        }
                    }
                });
        return layoutInflater;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savedBundle = getArguments();
        if (savedBundle == null) {
            savedBundle = new Bundle();
        }
        initData(savedBundle);
    }

    @Override
    public void onStart() {
        super.onStart();
        // 全屏显示Dialog，重新测绘宽高
//        if (getDialog() != null) {
//            //获取Window
//            Window window = getDialog().getWindow();
//            //window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            //设置宽高
//            window.getDecorView().setPadding(0, 0, 0, 0);
//            WindowManager.LayoutParams wlp = window.getAttributes();
//            wlp.dimAmount = getDimAmount();
//            wlp.width = getDialogWidth();
//            wlp.height = getDialogHeight();
//            //设置对齐方式
//            wlp.gravity = getGravity();
//            //设置动画
//            window.setWindowAnimations(getDialogAnimationRes());
//            window.setAttributes(wlp);
//        }
    }


    protected void initStyle() {
        //设置对话框背景色，否则有虚框
        if (fullScreen()) {
            Window window = getDialog().getWindow();
            //window.requestFeature(Window.FEATURE_NO_TITLE);
            //设置背景为透明
            BarUtil.setTransparentForWindow(window);
            window.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), android.R.color.transparent));
            int dialogHeight = getContextRect(getActivity());
            //设置弹窗大小为 全屏
            window.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    dialogHeight == 0 ? ViewGroup.LayoutParams.MATCH_PARENT : dialogHeight
            );
            //去除阴影
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            DisplayMetrics dm = new DisplayMetrics();
            layoutParams.dimAmount = 0.0f;
            layoutParams.gravity = Gravity.BOTTOM;

//            layoutParams.gravity =Gravity.CENTER;
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setAttributes(layoutParams);
            //Watermark.getInstance().showAtView(mContext,container);
        } else {
            //获取Window
            Window window = getDialog().getWindow();
            //无标题
            // getDialog().requestWindowFeature(STYLE_NO_TITLE);
            //设置宽高
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.dimAmount = getDimAmount();
            wlp.width = getDialogWidth();
            wlp.height = getDialogHeight();
            //设置对齐方式
            wlp.gravity = getGravity();
            //设置偏移量
//            wlp.x = DensityUtil.dip2px(getDialog().getContext(), mOffsetX);
//            wlp.y = DensityUtil.dip2px(getDialog().getContext(), mOffsetY);
            //设置动画
            window.setWindowAnimations(getDialogAnimationRes());
            window.setAttributes(wlp);
        }
        try {
            BarUtil.setTransparentForWindow(getDialog().getWindow());
            //无标题
            getDialog().requestWindowFeature(STYLE_NO_TITLE);
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            if (getDialogAnimationRes() > 0) {
                getDialog().getWindow().setWindowAnimations(getDialogAnimationRes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getDialog().setCancelable(isCancelable());
        getDialog().setCanceledOnTouchOutside(isCancelableOutside());
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (canBack()) {
                        onCancel();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //获取内容区域
    protected int getContextRect(FragmentActivity activity) {
        Rect outRect1 = new Rect();

        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(outRect1);

        Log.d("getContextRect", "outRect1 height=" + outRect1.height());
        Log.d("getContextRect", "outRect1 top=" + outRect1.top);
        Log.d("getContextRect", "outRect1 bottom=" + outRect1.bottom);
        Rect outRect2 = new Rect();

        activity.getWindow().getDecorView().getDisplay().getRectSize(outRect2);
        Log.d("getContextRect", "getDisplay height=" + outRect2.height());
        Log.d("getContextRect", "getDisplay top=" + outRect2.top);
        Log.d("getContextRect", "getDisplay bottom=" + outRect2.bottom);

        Log.d("getContextRect", "getDecorView getBottom=" + activity.getWindow().getDecorView().getBottom());

        return outRect2.height();
        //return outRect1.height();
    }


    /**
     * 当对话框消失时的监听事件
     */
    protected void onCancel() {
        closeSelf();
    }

    protected void closeSelf() {
        if(dialogIsShowing){
            //getFragmentManager().beginTransaction().hide(this).commit();
            dialogIsShowing = false;
            dismissAllowingStateLoss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        dialogIsShowing = false;
    }


    /**
     * 设置弹框的样式
     **/
    public int getStyle() {
        return R.style.BaseDialogTheme;
    }


    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        T model = mFragmentProvider.get(modelClass);
        if (model instanceof BaseModel && this instanceof IntentView) {
            ((BaseModel<?, ?>) model).bindView((IntentView) this);
        }
        return model;
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(requireActivity());
        }
        T model = mActivityProvider.get(modelClass);
        if (model instanceof BaseModel && this instanceof IntentView) {
            ((BaseModel<?, ?>) model).bindView((IntentView) this);
        }
        return model;
    }


    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((BaseApplication) requireActivity().getApplicationContext());
        }
        return mApplicationProvider.get(modelClass);
    }


    /**
     * 获取弹窗显示动画,子类实现
     *
     * @return 动画
     */
    public int getDialogAnimationRes() {
        return 0;
    }

    //获取设备屏幕宽度
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    //获取设备屏幕高度
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    protected void showSoftInput(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText, 0);
    }

    protected void hideSoftInput(EditText editText) {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);

    }
}
