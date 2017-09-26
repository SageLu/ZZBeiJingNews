package zz.com.hanhan.zzbeijingnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2016/12/16.
 */

public class CacheUtils {
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("hanhanzz", Context.MODE_PRIVATE);

        return sp.getBoolean(key,false);
    }
}
