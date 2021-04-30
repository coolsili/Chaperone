package com.lww.mwwm.model;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
import androidx.databinding.adapters.ListenerUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.lww.lwwlibrary.adapter.RecyclerItem;
import com.lww.lwwlibrary.adapter.RecyclerViewAdapter;
import com.lww.lwwlibrary.entity.BaseEntity;
import com.lww.lwwlibrary.retrofit.ApiService;
import com.lww.lwwlibrary.retrofit.BaseObserver;
import com.lww.lwwlibrary.retrofit.InterceptorHelper;
import com.lww.lwwlibrary.retrofit.ObservableManager;
import com.lww.lwwlibrary.retrofit.ParamsBuilder;
import com.lww.lwwlibrary.retrofit.RetrofitHandler;
import com.lww.lwwlibrary.retrofit.RxTransformerHelper;
import com.lww.lwwlibrary.retrofit.entity.BaseResponseEntity;
import com.lww.mwwm.R;
import com.lww.mwwm.activity.HomeActivity;
import com.lww.mwwm.entity.RecyclerViewItemEntity;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

//@InverseBindingMethods({@InverseBindingMethod(type=androidx.swiperefreshlayout.widget.SwipeRefreshLayout.class,attribute = "refresheds",event = "bind_swipeRefreshLayout_refreshingAttrChanged",method = "isRefreshing")})
public class RecyclerViewListViewModel extends ViewModel{
    public MutableLiveData<ArrayList<RecyclerItem>> data = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRefreshing  = new MutableLiveData<>(false);
    public int requestNum = 0;

    public RecyclerViewListViewModel() {
        loadData();
        InterceptorHelper.isLog = true;
    }

    /**
     * 获取recyclerView的数据
     */
    public void loadData(){
        isRefreshing.setValue(true);
        requestNum++;
        Log.e("TAG","requestNum: "+requestNum);
        RetrofitHandler.getInstance().getAPIService(ApiService.class)
                .login(ObservableManager.getInstance().getRequestBody(ParamsBuilder.getIntance()
                        .addParams("userName","18511284125")
                        .addParams("password","sinomis2028")
                        .addParams("type","0").get()))
                .compose(RxTransformerHelper.<BaseResponseEntity<BaseEntity>>observableIO2Main())
                .subscribe(new BaseObserver<BaseEntity>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<BaseEntity> tBaseEntity) {
                        isRefreshing.setValue(false);
                        RecyclerItem<RecyclerViewItemEntity> listItem =  RecyclerViewItemEntity.toRecyclerItem(new RecyclerViewItemEntity("名称1","内容1"));
                        ArrayList<RecyclerItem> lis = new ArrayList<>();
                        lis.add(listItem);
                        data.setValue(lis);
                    }

                    @Override
                    protected void onFailure(String errorMessage) {
                        Log.e("TAG", "onFailure time:" + errorMessage);
                        Log.e("TAG","requestNum: "+requestNum);
                        isRefreshing.setValue(false);
                        RecyclerItem<RecyclerViewItemEntity> listItem =  RecyclerViewItemEntity.toRecyclerItem(new RecyclerViewItemEntity("名称1","内容1"));
                        ArrayList<RecyclerItem> lis = new ArrayList<>();
                        lis.add(listItem);
                        data.setValue(lis);
                    }
                });
    }



    /**
     * 改变isRefreshing的值
     */

    public void chageRefreshStatus(){
        loadData();
    }
    /**
     * 加载更多
     */
    public void loadMore(){

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
    @BindingAdapter(value = {"refreshListener","loadMoreListener"},requireAll = false)
    public static void setRefresh(SmartRefreshLayout refreshLayout,OnRefreshListener refreshListener,OnLoadMoreListener loadMoreListener){
        refreshLayout.setRefreshHeader(new ClassicsHeader(refreshLayout.getContext()));
        refreshLayout.setRefreshFooter(new ClassicsFooter(refreshLayout.getContext()));
        refreshLayout.setOnRefreshListener(refreshListener);
        refreshLayout.setOnLoadMoreListener(loadMoreListener);
    }
    public OnRefreshListener getOnRefreshListener(){
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG","RefreshHeader  onRefresh");
                loadData();
                refreshLayout.finishRefresh(5000);
                Log.e("TAG","RefreshHeader  onRefresh2");
            }
        };
    }
    public OnLoadMoreListener getOnRefreshLoadMoreListener(){
        return new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Log.e("TAG","RefreshFooter  onLoadMore");
                loadData();
                refreshLayout.finishLoadMore();
            }
        };
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

    @BindingAdapter(value = "refreshing")
    public static void setRefreshing(SwipeRefreshLayout swipeRefreshLayout,Boolean isRefreshing){
        if(swipeRefreshLayout.isRefreshing()!=isRefreshing){
            swipeRefreshLayout.setRefreshing(isRefreshing);
        }
    }

    /**
     * attribute 为xml中的属性名  event 为设置属性监听的属性名，类型是InverseBindingListener。
     * @param view
     * @return
     */
    @InverseBindingAdapter(attribute = "refreshing",event = "refreshingAttrChanged")
    public static boolean isRefreshing(SwipeRefreshLayout view) {
        return view.isRefreshing();
    }

    @BindingAdapter(value = { "onRefreshListener","refreshingAttrChanged"},requireAll = false)
    public static void setOnRefreshListener(final SwipeRefreshLayout swipeRefreshLayout, final SwipeRefreshLayout.OnRefreshListener listener,final InverseBindingListener bindingListener) {
            SwipeRefreshLayout.OnRefreshListener newValue = new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if (listener != null) {
                        listener.onRefresh();
                    }
                    if (bindingListener != null) {
                        bindingListener.onChange();
                    }
                }
            };
//            SwipeRefreshLayout.OnRefreshListener oldValue = ListenerUtil.trackListener(swipeRefreshLayout, newValue, R.id.onRefresh);
//            if (oldValue != null) {
//                swipeRefreshLayout.setOnRefreshListener(null);
//            }
            swipeRefreshLayout.setOnRefreshListener(newValue);
    }
    public SwipeRefreshLayout.OnRefreshListener onRefreshList() {
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Do something you need
                Log.e("TAG","onRefreshListener");
                loadData();
            }
        };
    }

}
