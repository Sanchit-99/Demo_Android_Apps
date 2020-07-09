package com.example.ibm_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyHolder> {

    private List<data> mydata;
    private LayoutInflater layoutInflater;
    private Boolean isLinear;
    public RecyclerAdapter(List<data> mydata, Context context,Boolean isLinear) {
        this.layoutInflater=LayoutInflater.from(context);
        this.mydata=mydata;
        this.isLinear=isLinear;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(isLinear ?R.layout.listview_layout : R.layout.grid_recycler,parent,false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        holder.img.setImageResource(mydata.get(position).image);
        holder.title.setText(mydata.get(position).name);
        holder.num.setText(mydata.get(position).num);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), holder.title.getText().toString()+" Deleted", Toast.LENGTH_SHORT).show();
                mydata.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mydata.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mydata.size();
    }


    class MyHolder extends RecyclerView.ViewHolder  {

        ImageView img,delete;
        TextView title,num;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.name);
            delete=itemView.findViewById(R.id.del);
            num=itemView.findViewById(R.id.num);
        }
    }
}
