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

    private final LinkedList<String> mWordList;
    private LayoutInflater mInflater;

    public RecyclerViewAdapter(Context context, LinkedList<String> wordList) {
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
        String mCurrent = mWordList.get(position);
        holder.wordItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public final TextView wordItemView;
        final RecyclerViewAdapter mAdapter;

        public RecyclerViewHolder(View itemView, RecyclerViewAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.txtTitle);
            this.mAdapter = adapter;
        }
    }
}

