package com.bignerdranch.android.reader.iu.adapter.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;
import java.io.File;
import java.io.IOException;

public class FileHelperPdf implements BaseFileHelper <Bitmap, File>{

    private PdfRenderer pdfRenderer = null;
    private ParcelFileDescriptor fileDescriptor = null;
    private int pageCount;

    public FileHelperPdf() {}

    @Override
    public Bitmap pageRender(int page) {
        PdfRenderer.Page rendererPage = pdfRenderer.openPage(page);
        int rendererPageWidth = rendererPage.getWidth();
        int rendererPageHeight = rendererPage.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(
                rendererPageWidth,
                rendererPageHeight,
                Bitmap.Config.ARGB_8888);

        rendererPage.render(bitmap, null, null,
                PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

        rendererPage.close();
        return bitmap;
        //pdfRenderer.close();
    }

    @Override
    public void open(Context context, File filePath) throws IOException {
        fileDescriptor = ParcelFileDescriptor.open(
                filePath, ParcelFileDescriptor.MODE_READ_ONLY);
        pdfRenderer = new PdfRenderer(fileDescriptor);
        pageCount = pdfRenderer.getPageCount();
        //fileDescriptor.close();
    }

    @Override
    public int getPageCount() {
        return pageCount;
    }
}