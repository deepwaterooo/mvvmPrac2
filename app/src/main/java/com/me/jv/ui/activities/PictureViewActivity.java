package com.me.jv.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.me.jv.R;
import com.me.jv.databinding.ActivityPictureViewBinding;
import com.me.jv.ui.adapters.ImageAdapter;
import com.me.jv.viewmodels.PictureViewModel;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * 图片查看
 */
@AndroidEntryPoint
public class PictureViewActivity extends AppCompatActivity {

    private PictureViewModel viewModel;
    private ActivityPictureViewBinding binding;

    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_picture_view);

        viewModel = new ViewModelProvider(this).get(PictureViewModel.class);
        String img = getIntent().getStringExtra("img");

        //获取热门壁纸数据
        viewModel.getWallPaper();
        viewModel.wallPaper.observe(this, wallPapers -> {
                binding.vp.setAdapter(new ImageAdapter(wallPapers));
                for (int i = 0; i < wallPapers.size(); i++) {
                    if (img == null) {
                        return;
                    }
                    if (wallPapers.get(i).getImg().equals(img)) {
                        binding.vp.setCurrentItem(i,false);
                        break;
                    }
                }
            });
    }
}