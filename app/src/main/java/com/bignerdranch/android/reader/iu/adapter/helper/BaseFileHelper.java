package com.bignerdranch.android.reader.iu.adapter.helper;

import android.content.Context;

import java.io.IOException;

public interface BaseFileHelper<T,E> {
    T pageRender(int page);
    void open(Context context, E filePath) throws IOException;
    int getPageCount();
}
