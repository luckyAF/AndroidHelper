package com.luckyaf.smartandroid.action;

import android.os.Bundle;
import android.os.Parcelable;
import androidx.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * 类描述：获取bundle内的数据
 *
 *
 * @author Created by luckyAF on 2021/6/29
 */
public interface BundleAction {

    /**
     *
     * @return 返回bundle
     */
    @Nullable
    Bundle getBundle();

    /**
     * 获取
     * @param name
     * @return
     */
    default int getExtrasInt(String name) {
        return getExtrasInt(name, 0);
    }

    default int getExtrasInt(String name, int defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getInt(name, defaultValue);
    }

    default long getExtrasLong(String name) {
        return getExtrasLong(name, 0);
    }

    default long getExtrasLong(String name, int defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getLong(name, defaultValue);
    }

    default float getExtrasFloat(String name) {
        return getExtrasFloat(name, 0);
    }

    default float getExtrasFloat(String name, int defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getFloat(name, defaultValue);
    }

    default double getExtrasDouble(String name) {
        return getExtrasDouble(name, 0);
    }

    default double getExtrasDouble(String name, int defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getDouble(name, defaultValue);
    }

    default boolean getExtrasBoolean(String name) {
        return getExtrasBoolean(name, false);
    }

    default boolean getExtrasBoolean(String name, boolean defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getBoolean(name, defaultValue);
    }

    default String getExtrasString(String name) {
        return getExtrasString(name,null);
    }
    default String getExtrasString(String name,String defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getString(name);
    }

    default <P extends Parcelable> P getExtrasParcelable(String name) {
        return getExtrasParcelable(name,null);
    }
    default <P extends Parcelable> P getExtrasParcelable(String name,P defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return bundle.getParcelable(name);
    }
    @SuppressWarnings("unchecked")
    default <S extends Serializable> S getExtrasSerializable(String name) {
        return getExtrasSerializable(name,null);
    }


    @SuppressWarnings("unchecked")
    default <S extends Serializable> S getExtrasSerializable(String name,S defaultValue) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return defaultValue;
        }
        return (S) (bundle.getSerializable(name));
    }

    default ArrayList<String> getExtrasStringArrayList(String name) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return null;
        }
        return bundle.getStringArrayList(name);
    }

    default ArrayList<Integer> getExtrasIntegerArrayList(String name) {
        Bundle bundle = getBundle();
        if (bundle == null) {
            return null;
        }
        return bundle.getIntegerArrayList(name);
    }
}