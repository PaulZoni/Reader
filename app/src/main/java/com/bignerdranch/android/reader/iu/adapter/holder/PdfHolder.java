package com.bignerdranch.android.reader.iu.adapter.holder;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.View;
import com.bignerdranch.android.reader.R;
import com.jsibbold.zoomage.ZoomageView;

public class PdfHolder extends BaseHolder<Bitmap>  {

    private ZoomageView pdfView;

    public PdfHolder(@NonNull View itemView) {
        super(itemView);
        pdfView = itemView.findViewById(R.id.pdfview);
        pdfView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                System.out.println("ZOM");
            }
        });
    }

    @Override
    public void setContextView(Bitmap bitmap) {
        pdfView.setImageBitmap(bitmap);
    }
}