package com.lww.mwwm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lww.mwwm.view.ItemFragment;
import com.lww.mwwm.view.NoscrollListView;
import com.lww.mwwm.view.SyncHorizontalScrollView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoscrollListView mLeft;
    private LeftAdapter mLeftAdapter;
    private SyncHorizontalScrollView mHeaderHorizontal;
    private SyncHorizontalScrollView mDataHorizontal;
    private List<String> mListData;
    private ArrayList<HashMap<String, String>> data ;
    private Button bt_1;
    private ArrayList<Fragment> fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
//        initData();
        setListener();
        FragmentManager manager =getFragmentManager();
        FragmentTransaction tx = manager.beginTransaction();
        fragments=new ArrayList<Fragment>();
        for (int i = 0; i < mListData.size(); i++) {
            ItemFragment mFOne = new ItemFragment();
            fragments.add(mFOne);
        }
        Log.i("TAG", "fragment.size=="+fragments.size());
        for (int i = 0; i < fragments.size(); i++) {
            tx.add(R.id.lv_data, fragments.get(i));
        }
        tx.commit();
    }
    private void setListener() {
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < fragments.size(); i++) {
            ItemFragment fragment=(ItemFragment) fragments.get(i);
            fragment.setXm(data.get(i).get("姓名"+i));
            fragment.setNl(data.get(i).get("年龄"+i));
            fragment.setTw(data.get(i).get("体温"+i));
            fragment.setMb(data.get(i).get("脉搏"+i));
            fragment.setHx(data.get(i).get("呼吸"+i));
        }

    }

    protected void save() {
        for (int i = 0; i < fragments.size(); i++) {
            String string=  ((ItemFragment)fragments.get(i)).getValue();
            Log.i("TAG", "string=="+string);

        }
    }

    private void initView(){
        bt_1 =(Button) findViewById(R.id.bt_1);
        mLeft = (NoscrollListView) findViewById(R.id.lv_left);
        mDataHorizontal = (SyncHorizontalScrollView) findViewById(R.id.data_horizontal);
        mHeaderHorizontal = (SyncHorizontalScrollView) findViewById(R.id.header_horizontal);

        mDataHorizontal.setScrollView(mHeaderHorizontal);
        mHeaderHorizontal.setScrollView(mDataHorizontal);

        mListData = new ArrayList<>();
        mListData.add("1");
        mListData.add("2");
        mListData.add("3");
        mListData.add("4");
        mListData.add("5");
        mListData.add("6");
        mListData.add("7");
        mListData.add("8");
        mListData.add("9");
        mListData.add("10");
        mListData.add("11");
        mListData.add("12");
        mListData.add("13");
        for(int i=14;i<1000;i++){
            mListData.add(i+"");
        }
        mLeftAdapter= new LeftAdapter();
        mLeft.setAdapter(mLeftAdapter);
        setData();
    }

    private void setData() {
        if (data==null) {
            data=new ArrayList<>();
        }
        for (int i = 0; i <mListData.size(); i++) {
            HashMap<String, String> map=new HashMap<String, String>();
            map.put("姓名"+i, i+"姓名");
            map.put("年龄"+i, i+"年龄");
            map.put("体温"+i, i+"体温");
            map.put("脉搏"+i, i+"脉搏");
            map.put("呼吸"+i, i+"呼吸");
            data.add(map);

        }
    }

class LeftAdapter extends BaseAdapter {
    ArrayList<ArrayList<EditText>>list;
        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.item_left, null);
                holder.tvLeft = (TextView) convertView.findViewById(R.id.tv_left);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tvLeft.setText("第" + position + "床");

            return convertView;
        }

        class ViewHolder {
            TextView tvLeft;
        }
    }
  }

