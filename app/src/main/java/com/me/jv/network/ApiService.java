package com.me.jv.network;

import com.me.jv.model.BiYingResponse;
import com.me.jv.model.BitcoinResponse;
import com.me.jv.model.DigitalCoinResponse;
import com.me.jv.model.NewsDetailResponse;
import com.me.jv.model.NewsResponse;
import com.me.jv.model.VideoResponse;
import com.me.jv.model.WallPaperResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /**
     * 必应每日一图
     */
    @GET("/HPImageArchive.aspx?format=js&idx=0&n=1")
    Observable<BiYingResponse> biying();

    /**
     * 热门壁纸
     */
    @GET("/v1/vertical/vertical?limit=30&skip=180&adult=false&first=0&order=hot")
    Observable<WallPaperResponse> wallPaper();

    /**
     * 聚合新闻数据
     */
    @GET("/toutiao/index?type=&page=&page_size=&is_filter=&key=99d3951ed32af2930afd9b38293a08a2")
    Observable<NewsResponse> news();

    /**
     * 聚合新闻数据详情
     */
    @GET("/toutiao/content?key=99d3951ed32af2930afd9b38293a08a2")
    Observable<NewsDetailResponse> newsDetail(@Query("uniquekey") String uniquekey);

    /**
     * 聚合热门视频数据
     */
    @GET("/fapig/douyin/billboard?type=hot_video&size=20&key=a9c49939cae34fc7dae570b1a4824be4")
    Observable<VideoResponse> video();

    
    @GET("v1/coins") // 1
    // Call<List<DigitalCoin>> digitalCoins();
    Observable<DigitalCoinResponse> digitalCoins();

    @GET("v1/coins") // 1: digitalCoins()
    Call<List<DigitalCoinResponse>> getDigitalCoinsList();

//    @GET("v1/bpi/currentprice.json") // 0
//    Call<Bitcoin> getBitcoinPrices();

    @GET("v1/bpi/currentprice.json") // 0
    Observable<BitcoinResponse> bitcoin();
}
