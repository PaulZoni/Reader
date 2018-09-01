package com.bignerdranch.android.reader.iu.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.bignerdranch.android.reader.R;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<String> textInList;

    public ListAdapter(List<String> textInList) {
        this.textInList = textInList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_book, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.mTextView.setText(textInList.get(position));
    }

    @Override
    public int getItemCount() {
        return textInList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_in_list) TextView mTextView;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            //mTextView = itemView.findViewById(R.id.text_in_list);
        }
    }
}
