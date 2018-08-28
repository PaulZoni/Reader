package com.bignerdranch.android.reader.iu.fragment;

import com.bignerdranch.android.reader.model.BaseManager;
import com.bignerdranch.android.reader.model.FileManager;

public class PresenterListFragment implements ContractReader.Presenter {

    private ContractReader.View mView;
    private BaseManager mManager;

    public PresenterListFragment() {
        mManager = new FileManager();
    }

    @Override
    public void attach(ContractReader.View view) {
        this.mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public String readText(String path) {
       return mManager.load(path);
    }
}
