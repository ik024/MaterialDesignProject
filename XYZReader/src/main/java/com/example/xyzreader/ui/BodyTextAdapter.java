package com.example.xyzreader.ui;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xyzreader.R;

import java.util.List;

class BodyTextAdapter extends RecyclerView.Adapter<BodyTextAdapter.MyViewHolder>{

    private List<String> mTextList;

    BodyTextAdapter(List<String> textList){
        mTextList = textList;
    }

    void updateList(List<String> updatedBodyText){
        mTextList = updatedBodyText;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_body_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String text = mTextList.get(position);
        holder.setBodyText(text);
    }

    @Override
    public int getItemCount() {
        return mTextList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTvBody;
        MyViewHolder(View itemView) {
            super(itemView);
            mTvBody = (TextView) itemView.findViewById(R.id.tv_item_body_text);
            mTvBody.setTypeface(Typeface.createFromAsset(itemView.getContext().getResources().getAssets(), "Rosario-Regular.ttf"));
        }

        public void setBodyText(String text){
            mTvBody.setText(text);
        }
    }
}
