package zz.com.hanhan.testrecyclerview.wraprecyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import zz.com.hanhan.testrecyclerview.MyRecyclerViewAdapter;
import zz.com.hanhan.testrecyclerview.R;

public class WrapRecyclerViewActivity extends Activity {
    private RecyclerView wrap_recyclerview;
    // 数据适配器包装类
    private WrapAdapter<MyRecyclerViewAdapter> mWrapAdapter;
    private ArrayList<String> datas;
    private MyRecyclerViewAdapter mrecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrap_recycler_view);

        innitData();
        initView();
    }

    private void initView() {
        wrap_recyclerview = (RecyclerView) findViewById(R.id.wrap_recyclerview);

//        wrap_recyclerview.setLayoutManager(new LinearLayoutManager(this));
//        wrap_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        wrap_recyclerview.setLayoutManager(new GridLayoutManager(this, 3));
        //设置分隔符
//        wrap_recyclerview.addItemDecoration(new DividerGridItemDecoration(this));


        // 创建RecyclerView的数据适配器
        mrecyclerViewAdapter = new MyRecyclerViewAdapter(this, datas);
        // 设置将要添加的头部和尾部占据一行
        mWrapAdapter = new WrapAdapter<>(mrecyclerViewAdapter);
        // 设置头部和尾部占据一行
        mWrapAdapter.adjustSpanSize(wrap_recyclerview);
        // 设置RecyclerView的数据适配器(适配器包装)
        wrap_recyclerview.setAdapter(mWrapAdapter);

        //添加头部试图
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.recycler_header, null);
        mWrapAdapter.addHeaderView(view);

    }

    private void innitData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            datas.add("第" + i + "个妹纸");
        }

    }
}
