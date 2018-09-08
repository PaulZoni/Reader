package com.bignerdranch.android.reader.iu.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseHolder<T> extends RecyclerView.ViewHolder {
    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    abstract public void setContextView(T bitmap) ;
}