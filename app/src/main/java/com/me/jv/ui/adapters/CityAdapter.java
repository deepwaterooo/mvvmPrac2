package com.me.jv.ui.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.me.jv.R;
import com.me.jv.databinding.ItemCityBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 城市适配器
 */
public class CityAdapter extends BaseQuickAdapter<String, BaseDataBindingHolder<ItemCityBinding>> {

    public CityAdapter(@Nullable List<String> data) {
        super(R.layout.item_city, data);
    }

    @Override
        protected void convert(@NotNull BaseDataBindingHolder<ItemCityBinding> bindingHolder, String s) {
        ItemCityBinding binding = bindingHolder.getDataBinding();
        if (binding != null) {
            binding.setCityName(s);
            binding.executePendingBindings();
        }
    }
}
