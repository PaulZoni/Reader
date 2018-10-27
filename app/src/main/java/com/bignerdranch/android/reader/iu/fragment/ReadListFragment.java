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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bignerdranch.android.reader.R;
import com.bignerdranch.android.reader.constants.Constants;
import com.bignerdranch.android.reader.iu.adapter.ListAdapter;
import com.bignerdranch.android.reader.iu.view.MovableFloatingActionButton;
import com.bignerdranch.android.reader.state.StateManager;
import java.io.File;
import java.io.IOException;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ReadListFragment extends Fragment implements ContractReader.View, ListAdapter.ListenerPage {

    @BindView(R.id.recycler_view_list) RecyclerView mRecyclerView;
    @BindView(R.id.buttonRight) MovableFloatingActionButton button;
    @BindView(R.id.buttonLeft) MovableFloatingActionButton buttonLeft;
    @BindView(R.id.view_image) LinearLayout viewImage;
    private ContractReader.Presenter mPresenter;
    private int positionPage = 0;
    private int currentPosition = 0;
    private String typeFile;
    private String path;
    private int st = 0;

    public ReadListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View parentViewActivity = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, parentViewActivity);

        if (StateManager.StateReadList.isReadListDestroy()) {
            path = StateManager.StateReadList.getPath();
            typeFile = StateManager.StateReadList.getTypeFile();
            createPage(path, typeFile);
            StateManager.StateReadList.setReadListDestroy(false);
        }else checkBundle();
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
        if (path != null && typeFile != null ) {
            StateManager.StateReadList.setReadListDestroy(true);
            StateManager.StateReadList.setPageNumber(currentPosition);
            StateManager.StateReadList.setTypeFile(typeFile);
            StateManager.StateReadList.setPath(path);
        }
        mPresenter.detach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void checkBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null){
            viewImage.setLayoutParams(new FrameLayout.LayoutParams(0,0));
            typeFile = bundle.getString(Constants.TYPE_FILE_KEY);
            path = bundle.getString(Constants.PATH_FILE, "");
            createPage(path, typeFile);
        }
    }

    private void createPage(String path, String typeFile) {
        File file = new File(path);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        ListAdapter listAdapter = null;
        try {
            listAdapter = new ListAdapter( getActivity(), typeFile, file);
            listAdapter.addListener(this);


            LinearLayoutManager manager = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false) {
                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
            mRecyclerView.setNestedScrollingEnabled(false);
            mRecyclerView.setLayoutManager(manager);
            mRecyclerView.setAdapter(listAdapter);
            listenerPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void listenerPage() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (positionPage != currentPosition) {
                        Toast.makeText( getContext() , "pageCount = " + positionPage, Toast.LENGTH_LONG).show();
                        currentPosition = positionPage;
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        button.setOnClickListener((view)-> {
            st++;
            mRecyclerView.scrollToPosition(st);
        });

        buttonLeft.setOnClickListener((view)-> {
            if (st > 0)st--;
            mRecyclerView.scrollToPosition(st);
        });
    }

    @Override
    public void positionChange(int position) {
        this.positionPage = position;
    }
}
