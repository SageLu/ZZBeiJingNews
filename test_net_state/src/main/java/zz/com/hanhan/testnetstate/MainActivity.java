package zz.com.hanhan.testnetstate;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView tv;
    private Button btn;
    private Button btn4;
    boolean isShowingRubberEffect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION); //透明导航栏

        tv = (TextView) findViewById(R.id.tv);
        btn = (Button) findViewById(R.id.btn);
        btn4 = (Button)findViewById(R.id.btn4);
        btn.setOnClickListener(this);

    }

    /**
     * 动画的测试
     * @param view
     */
    public void viewAnimation(View view){

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        btn4.startAnimation(animation);

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                float fraction = animation.getAnimatedFraction();
//                if (fraction >=  0.8&& !isShowingRubberEffect) {
//                    isShowingRubberEffect = true;
//                    finish();
//                } else if (fraction >= 0.95) {
//                    valueAnimator.cancel();
//                    YoYo.with(Techniques.Tada)
//                            .duration(1000)
//                            .playOn(btn4);
//                }

                YoYo.with(Techniques.Tada)
                        .duration(1000)
                        .playOn(btn4);

            }
        });
        valueAnimator.start();
    }
    /*
    navigationView的测试
     */
    public void navigationView(View view){
        Intent intent = new Intent(MainActivity.this, NavigationViewActivity.class);
        startActivity(intent);
    }


    /*
    viewpagerIndicator的测试
     */
    public void viewpagerIndicator(View view){
        Intent intent = new Intent(MainActivity.this, ViewpagerIndicatorActivity.class);
        startActivity(intent);
    }
    /*
        viewpager自带pagerTabStrip的测试
     */
    public void pagerTabStrip(View view){
        Intent intent = new Intent(MainActivity.this, ViewpagerTabStripActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View view) {
        //测试网络状态
        testNet();
        //显示软键盘或隐藏
        KeyBoardUtil.getInstance(this).showOrHide();
        //设置倒计时
        CountDownButtonHelper helper = new CountDownButtonHelper(btn,
                "发送验证码", 60, 1);
        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {

            @Override
            public void finish() {
                Toast.makeText(MainActivity.this, "ZZ倒计时结束",
                        Toast.LENGTH_SHORT).show();
            }
        });
        helper.start();

    }

    private void testNet() {
        if (HttpUtil.isNetworkAvailable(this)) {
            if (HttpUtil.isMobileDataEnable(this)) {
                tv.setText("isMobileDataEnable");
            }
            if (HttpUtil.isWifiDataEnable(this)) {
                tv.setText("isWifiDataEnable");

            }
            if (HttpUtil.isNetworkRoaming(this)) {
                tv.setText("isNetworkRoaming");
            }

        } else {
            tv.setText("没有网络");
        }
    }
}
