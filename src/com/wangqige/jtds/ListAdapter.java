package com.wangqige.jtds;

import java.util.ArrayList;

import  com.wangqige.jtds.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

	private final LayoutInflater mInflater;
	private ArrayList<String> list;

	public void setPopList(ArrayList<String> popList) {
		this.list = popList;
	}

	public ListAdapter(Context context) {
		super();
		this.mInflater = LayoutInflater.from(context);
		list = new ArrayList<String>();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ListHolder holder = null;

		if (convertView == null) {
			holder = new ListHolder();
			convertView = mInflater.inflate(R.layout.listview, null);
			holder.str1 = (TextView) convertView.findViewById(R.id.str1);
			holder.str2 = (TextView) convertView.findViewById(R.id.str2);
			holder.str3 = (TextView) convertView.findViewById(R.id.str3);
			holder.str4 = (TextView) convertView.findViewById(R.id.str4);

			convertView.setTag(holder);
		} else {
			holder = (ListHolder) convertView.getTag();
		}
		String[] list2 = String.valueOf(list.get(position)).split("@__@@");
		holder.str1.setText(list2[0]);
		holder.str2.setText(list2[1]);
		holder.str3.setText(list2[2]);
		holder.str4.setText(list2[3]);
		
		return convertView;
	}

	class ListHolder {
		public TextView str1;
		public TextView str2;
		public TextView str3;
		public TextView str4;

	}
}
