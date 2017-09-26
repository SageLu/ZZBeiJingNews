package zz.com.hanhan.zzbeijingnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import zz.com.hanhan.zzbeijingnews.activity.GuideActivity;
import zz.com.hanhan.zzbeijingnews.activity.MainActivity;
import zz.com.hanhan.zzbeijingnews.utils.CacheUtils;

public class SplashActivity extends AppCompatActivity {

    /**
     * 静态常量
     */
    public static final String START_MAIN = "start_main";
    private RelativeLayout rl_splahs_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rl_splahs_root = (RelativeLayout)findViewById(R.id.rl_splahs_root);

        //渐变动画，缩放动画，旋转动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        sa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        ra.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        rl_splahs_root.startAnimation(set);

        set.setAnimationListener(new MyAnimationListener());


    }

    private class MyAnimationListener implements Animation.AnimationListener {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //判断是否进入过主页面
            boolean isStartMain = CacheUtils.getBoolean(SplashActivity.this, START_MAIN);
            Intent intent;
            if(isStartMain){
                //如果进入过主页面，直接进入主页面
                //2.跳转到主页面
                intent = new Intent(SplashActivity.this,MainActivity.class);

            }else{
                //如果没有进入过主页面，进入引导页面
                intent = new Intent(SplashActivity.this,GuideActivity.class);
            }
            startActivity(intent);

            //关闭Splash页面
            finish();

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
