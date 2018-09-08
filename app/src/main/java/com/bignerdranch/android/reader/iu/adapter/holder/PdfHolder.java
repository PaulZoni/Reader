package com.bignerdranch.android.reader.iu.adapter.holder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bignerdranch.android.reader.R;

public  class PdfHolder extends BaseHolder<Bitmap>  {

    private ImageView pdfView;

    public PdfHolder(@NonNull View itemView){
        super(itemView);
        pdfView = itemView.findViewById(R.id.pdfview);
    }

    @Override
    public void setContextView(Bitmap bitmap) {
        pdfView.setImageBitmap(bitmap);
    }
}