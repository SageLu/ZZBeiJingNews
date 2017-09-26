package zz.com.hanhan.testnetstate;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewpagerTabStripActivity extends AppCompatActivity {
    private ViewPager pager = null;
    private PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainter = new ArrayList<>();
    ArrayList<String> titleContainer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_tab_strip);
        pager = (ViewPager) this.findViewById(R.id.viewpager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
        //取消tab下面的长横线
        tabStrip.setDrawFullUnderline(false);
        //设置tab的背景色
        tabStrip.setBackgroundColor(Color.RED);
        //设置当前tab页签的下划线颜色
        tabStrip.setTabIndicatorColor(Color.GREEN);
        tabStrip.setTextSpacing(200);

        initData();
        pager.setAdapter(new MyPagerAdapter());
        pager.addOnPageChangeListener(new MyOnPageChangeListener());
    }

    private void initData() {
        for (int i = 0; i < 10; i++) {
            View view = View.inflate(this, R.layout.item_viewpager, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_item_viewpager);
            tv.setText("内容" + i);

            viewContainter.add(view);
            titleContainer.add("标题" + i);

        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public CharSequence getPageTitle(int position) {
            return titleContainer.get(position);
        }

        @Override
        public int getCount() {
            return viewContainter.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewContainter.get(position));
            return viewContainter.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewContainter.get(position));
        }
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Toast.makeText(ViewpagerTabStripActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
