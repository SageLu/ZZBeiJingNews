package zz.com.hanhan.testrecyclerview;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import zz.com.hanhan.testrecyclerview.animator.RotateItemAnimator;
import zz.com.hanhan.testrecyclerview.wraprecyclerview.WrapRecyclerViewActivity;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<String> datas;
    private MyRecyclerViewAdapter recyclerViewAdapter;
    private SwipeRefreshLayout swipe_refresh_widget;
    private LinearLayoutManager mLayoutManager ;
    private int lastVisibleItem;
    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what==1) {
//                fetchingNewData();
                heheData();
            }else {
                swipe_refresh_widget.setRefreshing(false);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        innitData();

        swipe_refresh_widget = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_widget);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        swipe_refresh_widget.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary));
        swipe_refresh_widget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //刷新
                handler.sendEmptyMessageDelayed(2,1000);
            }
        });
//// 这句话是为了，第一次进入页面的时候显示加载进度条
//        swipe_refresh_widget.setProgressViewOffset(false, 0, (int) TypedValue
//                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
//                        .getDisplayMetrics()));


        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && lastVisibleItem + 1 == recyclerViewAdapter.getItemCount()) {
                    swipe_refresh_widget.setRefreshing(true);
                    // 此处在现实项目中，请换成网络请求数据代码，sendRequest .....
                    handler.sendEmptyMessageDelayed(1,3000);


                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            }
        });


        recyclerView.setHasFixedSize(true);
        mLayoutManager  = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        //设置布局样式
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerViewAdapter = new MyRecyclerViewAdapter(this, datas);
        recyclerView.setAdapter(recyclerViewAdapter);

//        //设置布局样式
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL_LIST));
//
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
//        recyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        //设置每一条的点击监听
        recyclerViewAdapter.setOnItemClickListener(new MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String data) {
                Toast.makeText(MainActivity.this, "data:" + data, Toast.LENGTH_SHORT).show();
            }
        });

        //设置动画
//        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.addOnScrollListener();

        recyclerView.setItemAnimator(new RotateItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(0,10,0,10);
            }
        });

    }


    private void innitData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            datas.add("第" + i + "个妹纸");
        }

    }

    private void heheData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <3 ; i++) {
            list.add("heheheh" + i + "xinshuju");
        }
        datas.addAll(list);

        recyclerViewAdapter.notifyDataSetChanged();
        swipe_refresh_widget.setRefreshing(false);
        recyclerView.scrollToPosition(lastVisibleItem+1);
    }

    private void fetchingNewData() {
        datas.add(recyclerViewAdapter.getItemCount(), "加载出来的数据");
        recyclerViewAdapter.notifyDataSetChanged();
        swipe_refresh_widget.setRefreshing(false);
        recyclerView.scrollToPosition(recyclerViewAdapter.getItemCount()+1);
    }

    public void towrap(View view) {
        Intent intent = new Intent(this, WrapRecyclerViewActivity.class);
        startActivity(intent);

    }

    public void add(View view) {
        recyclerViewAdapter.addData(0, "new_data");
        recyclerView.scrollToPosition(0);

    }

    public void delete(View view) {
        recyclerViewAdapter.deleteData(0);
    }

    public void toList(View view) {

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

    }

    public void toGrid(View view) {

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void toFlow(View view) {
        Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
        startActivity(intent);

    }

    public void togallery(View view) {
        Intent intent = new Intent(this, GalleryActivity.class);
        startActivity(intent);

    }

    public void tocustom(View view) {
        Intent intent = new Intent(this, CustomRecyclerActivity.class);
        startActivity(intent);

    }
}
