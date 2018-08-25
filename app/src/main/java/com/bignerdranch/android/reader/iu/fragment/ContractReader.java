package com.bignerdranch.android.reader.iu.fragment;

import com.bignerdranch.android.reader.iu.BasePresenter;
import com.bignerdranch.android.reader.iu.BaseView;
import com.bignerdranch.android.reader.model.BaseManager;

public interface ContractReader {

    interface View extends BaseView<Presenter> {}
    interface Presenter extends BasePresenter {
        String readText();
    }
}
