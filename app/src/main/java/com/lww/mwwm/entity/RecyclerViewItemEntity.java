package com.lww.mwwm.entity;

import com.lww.lwwlibrary.adapter.RecyclerItem;
import com.lww.mwwm.R;

public class RecyclerViewItemEntity {
    public String itemName;
    public String itemContent;

    public RecyclerViewItemEntity(String itemName,String itemContent){
        this.itemName = itemName;
        this.itemContent= itemContent;
    }

    public RecyclerViewItemEntity() {
    }

    public static RecyclerItem<RecyclerViewItemEntity> toRecyclerItem (RecyclerViewItemEntity entity){
        return new RecyclerItem<>(entity,R.layout.item_recycler_view, com.lww.mwwm.BR.user);
    }
}
