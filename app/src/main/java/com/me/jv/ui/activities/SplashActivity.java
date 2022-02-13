package com.me.jv.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.me.jv.R;
import com.me.jv.databinding.ActivitySplashBinding;
import com.me.jv.utils.Constant;
import com.me.jv.utils.EasyAnimation;
import com.me.jv.utils.MVUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SplashActivity extends BaseActivity {

    @Inject
        MVUtils mvUtils;

    // private boolean first;
    // private String fstRun = "FIRST_RUN";
    
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_splash); // 使用mvvm databinding
        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
//        setStatusBar(true);
        EasyAnimation.moveViewWidth(binding.tvTranslate, () -> {
                binding.tvMvvm.setVisibility(View.VISIBLE);
                jumpActivityFinish(mvUtils.getBoolean(Constant.IS_LOGIN) ? MainActivity.class : LoginActivity.class);
            });
    }
        
        //     // 可以把http request等相关工作放在这里进行必要的优化处理:
        //     // 对于被观察者而言，只有当真正被订阅的时候，其数据的更新才有意义。换句话说，当开发者构建出一个LiveData<PagedList>时候，这时立即通过后台线程开始异步请求分页数据是没有意义的。
        //     // 反过来理解，若没有订阅就请求数据，当真正订阅的时候，DataSource中的数据已经过时了，这时还需要重新请求拉取最新数据，这样之前的一系列行为就没有意义了。
        //     // 真正的请求应该放在LiveData.observe()的时候，即被订阅时才去执行，笔者这里更偏向于称其为“懒加载”——如果读者对RxJava比较熟悉的话，会发现这和Observable.defer()操作符概念比较相似

        //     // 不是根据点击事件来的：根据LiveData<SharedPreference>来决定是否加载的
        //     // 现在是model的数据与view混在一起
        //     // Restore preferences
        //     SharedPreferences settings = getSharedPreferences("currency.ini", 0);
        //     first = settings.getBoolean(fstRun, true);
        //     if (first) { // 用户不曾配置数据
        //         // CreateDialog();
        //         new Handler().postDelayed(new Runnable() {
        //                 @Override
        //                 public void run() {
        //                     Intent i = new Intent(SplashActivity.this, DialogActivity.class);
        //                     startActivity(i);
        //                     //启动主Activity后销毁自身
        //                     finish();
        //                 }
        //             }, 1000);
        //     } else {
        //         new Handler().postDelayed(new Runnable() {
        //                 @Override
        //                 public void run() {
        //                     Intent i = new Intent(SplashActivity.this, DialogActivity.class);
        //                     startActivity(i);
        //                     //启动主Activity后销毁自身
        //                     finish();
        //                 }
        //             }, 3000);
        //     }
        //     // // 3s后，执行run方法启动主界面Activity
        //     // new Handler().postDelayed(new Runnable() {
        //     //     @Override
        //     //     public void run() {
        //     //         Intent i = new Intent(SplashActivity.this, MainActivity.class);
        //     //         startActivity(i);
        //     //         //启动主Activity后销毁自身
        //     //         finish();
        //     //     }
        //     // }, 1000);
        // }
    }