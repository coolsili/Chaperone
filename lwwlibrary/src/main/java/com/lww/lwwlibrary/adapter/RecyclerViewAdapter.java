package com.lww.lwwlibrary.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<BindingViewHolder> {
    private ArrayList<RecyclerItem> list = new ArrayList<RecyclerItem>();

    public RecyclerViewAdapter(ArrayList<RecyclerItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, viewType, parent, false);
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
        getItem(position).bind(holder.binding);
        holder.binding.executePendingBindings();
    }

    private RecyclerItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).layoutId;
    }

    public void updateData(ArrayList<RecyclerItem> items){
        list = items;
        notifyDataSetChanged();
    }
}

class BindingViewHolder extends RecyclerView.ViewHolder {
    public ViewDataBinding binding;

    public BindingViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public BindingViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}
