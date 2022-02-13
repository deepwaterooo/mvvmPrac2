package com.me.jv.ui.activities;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import com.google.android.material.appbar.AppBarLayout;
import com.me.jv.R;
import com.me.jv.databinding.ActivityMainBinding;
import com.me.jv.ui.adapters.WallPaperAdapter;
import com.me.jv.viewmodels.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {
    private final String TAG = "test MainActivity";
    
    private ActivityMainBinding dataBinding;
    
    private MainViewModel mainViewModel;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showLoading();

        //数据绑定视图
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        //必应网络请求
        mainViewModel.getBiying();
        //返回数据时更新ViewModel，ViewModel更新则xml更新
        mainViewModel.biying.observe(this, biYingImgResponse -> dataBinding.setViewModel(mainViewModel));

        initView();
        //热门壁纸 网络请求
        mainViewModel.getWallPaper();
        mainViewModel.wallPaper.observe(this, wallPaperResponse -> {
                dataBinding.rv.setAdapter(new WallPaperAdapter(wallPaperResponse.getRes().getVertical()));
                dismissLoading();
            });
        mainViewModel.failed.observe(this, failed -> dismissLoading());
    }

    /**
     * 初始化
     */
    private void initView() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        dataBinding.rv.setLayoutManager(manager);
        //伸缩偏移量监听
        dataBinding.appbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                boolean isShow = true;
                int scrollRange = -1;

                @Override
                    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if (scrollRange == -1) {
                        scrollRange = appBarLayout.getTotalScrollRange();
                    }
                    if (scrollRange + verticalOffset == 0) {//收缩时
                        dataBinding.toolbarLayout.setTitle("每日壁纸");
                        isShow = true;
                    } else if (isShow) {//展开时
                        dataBinding.toolbarLayout.setTitle("");
                        isShow = false;
                    }
                }
            });
        //页面上下滑动监听
        dataBinding.scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY > oldScrollY) {
                    //上滑
                    dataBinding.fabHome.hide();
                } else {
                    //下滑
                    dataBinding.fabHome.show();
                }
            });
    }

    public void toHome(View view) {
        jumpActivity(HomeActivity.class);
    }

    private long timeMillis;
    /**
     * Add a prompt to exit the application
     */
    @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - timeMillis) > 2000) {
                showMsg("再次按下退出应用程序");
                timeMillis = System.currentTimeMillis();
            } else {
                exitTheProgram();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
