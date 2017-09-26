package com.example.zhy_handler_imageloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhy.utils.ImageLoader;
import com.zhy.utils.ImageLoader.Type;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;
    private String mDirPath;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;

    public MyAdapter(Context context, List<String> mData, String dirPath) {
        this.mContext = context;
        this.mData = mData;
        this.mDirPath = dirPath;
        mInflater = LayoutInflater.from(mContext);

        mImageLoader = ImageLoader.getInstance(3, Type.LIFO);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.grid_item, parent,
                    false);
            holder.mImageView = (ImageView) convertView
                    .findViewById(R.id.id_item_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImageView
                .setImageResource(R.drawable.friends_sends_pictures_no);
        //使用Imageloader去加载图片
		mImageLoader.loadImage(mDirPath + "/" + mData.get(position),
				holder.mImageView);


        //直接加载不行,为什么？？？？---》》直接崩溃，我也不知道为啥
        //裁剪之后加载可以，但是却无比的卡顿，
//        Bitmap bitmap = BitmapFactory.decodeFile(mDirPath + "/" + mData.get(position), new BitmapFactory.Options());
//        holder.mImageView.setImageBitmap(bitmap);


        return convertView;
    }

    private final class ViewHolder {
        ImageView mImageView;
    }
}
