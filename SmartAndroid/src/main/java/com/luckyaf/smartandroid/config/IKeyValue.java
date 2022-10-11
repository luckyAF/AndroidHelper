package com.luckyaf.smartandroid.config;

import androidx.annotation.NonNull;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/12/6
 */
public interface IKeyValue {

    /**
     *
     * @param key
     * @param value
     */
    void put(@NonNull String key, Object value);

    /**
     *
     * @param key  key
     * @param defaultValue value
     * @param <V>
     * @return
     */
    @NonNull
    <V>  V get(@NonNull String key,@NonNull V defaultValue);


    void remove(@NonNull String key);

    /**
     * 清除缓存中所有的内容
     */
    void clear();
    /**
     * 关闭
     */
    void close();

}
