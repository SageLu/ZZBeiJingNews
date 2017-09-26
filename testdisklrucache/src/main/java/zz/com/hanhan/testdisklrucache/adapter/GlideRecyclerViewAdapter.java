package zz.com.hanhan.testdisklrucache.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.FutureTarget;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import zz.com.hanhan.testdisklrucache.R;

/**
 * Created by lenovo on 2017/1/9.
 */
public class GlideRecyclerViewAdapter extends RecyclerView.Adapter<GlideRecyclerViewAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<String> datas;

    public GlideRecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_glide_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, context.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, context.getResources().getDisplayMetrics());

        Glide.with(context)
                .load(datas.get(position))
                .placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
//                .skipMemoryCache(true)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(holder.item_iv);


//        new Thread(){
//            public void run(){
//                String cache = getImagePathFromCache(datas.get(0));
//                Log.d("address==",cache);
//            }
//        }.start();


        holder.item_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position);
            }
        });

    }

    private String getImagePathFromCache(String url) {
        FutureTarget<File> fileFutureTarget = Glide.with(context)
                .load(url)
                .downloadOnly(100, 100);
        try {
            String absolutePath = fileFutureTarget.get().getAbsolutePath();
            return absolutePath;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView item_iv;

        public ViewHolder(View itemView) {
            super(itemView);
            item_iv= (ImageView) itemView.findViewById(R.id.item_iv);
        }
    }


    private IOnItemClickListener onItemClickListener;

    public void setOnItemClickListener(IOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface IOnItemClickListener {
        void onItemClick(int position);
    }
}
