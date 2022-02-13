package com.me.jv.repository;

import android.annotation.SuppressLint;

import androidx.lifecycle.MutableLiveData;

import com.me.jv.model.NewsDetailResponse;
import com.me.jv.network.ApiService;
import com.me.jv.network.BaseObserver;
import com.me.jv.network.NetworkApi;

import javax.inject.Inject;

/**
 * 对新闻详情数据进行处理
 */
@SuppressLint("CheckResult")
public class WebRepository {

    final MutableLiveData<NewsDetailResponse> newsDetail = new MutableLiveData<>();

    public final MutableLiveData<String> failed = new MutableLiveData<>();

    @Inject
    WebRepository(){}

    /**
     * 获取新闻详情数据
     * @param uniquekey 新闻ID
     * @return newsDetail
     */
    public MutableLiveData<NewsDetailResponse> getNewsDetail(String uniquekey) {
        NetworkApi.createService(ApiService.class, 2).
            newsDetail(uniquekey).compose(NetworkApi.applySchedulers(new BaseObserver<NewsDetailResponse>() {
                        @Override
                            public void onSuccess(NewsDetailResponse newsDetailResponse) {
                            if (newsDetailResponse.getError_code() == 0) {
                                newsDetail.setValue(newsDetailResponse);
                            } else {
                                failed.postValue(newsDetailResponse.getReason());
                            }
                        }

                        @Override
                            public void onFailure(Throwable e) {
                            failed.postValue("NewsDetail Error: " + e.toString());
                        }
                    }));
        return newsDetail;
    }
}
