package zz.com.hanhan.testdisklrucache.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import zz.com.hanhan.testdisklrucache.R;
import zz.com.hanhan.testdisklrucache.adapter.ImageAdapter;

public class PictureSelectActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, ImageAdapter.OnImageCheckListener, View.OnClickListener {

    private static final int CAMERA_CODE = 12;
    List<File> fileList = new ArrayList<>();
    ImageAdapter adapter;

    GridView gvImage;
    TextView tvFinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_select);
        initView();
        //弹出对话框，加载数据
        loadData();
    }

    private void initView() {
        gvImage = (GridView) findViewById(R.id.gv_image);
        tvFinish = (TextView) findViewById(R.id.tv_finish);


        adapter = new ImageAdapter(this, fileList);
        adapter.setOnImageCheckListener(this);
        gvImage.setAdapter(adapter);
        gvImage.setOnItemClickListener(this);
        gvImage.setOnItemLongClickListener(this);

        tvFinish.setOnClickListener(this);
    }

    private ProgressDialog showProgressDialog() {
        //弹出对话框
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("提示");
        dialog.setMessage("正在加载图片，请稍等。。。");
        dialog.show();
        return dialog;
    }

    private void loadData() {
        final ProgressDialog dialog = showProgressDialog();
        //开启线程
        new Thread() {
            @Override
            public void run() {
                super.run();
                //递归
                //从sd卡中获取所有图片
                getFile(Environment.getExternalStorageDirectory());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        }.start();
    }

    public void getFile(File dir) {
        //1. 获取子目录
        File[] files = dir.listFiles();
        if (files == null)
            return;
        //集合或者数组去点for
        for (File file : files) {
            if (file.isDirectory())
                getFile(file);
            else {
                //加载图片
                if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
                    fileList.add(file);
                }
            }

        }
    }

    File cameraFile;

    //点击
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            //getAbsolutePath返回的路径是没有"/"
            cameraFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/" + System.currentTimeMillis() + ".png");
            //打开照相机
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //照相机需要带数据
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraFile));
            startActivityForResult(intent, CAMERA_CODE);
        } else {
            //打开大图
            File file = fileList.get(position - 1);
            //带数据跳转到现实大图
            Intent intent = new Intent(this, ShowBigImage.class);
            intent.putExtra("file", file);
            startActivity(intent);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", resultCode + "---------------------");
        if (requestCode == CAMERA_CODE && resultCode == RESULT_OK) {
            Log.e("TAG", (cameraFile.exists()) + "");
            fileList.add(0, cameraFile);
            adapter.notifyDataSetChanged();
        }
    }

    //长按
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0)
            return false;
        else {
            adapter.open(position);
        }
        return true;
    }

    @Override
    public void onImageCheck(boolean b) {
        //b代表 适配器中 有没有勾选的值
        tvFinish.setEnabled(b);

    }

    @Override
    public void onClick(View v) {
        //需要知道有哪些数据被选中
        //不能使用泛型，ArrayList才实现了序列化，List没有实现
        ArrayList<File> resultList = new ArrayList<>();
        //通过适配器中的 为true的 选中的项来加载file
        SparseBooleanArray booleanArray = adapter.getBooleanArray();
        for (int i = 0; i < booleanArray.size(); i++) {
            boolean isCheck = booleanArray.get(booleanArray.keyAt(i));
            if (isCheck) {
                int position = booleanArray.keyAt(i);
                resultList.add(fileList.get(position - 1));
            }
        }
        Intent intent = new Intent();
        intent.putExtra("list", resultList);
        //返回数据
        setResult(RESULT_OK, intent);
        finish();
    }
}
