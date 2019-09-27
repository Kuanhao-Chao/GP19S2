package com.anu.calculator.graphs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.anu.calculator.R;

import java.util.ArrayList;


public class functionlistAdaptor extends ArrayAdapter {
    private ArrayList<ListModel> dataSet;
    Context currentContext;

    private static class ViewHolder {
        TextView func;
        CheckBox checked;
    }

    public functionlistAdaptor(ArrayList data, Context context) {
        super(context, R.layout.list_row, data);
        this.dataSet = data;
        this.currentContext = context;
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ListModel getItem(int position) {
        return dataSet.get(position);
    }
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, parent, false);
            viewHolder.func =  convertView.findViewById(R.id.list_row_func_name);
            viewHolder.checked = convertView.findViewById(R.id.list_row_func_check);
            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        ListModel item = getItem(position);
        viewHolder.func.setText(item.func.show());
        viewHolder.func.setTextColor(CalcChart.getPallet(position));
        viewHolder.checked.setChecked(item.checked);
        return result;
    }

}
