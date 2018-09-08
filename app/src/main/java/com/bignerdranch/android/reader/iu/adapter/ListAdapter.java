package com.bignerdranch.android.reader.iu.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bignerdranch.android.reader.iu.adapter.helper.BaseFileHelper;
import com.bignerdranch.android.reader.iu.adapter.helper.FactoryFileHelper;
import com.bignerdranch.android.reader.iu.adapter.holder.BaseHolder;
import com.bignerdranch.android.reader.iu.adapter.holder.FactoryViewHolder;
import java.io.File;
import java.io.IOException;

public class ListAdapter extends RecyclerView.Adapter<BaseHolder> {

    private ListenerPage listenerPage;
    private BaseFileHelper fileHelper;
    private String typeFile;

    public ListAdapter(Context context, String typeFile, File file) throws IOException {
        initializationsHelper(typeFile);
        fileHelper.open(context, file);
    }

    public ListAdapter(Context context, String typeFile, String file) throws IOException {
        initializationsHelper(typeFile);
        fileHelper.open(context, file);
    }

    private void initializationsHelper(String typeFile) {
        this.typeFile = typeFile;
        fileHelper = FactoryFileHelper.getHelper(typeFile);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewTpe) {
        View view = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(FactoryAdapterLayout.getLayoutAdapter(typeFile), viewGroup, false);
        return FactoryViewHolder.returnHolder(typeFile, view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseHolder pdfHolder, int position) {
        pdfHolder.setContextView(fileHelper.pageRender(position));
        listenerPage.positionChange(position);
    }

    @Override
    public int getItemCount() {
        return fileHelper.getPageCount();
    }

    public void addListener(ListenerPage listenerPage){
        this.listenerPage = listenerPage;
    }

    public interface ListenerPage {
        void positionChange(int position);
    }
}