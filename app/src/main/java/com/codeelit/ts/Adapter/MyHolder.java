package com.codeelit.ts.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codeelit.ts.R;

public class MyHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    TextView mTitle, mDesc;
    public View view_container;

    public MyHolder(@NonNull View itemView) {
        super(itemView);
        this.view_container=itemView.findViewById( R.id.root );
        this.mImageView = itemView.findViewById(R.id.imageIv);
        this.mTitle = itemView.findViewById(R.id.titleTv);
        this.mDesc = itemView.findViewById(R.id.descriptionTv);

    }
}
