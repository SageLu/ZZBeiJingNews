package zz.com.hanhan.testdisklrucache.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import zz.com.hanhan.testdisklrucache.R;
import zz.com.hanhan.testdisklrucache.adapter.GlideRecyclerViewAdapter;
import zz.com.hanhan.testdisklrucache.utils.DividerItemDecoration;
import zz.com.hanhan.testdisklrucache.utils.Images;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class LruCacheActivity extends Activity implements GlideRecyclerViewAdapter.IOnItemClickListener {
private RecyclerView glide_recyclerview;
    private ArrayList<String> datas;
    private GlideRecyclerViewAdapter glideRecyclerViewAdapter;
    private String TAG=LruCacheActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        setContentView(R.layout.activity_lrucache);
        glide_recyclerview = (RecyclerView)findViewById(R.id.glide_recyclerview);

        glideRecyclerViewAdapter = new GlideRecyclerViewAdapter(this, datas);
        glide_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        glide_recyclerview.addItemDecoration(new DividerItemDecoration(this,VERTICAL));
        glide_recyclerview.setAdapter(glideRecyclerViewAdapter);
        glideRecyclerViewAdapter.setOnItemClickListener(this);
    }

    private void initData() {
        datas=new ArrayList<>();
        for (int i = 0; i < Images.imageThumbUrls.length; i++) {
            datas.add(Images.imageThumbUrls[i]);
        }

    }

    @Override
    public void onItemClick(int position) {
        Log.e(TAG, Environment.getDownloadCacheDirectory().getPath());
        Toast.makeText(LruCacheActivity.this, "position=="+position+"~~~datas=="+datas.get(position), Toast.LENGTH_SHORT).show();
    }
}
