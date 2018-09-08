package com.bignerdranch.android.reader.iu.adapter;

import com.bignerdranch.android.reader.R;
import static com.bignerdranch.android.reader.constants.Constants.PDF;

public class FactoryAdapterLayout {
    private FactoryAdapterLayout() {}
    public static int getLayoutAdapter(String typeFile) {
        switch (typeFile) {
            case PDF:
                return R.layout.pdf_layout;
            default:
                return R.layout.pdf_layout;
        }
    }
}