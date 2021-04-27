package com.lww.mwwm.model;

import android.util.Log;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.lww.lwwlibrary.adapter.RecyclerItem;
import com.lww.lwwlibrary.adapter.RecyclerViewAdapter;
import com.lww.mwwm.entity.RecyclerViewItemEntity;

import java.util.ArrayList;

public class RecyclerViewListViewModel extends ViewModel {
    public MutableLiveData<ArrayList<RecyclerItem>> data = new MutableLiveData<>();

    public RecyclerViewListViewModel() {
        loadData();
    }

    /**
     * 获取recyclerView的数据
     */
    public void loadData(){
        RecyclerItem<RecyclerViewItemEntity> listItem =  RecyclerViewItemEntity.toRecyclerItem(new RecyclerViewItemEntity("名称1","内容1"));
        ArrayList<RecyclerItem> lis = new ArrayList<>();
        lis.add(listItem);
        data.setValue(lis);

    }

    public void addItem(){
//        ListAdapter
        RecyclerItem<RecyclerViewItemEntity> listItem =  RecyclerViewItemEntity.toRecyclerItem(new RecyclerViewItemEntity("名称1","内容1"));
        RecyclerItem<RecyclerViewItemEntity> listItem2 =  RecyclerViewItemEntity.toRecyclerItem(new RecyclerViewItemEntity("名称1","内容1"));

        ArrayList<RecyclerItem> lis = new ArrayList<>();
        lis.add(listItem);
        lis.add(listItem2);
        data.getValue().addAll(lis);
        data.setValue(data.getValue());
    }

    @BindingAdapter("items")
    public static void setRecyclerViewItems(RecyclerView recyclerView, ArrayList<RecyclerItem> items){
        RecyclerViewAdapter adapter = (RecyclerViewAdapter) recyclerView.getAdapter();
        if (adapter == null) {
            if(items==null){
                Log.e("TAG","RecyclerVieewAdapter: items is null");
                items = new ArrayList<>();
            }
            adapter = new RecyclerViewAdapter(items);
            recyclerView.setAdapter(adapter);
        }else{
            adapter.updateData(items);
        }
    }

}
