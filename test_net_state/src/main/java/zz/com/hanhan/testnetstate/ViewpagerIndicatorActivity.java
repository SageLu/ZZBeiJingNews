package zz.com.hanhan.testnetstate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;

public class ViewpagerIndicatorActivity extends Activity {
    private ViewPager pager;
    private TabPageIndicator indicator;
    ArrayList<View> viewContainter = new ArrayList<>();
    ArrayList<String> titleContainer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_indicator);
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        pager = (ViewPager) findViewById(R.id.pager);

        initData();

        pager.setAdapter(new MyPagerAdapter());
        indicator.setViewPager(pager);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Toast.makeText(ViewpagerIndicatorActivity.this, "position" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
}
