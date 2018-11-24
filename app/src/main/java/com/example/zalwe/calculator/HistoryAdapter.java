package com.example.zalwe.calculator;

import android.annotation.SuppressLint;
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

    private Context context;
    int resource;
    private List<DbHelper> equationList;
    private DbOperations manager;
    HistoryAdapter(Context context, int resource, List<DbHelper> equationList, DbOperations manager) {
        super(context, resource,equationList);

        this.context=context;
        this.equationList=equationList;
        this.resource=resource;
        this.manager=manager;
    }


    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder")
        View view= inflater.inflate(resource,null);
        TextView Row = view.findViewById(R.id.Row);
        TextView Id = view.findViewById(R.id.Id);
        DbHelper dbHelper= equationList.get(position);
        Row.setText(dbHelper.getEqauation());
        Id.setText(dbHelper.getDataId()+".");
        return view;

    }



}
