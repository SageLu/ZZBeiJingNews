package zz.com.hanhan.testnewstab;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import zz.com.hanhan.testnewstab.utils.ACache;
import zz.com.hanhan.testnewstab.utils.DialogUtil;
import zz.com.hanhan.testnewstab.utils.IntentUtils;
import zz.com.hanhan.testnewstab.utils.StringUtils;

/**
 * Created by lenovo on 2016/12/25.
 */
public class BaseActivity extends Activity{
    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 无标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    /**
     * 显示dialog
     *
     *
     */
    public void showProgressDialog() {
        try {

            if (progressDialog == null) {
                progressDialog = DialogUtil.createLoadingDialog(this);

            }
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏dialog
     */
    public void dismissProgressDialog() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dialog是否显示
     */
    public boolean isShow() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更具类打开acitvity
     */
    public void openActivity(Class<?> pClass) {
        openActivity(pClass, null, 0);

    }

    public void openActivityForResult(Class<?> pClass, int requestCode) {
        openActivity(pClass, null, requestCode);
    }

    /**
     * 更具类打开acitvity,并携带参数
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (requestCode == 0) {
            IntentUtils.startPreviewActivity(this, intent, 0);
            // startActivity(intent);
        } else {
            IntentUtils.startPreviewActivity(this, intent, requestCode);
            // startActivityForResult(intent, requestCode);
        }
        // actityAnim();
    }


    /**
     * 显示Toast
     */
    public void showShortToast(String pMsg) {
        // ToastUtil.createCustomToast(this, pMsg, Toast.LENGTH_LONG).show();
        Toast.makeText(this, pMsg, Toast.LENGTH_SHORT).show();
    }



    /**
     * 设置缓存数据（key,value）
     */
    public void setCacheStr(String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            ACache.get(this).put(key, value);
        }
    }

    /**
     * 获取缓存数据更具key
     */
    public String getCacheStr(String key) {
        return ACache.get(this).getAsString(key);
    }
}
