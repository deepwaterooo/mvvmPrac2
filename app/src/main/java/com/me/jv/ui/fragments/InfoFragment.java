package com.me.jv.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.me.jv.R;
import com.me.jv.databinding.InfoFragmentBinding;
import com.me.jv.ui.adapters.InfoFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 资讯  包含新闻、视频
 * @author llw
 */
@AndroidEntryPoint
public class InfoFragment extends BaseFragment {

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }
    private InfoFragmentBinding binding;

    /**
     * 标题数组
     */
    private final String[] titles = {"新闻","视频"};
    private final List<Fragment> fragmentList = new ArrayList<>();

    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.info_fragment,container,false);
        return binding.getRoot();
    }

    @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fragmentList.add(new NewsFragment());
        fragmentList.add(new VideoFragment());
        binding.vp.setAdapter(new InfoFragmentAdapter(getChildFragmentManager(), fragmentList, titles));
        binding.tab.setupWithViewPager(binding.vp);
    }
}