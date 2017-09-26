package zz.com.hanhan.testnetstate;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class NavigationViewActivity extends AppCompatActivity {
    private DrawerLayout drawerlayout;
    private NavigationView navigation_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        inits();

    }

    private void inits() {
        drawerlayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = navigation_view.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NavigationViewActivity.this, "上边的布局", Toast.LENGTH_SHORT).show();
            }
        });

        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                //在这里处理item的点击事件
                switch (item.getItemId()) {
                    case R.id.nav_news:
                        Toast.makeText(NavigationViewActivity.this, "新闻...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_photo:
                        Toast.makeText(NavigationViewActivity.this, "图片...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_video:
                        Toast.makeText(NavigationViewActivity.this, "视频...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_night_mode:
                        break;
                }
                drawerlayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });
    }

    public void goMenu(View view) {
        drawerlayout.openDrawer(Gravity.LEFT);

    }


}
