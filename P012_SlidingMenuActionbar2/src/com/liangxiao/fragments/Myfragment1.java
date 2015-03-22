package com.liangxiao.fragments;

import com.liangxiao.fragmentslidingmenu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Myfragment1 extends Fragment {
	private TextView myTextView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.myfragment1, container, false);
		myTextView = (TextView) v.findViewById(R.id.myTextView);
		myTextView.setOnClickListener(listener);
		return v;
	}

	private OnClickListener listener = new OnClickListener() {
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.myTextView:
				Toast.makeText(getActivity(), "ΩÁ√Ê“ª", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};
}
