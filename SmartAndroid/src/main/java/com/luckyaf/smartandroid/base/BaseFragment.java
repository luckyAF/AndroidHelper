package com.luckyaf.smartandroid.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.luckyaf.smartandroid.R;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/7/11
 */
@SuppressWarnings("unused")
public abstract class BaseFragment extends Fragment {
    protected String tag = this.getClass().getName();
    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";
    private static final String KEY_SAVE_BUNDLE = "key_fragment_save_bundle";
    private Bundle savedBundle;

    protected Context mContext;
    protected AppCompatActivity mActivity;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (AppCompatActivity) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            savedBundle = savedInstanceState.getBundle(KEY_SAVE_BUNDLE);
            FragmentManager manager = getParentFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            if (isSupportHidden) {
                transaction.hide(this).commitAllowingStateLoss();
            } else {
                transaction.show(this).commitAllowingStateLoss();
            }
        }
    }


    public void initData(Bundle bundle){
    }

    /**
     * 初始化View
     */
    public void initView(){

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        savedBundle = getArguments();
        if (savedBundle == null) {
            savedBundle = new Bundle();
        }
        initData(savedBundle);
        initView();
    }




    public void closeFromRight(){
        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.base_left_in_window, R.anim.base_right_out_window)
                .hide(this).commit();
    }
    public void closeFromBottom(){
        FragmentManager fragmentManager= getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .setCustomAnimations(R.anim.base_bottom_in_window, R.anim.base_bottom_out_window)
                .hide(this).commit();
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBundle(KEY_SAVE_BUNDLE, this.savedBundle);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
        super.onSaveInstanceState(outState);

    }



}
