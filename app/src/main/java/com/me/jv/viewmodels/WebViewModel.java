package com.me.jv.viewmodels;


import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;

import com.me.jv.model.NewsDetailResponse;
import com.me.jv.repository.WebRepository;

/**
 * NewsFragment数据提供
 * @author llw
 */
public class WebViewModel extends BaseViewModel {

    private final WebRepository webRepository;
    public LiveData<NewsDetailResponse> newsDetail;

    @ViewModelInject
        WebViewModel(WebRepository webRepository){
        this.webRepository = webRepository;
    }

    public void getNewDetail(String uniquekey) {
        failed = webRepository.failed;
        newsDetail = webRepository.getNewsDetail(uniquekey);
    }
}