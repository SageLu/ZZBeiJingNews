package zz.com.hanhan.testrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/12/21.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private final Context context;
    private  ArrayList<String> datas;

    public MyRecyclerViewAdapter(Context context, ArrayList<String> datas) {
        this.context=context;
        this.datas=datas;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView=View.inflate(context,R.layout.item_recyclerview,null);
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_item.setText(datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(int position, String data) {
        datas.add(position,data);
        notifyItemInserted(position);
    }

    public void deleteData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_item;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_item= (TextView) itemView.findViewById(R.id.tv_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, "data:"+datas.get(getLayoutPosition()), Toast.LENGTH_SHORT).show();
                    if(onItemClickListener!=null) {
                        onItemClickListener.OnItemClick(view,datas.get(getLayoutPosition()));
                    }
                }
            });

        }
    }
    public interface OnItemClickListener{

        public void OnItemClick(View view,String data);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
