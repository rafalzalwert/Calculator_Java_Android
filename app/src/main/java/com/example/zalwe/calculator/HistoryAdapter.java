package com.example.zalwe.calculator;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter<DbHelper> {

    Context context;
    int resource;
    List<DbHelper> equationList;
    FeedReaderManager manager;
    public HistoryAdapter(Context context, int resource, List<DbHelper> equationList,FeedReaderManager manager) {
        super(context, resource,equationList);

        this.context=context;
        this.equationList=equationList;
        this.resource=resource;
        this.manager=manager;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(resource,null);
        TextView Row = view.findViewById(R.id.Row);

        DbHelper dbHelper= equationList.get(position);

        Row.setText(dbHelper.getEqauation());

        return view;
    }
}
