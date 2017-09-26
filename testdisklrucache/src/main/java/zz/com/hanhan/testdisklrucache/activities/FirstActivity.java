package zz.com.hanhan.testdisklrucache.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import zz.com.hanhan.testdisklrucache.R;
import zz.com.hanhan.testdisklrucache.adapter.MyRecyclerViewAdapter;
import zz.com.hanhan.testdisklrucache.utils.DividerItemDecoration;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class FirstActivity extends Activity implements MyRecyclerViewAdapter.IOnItemClickListener {
    private RecyclerView recyclerview;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private ArrayList<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_first);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, datas);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.addItemDecoration(new DividerItemDecoration(this,VERTICAL));
        recyclerview.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        datas = new ArrayList<>();
        datas.add("loadBigPicture");
        datas.add("lruCache");
        datas.add("piactureSelect");
        datas.add("listviewDelete");
        datas.add("dragpicture");


    }

    @Override
    public void onItemClick(int position) {
        Intent intent;
        if (datas.get(position).equals("loadBigPicture")) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (datas.get(position).equals("lruCache")) {
            intent = new Intent(this, LruCacheActivity.class);
            startActivity(intent);
        }
        if (datas.get(position).equals("piactureSelect")) {
            intent = new Intent(this, PictureSelectActivity.class);
            startActivity(intent);
        }
        if (datas.get(position).equals("listviewDelete")) {
            intent = new Intent(this, ListviewDeleteActivity.class);
            startActivity(intent);
        }
        if (datas.get(position).equals("dragpicture")) {
            intent = new Intent(this, DragPictureActivity.class);
            startActivity(intent);
        }

    }
}
