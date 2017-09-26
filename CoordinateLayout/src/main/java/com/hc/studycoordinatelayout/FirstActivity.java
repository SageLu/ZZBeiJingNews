package com.hc.studycoordinatelayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.VERTICAL;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView rvToDoList;
    private ArrayList<String> datas;
    private MyRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_first);
        rvToDoList = (RecyclerView)findViewById(R.id.rvToDoList);
        innitData();
        recyclerViewAdapter = new MyRecyclerViewAdapter(this, datas);
        rvToDoList.setAdapter(recyclerViewAdapter);
        rvToDoList.setLayoutManager(new LinearLayoutManager(this,VERTICAL,false));


    }
    private void innitData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            datas.add("第" + i + "个xiao妹纸");
        }

    }
    public void toMainActivity(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    public void toAppBarActivity(View view){
        Intent intent = new Intent(this, AppBarActivity.class);
        startActivity(intent);

    }


}
