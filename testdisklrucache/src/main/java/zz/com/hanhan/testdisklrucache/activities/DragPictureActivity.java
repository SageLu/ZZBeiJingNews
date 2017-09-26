package zz.com.hanhan.testdisklrucache.activities;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import zz.com.hanhan.testdisklrucache.R;
import zz.com.hanhan.testdisklrucache.custom.DragView;

import static android.content.pm.ActivityInfo.FLAG_IMMERSIVE;

/**
 * Created by lenovo on 2017/1/13.
 */

public class DragPictureActivity extends AppCompatActivity implements DragView.Callback{
private ImageView iv_custom;
    private DragView dragview;
    private ColorDrawable colorDrawable;
    private Toolbar toolbar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dragpicture);
        iv_custom = (ImageView)findViewById(R.id.iv_custom);
        dragview = (DragView)findViewById(R.id.dragview);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        dragview.setCallback(this);
        colorDrawable = new ColorDrawable(Color.BLACK);
        dragview.setBackground(colorDrawable);


        //点击时，不能滑动？
//        iv_custom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(DragPictureActivity.this, "图片", Toast.LENGTH_SHORT).show();
//            }
//        });

    }


    @Override
    public void onPullStart() {
        toolBarFadeOut();

        mIsStatusBarHidden = true;
        hideOrShowStatusBar();
    }

    @Override
    public void onPull(float progress) {
        progress = Math.min(1f, progress * 3f);
        colorDrawable.setAlpha((int) (0xff/*255*/ * (1f - progress)));
    }

    @Override
    public void onPullCancel() {
        toolBarFadeIn();
    }

    @Override
    public void onPullComplete() {
        supportFinishAfterTransition();

    }
    @Override
    public void supportFinishAfterTransition() {
        super.supportFinishAfterTransition();
    }

    private boolean mIsToolBarHidden;
    private boolean mIsStatusBarHidden;
    private void toolBarFadeOut() {
        mIsToolBarHidden = false;
        hideOrShowToolbar();
    }
    private void toolBarFadeIn() {
        mIsToolBarHidden = true;
        hideOrShowToolbar();
    }
    private void hideOrShowStatusBar() {
        if (mIsStatusBarHidden) {
            enter(DragPictureActivity.this);
        } else {
            exit(DragPictureActivity.this);
        }
        mIsStatusBarHidden = !mIsStatusBarHidden;
    }
    protected void hideOrShowToolbar() {
        toolbar.animate()
                .alpha(mIsToolBarHidden ? 1.0f : 0.0f)
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        mIsToolBarHidden = !mIsToolBarHidden;
    }
    public static void enter(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            clearFlags(activity.getWindow().getDecorView(), FLAG_IMMERSIVE);
        }
    }
    public static void exit(Activity activity) {
        if (Build.VERSION.SDK_INT >= 19) {
            addFlags(activity.getWindow().getDecorView(), FLAG_IMMERSIVE);
        }
    }
    public static void addFlags(View decorView, int flags) {
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | flags);
    }
    public static void clearFlags(View view, int flags) {
        view.setSystemUiVisibility(view.getSystemUiVisibility() & ~flags);
        // & ~flags 清除view.getSystemUiVisibility()中的flags
    }
}
