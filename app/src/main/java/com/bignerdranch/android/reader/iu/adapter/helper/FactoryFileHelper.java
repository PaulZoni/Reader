package com.bignerdranch.android.reader.iu.adapter.helper;

import static com.bignerdranch.android.reader.constants.Constants.PDF;

public class FactoryFileHelper {
    private FactoryFileHelper() {}
    public static BaseFileHelper getHelper(String typeFile) {
        switch (typeFile) {
            case PDF:
                return  new FileHelperPdf();
            default:
                return new FileHelperPdf();
        }
    }
}