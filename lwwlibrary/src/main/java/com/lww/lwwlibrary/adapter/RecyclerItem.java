package com.lww.lwwlibrary.adapter;

import androidx.annotation.LayoutRes;
import androidx.databinding.ViewDataBinding;

public class RecyclerItem<T> {
    public T data;
    @LayoutRes
    public int layoutId;
    public int variableId;


    public RecyclerItem(T data, int layoutId, int variableId) {
        this.data = data;
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    public void bind(ViewDataBinding binding){
        binding.setVariable(variableId, data);
    }
}
