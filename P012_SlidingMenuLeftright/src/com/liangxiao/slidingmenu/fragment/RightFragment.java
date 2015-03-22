package com.liangxiao.slidingmenu.fragment;

import com.liangxiao.slidingmenuleftright.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RightFragment extends Fragment {
	View _RootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		_RootView = inflater.inflate(R.layout.right_fragment_menu, null);
		return _RootView;
	}
}
