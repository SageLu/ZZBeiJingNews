
package zz.com.hanhan.testnewstab;

import android.app.Application;

import zz.com.hanhan.testnewstab.utils.SQLHelper;


public class App extends Application {
    private static App mAppApplication;
    private SQLHelper sqlHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this;

    }

    /** 获取Application */
    public static App getApp() {
        return mAppApplication;
    }

    /** 获取数据库Helper */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        // 整体摧毁的时候调用这个方法
    }


}
