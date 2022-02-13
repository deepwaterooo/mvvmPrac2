package com.me.jv.ui.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.me.jv.R;
import com.me.jv.databinding.ItemNewsBinding;
import com.me.jv.model.NewsResponse;
import com.me.jv.ui.activities.WebActivity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 新闻列表适配器
 */
public class NewsAdapter extends BaseQuickAdapter<NewsResponse.ResultBean.DataBean, BaseDataBindingHolder<ItemNewsBinding>> {

    public NewsAdapter(@Nullable List<NewsResponse.ResultBean.DataBean> data) {
        super(R.layout.item_news, data);
    }

    @Override protected void convert(@NotNull BaseDataBindingHolder<ItemNewsBinding> bindingHolder, NewsResponse.ResultBean.DataBean dataBean) {
        ItemNewsBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.setNews(dataBean);
            binding.setOnClick(new ClickBinding());
            binding.executePendingBindings();
        }
    }

    public static class ClickBinding {
        public void itemClick(NewsResponse.ResultBean.DataBean dataBean, View view) {
            if("1".equals(dataBean.getIs_content())){
                Intent intent = new Intent(view.getContext(), WebActivity.class);
                intent.putExtra("uniquekey", dataBean.getUniquekey());
                view.getContext().startActivity(intent);
            } else {
                Toast.makeText(view.getContext(), "没有详情信息", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
