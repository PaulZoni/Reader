package com.bignerdranch.android.reader.iu.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bignerdranch.android.reader.R;
import com.bignerdranch.android.reader.constants.Constants;
import com.bignerdranch.android.reader.iu.BaseActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class FileFragment extends ListFragment {

    private final String TRANSITION = "..";
    private View mView;
    private BaseActivity mBaseActivity;
    private List<String> directoryEntries = new ArrayList<>();
    private File currentDirectory = new File("/");
    @BindView(R.id.titleManager) TextView titleManager;

    public FileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_file, container, false);
        ButterKnife.bind(this, mView);
        browseTo(new File(String.valueOf(Environment.getExternalStorageDirectory())));
        return mView;
    }

    private void upOneLevel(){
        if(this.currentDirectory.getParent() != null) {
            this.browseTo(this.currentDirectory.getParentFile());
        }
    }

    private void browseTo(final File aDirectory) {
        if (aDirectory.isDirectory()) {
            if (aDirectory.listFiles() == null) return;
            this.currentDirectory = aDirectory;
            fill(aDirectory.listFiles());
            titleManager.setText(aDirectory.getAbsolutePath());
        } else {
            if (getContext() != null) createDialog(aDirectory);
        }
    }

    private void fill(File[] files) {
        if (files == null) return;
        this.directoryEntries.clear();
        if (this.currentDirectory.getParent() != null)
            this.directoryEntries.add(TRANSITION);
        for (File file : files) {
            this.directoryEntries.add(file.getAbsolutePath());
        }
        ArrayAdapter<String> directoryList = new ArrayAdapter<>(getContext(), R.layout.row, this.directoryEntries);
        this.setListAdapter(directoryList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseActivity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String selectedFileString = this.directoryEntries.get(position);
        if(selectedFileString.equals(TRANSITION)){
            this.upOneLevel();
        } else {
            //browse to clicked file or directory using browseTo()
            File clickedFile = new File(selectedFileString);
            if (clickedFile != null)
                this.browseTo(clickedFile);
        }
    }

    private void createDialog(final File aDirectory) {
        DialogInterface.OnClickListener okButtonListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                switch (formatCheck(aDirectory)) {
                    case Constants.TXT:
                        mBaseActivity.startFragment(aDirectory.getAbsolutePath(), Constants.TXT);
                        break;
                    case ".pdf":
                        mBaseActivity.startFragment(aDirectory.getAbsolutePath(), Constants.PDF);
                        break;
                }
            }
        };
        if (getContext() != null) new AlertDialog.Builder(getContext())
                .setTitle("Подтверждение") //title
                .setMessage("Хотите открыть файл "+ aDirectory.getName() + "?")
                .setPositiveButton("Да", okButtonListener)
                .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();
    }

    private String formatCheck(File file) {
        int index = file.toString().indexOf('.');
        return index == -1? null : file.toString().substring(index);
    }
}
