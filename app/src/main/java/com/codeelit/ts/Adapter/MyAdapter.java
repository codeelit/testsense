package com.codeelit.ts.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.codeelit.ts.Dummy;
import com.codeelit.ts.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyHolder> {


    ArrayList<String> mData;
    RequestOptions options ;
    private Context mContext ;


    public MyAdapter(Context mContext, ArrayList<String> models) {
        this.mContext = mContext;
        this.mData = models;
        options = new RequestOptions()
                .centerCrop()
                .placeholder( R.drawable.ic_arrow_forward_black_24dp)
                .error(R.drawable.ic_arrow_forward_black_24dp);

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_activity, null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, int i) {
        myHolder.mTitle.setText(mData.get(i));

        myHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(mContext, Dummy.class);
                // sending data process
                i.putExtra("title",String.valueOf( mData.get(myHolder.getPosition())));


              mContext.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
