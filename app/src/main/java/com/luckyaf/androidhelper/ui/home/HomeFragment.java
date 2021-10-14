package com.luckyaf.androidhelper.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.luckyaf.androidhelper.BR;
import com.luckyaf.androidhelper.R;
import com.luckyaf.androidhelper.databinding.FragmentHomeBinding;
import com.luckyaf.smartandroid.mvvm.DataBindingConfig;
import com.luckyaf.smartandroid.mvvm.DataBindingFragment;

/**
 * @author xiangzhongfei
 */
public class HomeFragment extends DataBindingFragment {

    private HomeViewModel homeViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.fragment_home, BR.vm,homeViewModel)
                .addBindingParam(BR.click,new ClickProxy())
                ;
    }

    @Override
    public void initView() {

    }

    public class ClickProxy{
        public void singleClick(){
            Toast.makeText(mContext,"单击",Toast.LENGTH_SHORT).show();

        }
        public void multipleClick(){
            Toast.makeText(mContext,"3击",Toast.LENGTH_SHORT).show();
        }
    }
}