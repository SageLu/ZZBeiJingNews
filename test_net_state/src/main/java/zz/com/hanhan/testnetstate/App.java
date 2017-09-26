package zz.com.hanhan.testnetstate;

import android.app.Application;
import android.content.Context;

/**
 * Created by lenovo on 2016/12/31.
 */
public class App extends Application{
    private static Context sAppContext;
    public static Context getAppContext() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppContext = this;
    }
}
