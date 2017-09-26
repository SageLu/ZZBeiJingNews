package zz.com.hanhan.testdisklrucache.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by lenovo on 2017/1/10.
 */

//也可以用 extends 来限制一个泛型的父类
//在类的后面定义一个泛型
public abstract class ListItemAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mList;

    //必须要有上下文，数据
    //List<File>  List<String>
    public ListItemAdapter(Context context, List<T> list) {
        mContext = context;
        mList = list;
    }

    //适配器去加载一个List
    public void setList(List<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}