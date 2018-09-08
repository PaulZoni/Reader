package com.bignerdranch.android.reader.iu.adapter.holder;

import android.view.View;
import static com.bignerdranch.android.reader.constants.Constants.PDF;

public class FactoryViewHolder {
    private FactoryViewHolder() {}

    public static BaseHolder returnHolder(String holderType, View view) {
        switch (holderType) {
            case PDF:
                return new PdfHolder(view);
            default:
                return new PdfHolder(view);

        }
    }
}