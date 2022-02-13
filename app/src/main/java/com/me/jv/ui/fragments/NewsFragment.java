package com.me.jv.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.me.jv.R;
import com.me.jv.databinding.NewsFragmentBinding;
import com.me.jv.ui.adapters.NewsAdapter;
import com.me.jv.viewmodels.NewsViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 新闻
 */
@AndroidEntryPoint
public class NewsFragment extends BaseFragment {

    private NewsFragmentBinding binding;
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        return binding.getRoot();
    }

    @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        NewsViewModel mViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        //获取新闻数据
        mViewModel.getNews();
        binding.rv.setLayoutManager(new LinearLayoutManager(context));
        //数据刷新
        mViewModel.news.observe(context, newsResponse ->
                                binding.rv.setAdapter(new NewsAdapter(newsResponse.getResult().getData())));
        mViewModel.failed.observe(context, this::showMsg);
    }

}