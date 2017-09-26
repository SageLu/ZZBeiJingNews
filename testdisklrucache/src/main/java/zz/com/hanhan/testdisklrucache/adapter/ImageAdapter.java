package zz.com.hanhan.testdisklrucache.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.File;
import java.util.List;

import zz.com.hanhan.testdisklrucache.R;

/**
 * Created by lenovo on 2017/1/10.
 */

public class ImageAdapter extends ListItemAdapter<File> {

    private boolean select = false;


    public void open(int posisiont) {
        select = true;
        booleanArray.put(posisiont, true);
        if (onImageCheckListener != null)
            onImageCheckListener.onImageCheck(true);
        this.notifyDataSetChanged();
    }

    public void close() {
        select = false;
        booleanArray.clear();
        notifyDataSetChanged();
    }

    //position
    //HashMap<Integer, Boolean> map = new HashMap<>();

    private SparseBooleanArray booleanArray = new SparseBooleanArray();

    public SparseBooleanArray getBooleanArray() {
        return booleanArray;
    }

    public ImageAdapter(Context context, List<File> list) {
        super(context, list);
    }

    @Override
    public int getCount() {
        //多出来的就是照相机
        return super.getCount() + 1;
    }

    //    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            ImageView iv = new ImageView(mContext);
//            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            iv.setBackgroundColor(Color.argb(0xFF, 0x07, 0x05, 0x18));
//            int width = mContext.getResources().getDisplayMetrics().widthPixels / 3 - 2;
//            GridView.LayoutParams params = new GridView.LayoutParams(width, width);
//            iv.setPadding(2, 2, 2, 2);
//            iv.setLayoutParams(params);
//            convertView = iv;
//        }
//        ImageView iv = (ImageView) convertView;
//        if (position == 0) {
//            //照相机
//            iv.setImageResource(R.mipmap.camera);
//        } else {
//            iv.setImageURI(Uri.fromFile(getItem(position - 1)));
//        }
//        return convertView;
//    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_image, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0) {
            holder.image.setImageResource(R.mipmap.ic_launcher);
            holder.checkBox.setVisibility(View.GONE);
        } else {
            holder.image.setImageURI(Uri.fromFile(getItem(position - 1)));
            if (select) {
                holder.checkBox.setVisibility(View.VISIBLE);
                //当前的需不需要勾选呢
                //null
                Boolean b = booleanArray.get(position);
                if (b == null || b == false) {
                    holder.checkBox.setChecked(false);
                } else {
                    holder.checkBox.setChecked(true);
                }
                //item点击和布局冲突
                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean b = booleanArray.get(position);
                        if (b == null || b == false)
                            b = true;
                        else
                            b = false;
                        booleanArray.put(position, b);
                        //判断所有的boolean，如果已经没有一个true 关闭
                        for (int i = 0; i < booleanArray.size(); i++) { //4-true  0==false
                            //两个值  key -- > 3 4
                            // 0  1  2 3 4 5
                            boolean isChecked = booleanArray.get(booleanArray.keyAt(i));
                            Log.e("TAG", "----" + isChecked);
                            Log.e("TAG", booleanArray.toString());
                            if (isChecked) {
                                //有被勾选的值
                                if (onImageCheckListener != null)
                                    onImageCheckListener.onImageCheck(true);
                                return;
                            }
                        }
                        if (onImageCheckListener != null)
                            onImageCheckListener.onImageCheck(false);
                        //没有被勾选的值了
                        //关闭
                        close();
                    }
                });
            } else {
                holder.checkBox.setVisibility(View.GONE);
            }
            //不能使用onCheck
//            holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                    booleanArray.put(position, isChecked);
//                }
//            });
        }
        return convertView;
    }


    //回调方法。
    //写在需要执行方法的地方
    //他实现 在需要返回的地方
    public interface OnImageCheckListener {
        public void onImageCheck(boolean b);
    }

    private OnImageCheckListener onImageCheckListener;

    //alt+insert

    public void setOnImageCheckListener(OnImageCheckListener onImageCheckListener) {
        this.onImageCheckListener = onImageCheckListener;
    }

    class ViewHolder {
        ImageView image;
        CheckBox checkBox;

        public ViewHolder(View convertView) {
            image = (ImageView) convertView.findViewById(R.id.iv_image);
            int width = mContext.getResources().getDisplayMetrics().widthPixels / 3 - 2;
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(width, width);
            image.setLayoutParams(params);
            checkBox = (CheckBox) convertView.findViewById(R.id.cb_check);
        }


    }
}
