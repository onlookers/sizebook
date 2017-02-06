package com.example.jcao5.sizebook;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jcao5 on 2017/2/6.
 */

//record list adapter
public class SizeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Data> list;
    public SizeAdapter(Context context, ArrayList<Data> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            convertView = View.inflate(context,R.layout.lv_item,null);
            vh = new ViewHolder();
            vh.tvName = (TextView) convertView.findViewById(R.id.item_tv);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }
        vh.tvName.setText(list.get(position).getName());
        return convertView;
    }
    class ViewHolder{
        TextView tvName;
    }
}
