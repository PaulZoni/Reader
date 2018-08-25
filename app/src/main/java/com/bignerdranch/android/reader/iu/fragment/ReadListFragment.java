package com.bignerdranch.android.reader.iu.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bignerdranch.android.reader.R;
import com.bignerdranch.android.reader.iu.adapter.ListAdapter;
import java.util.ArrayList;
import java.util.List;


public class ReadListFragment extends Fragment implements ContractReader.View {

    private ContractReader.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private View parentViewActivity;
    private List<String> listText;

    public ReadListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentViewActivity = inflater.inflate(R.layout.fragment_list, container, false);

        initializationComponent();
        testList();
        createPage();
        return parentViewActivity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter = new PresenterListFragment();
        mPresenter.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initializationComponent() {
        mRecyclerView = parentViewActivity.findViewById(R.id.recycler_view_list);
    }

    private void createPage() {
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        ListAdapter listAdapter = new ListAdapter(listText);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL,
                false));
        mRecyclerView.setAdapter(listAdapter);
    }

    private void testList(){
        listText = new ArrayList<>();
        listText.add(mPresenter.readText());
    }
}
