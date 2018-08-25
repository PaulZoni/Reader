package com.bignerdranch.android.reader.iu;

import com.bignerdranch.android.reader.iu.fragment.ContractReader;

public interface BasePresenter {
     void attach(ContractReader.View view);
     void detach();
}
