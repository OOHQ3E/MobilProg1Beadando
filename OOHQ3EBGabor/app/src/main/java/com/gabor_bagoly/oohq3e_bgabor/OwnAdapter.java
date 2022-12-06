package com.gabor_bagoly.oohq3e_bgabor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class OwnAdapter extends BaseAdapter {

    String[] array;
    Context context;

    public OwnAdapter(String[] array, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {

        return array.length;
    }

    @Override
    public Object getItem(int i) {
        return array[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }

        TextView textView = view.findViewById(R.id.textView);
        textView.setText(array[i]);
        return view;
    }
}
