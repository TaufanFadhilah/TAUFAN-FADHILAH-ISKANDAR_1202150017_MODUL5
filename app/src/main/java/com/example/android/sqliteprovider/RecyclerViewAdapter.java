package com.example.android.sqliteprovider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Taufan Fadhilah on 3/23/2018.
 */

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>{

    private final LinkedList<ToDo> mWordList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context, LinkedList<ToDo> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mWordList = wordList;
    }

    @Override
    public RecyclerViewAdapter.RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.recycler_item, parent, false);
        return new RecyclerViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.RecyclerViewHolder holder, int position) {
        holder.wordNameView.setText(mWordList.get(position).getName());
        holder.wordDescView.setText(mWordList.get(position).getDesc());
        holder.wordPriorityView.setText(mWordList.get(position).getPriority());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordNameView, wordDescView, wordPriorityView;
        final RecyclerViewAdapter mAdapter;

        public RecyclerViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            wordNameView = (TextView) itemView.findViewById(R.id.txtTitle);
            wordDescView = (TextView) itemView.findViewById(R.id.txtDesc);
            wordPriorityView = (TextView) itemView.findViewById(R.id.txtPriority);
            this.mAdapter = adapter;
        }
    }
}

