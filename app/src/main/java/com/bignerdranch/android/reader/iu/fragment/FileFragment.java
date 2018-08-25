package com.bignerdranch.android.reader.iu.fragment;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.IDNA;
import android.net.Uri;
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
import com.bignerdranch.android.reader.iu.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FileFragment extends ListFragment {

    private View mView;
    private BaseActivity mBaseActivity;
    private List<String> directoryEntries = new ArrayList<>();
    private File currentDirectory = new File("/");

    public FileFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_file, container, false);
        browseTo(new File(String.valueOf(Environment.getExternalStorageDirectory())));
        return mView;
    }

    private void upOneLevel(){
        if(this.currentDirectory.getParent() != null) {
            this.browseTo(this.currentDirectory.getParentFile());
        }
    }

    private void browseTo(final File aDirectory) {
        //if we want to browse directory
        if (aDirectory.isDirectory()) {
            //fill list with files from this directory
            this.currentDirectory = aDirectory;
            fill(aDirectory.listFiles());

            //set titleManager text
            TextView titleManager = mView.findViewById(R.id.titleManager);
            titleManager.setText(aDirectory.getAbsolutePath());
        } else {
            //if we want to open file, show this dialog:
            //listener when YES button clicked
            DialogInterface.OnClickListener okButtonListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("file://" + aDirectory.getAbsolutePath()));
                    startActivity(i);
                }
            };
            new AlertDialog.Builder(getContext())
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
    }

    private void fill(File[] files) {
        this.directoryEntries.clear();

        if (this.currentDirectory.getParent() != null)
            this.directoryEntries.add("..");

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
        int selectionRowID = position;
        String selectedFileString = this.directoryEntries.get(selectionRowID);

        //if we select ".." then go upper
        if(selectedFileString.equals("..")){
            this.upOneLevel();
        } else {
            //browse to clicked file or directory using browseTo()
            File clickedFile = new File(selectedFileString);
            if (clickedFile != null)
                this.browseTo(clickedFile);
        }
    }

}
