package com.liangxiao.fragmentslidingmenu;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.liangxiao.fragments.Myfragment1;
import com.liangxiao.fragments.Myfragment2;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout; // activity��������
	private LinearLayout mLeftLayout; // ����ͼ
	private LinearLayout mRightLayout; // �һ���ͼ
	private ListView mLeftLayout_listview;// �����ͼ��listview
	private String[] mPlanetTitles;// ��ߵ�item
	private CharSequence mDrawerTitle; // ������
	private CharSequence mTitle;// ����Ŀ����
	private ActionBarDrawerToggle mDrawerToggle;// actionbar����Ŀ���ò���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		init_actionbar();
		// if (savedInstanceState == null) {
		// selectItem(0);
		// }
	}

	// ��ʼ������
	private void init() {
		// actionbar����
		mTitle = mDrawerTitle = getTitle();
		// ��ʼ��item����
		mPlanetTitles = getResources().getStringArray(R.array.planets_array);
		// ���listview����
		mLeftLayout_listview = (ListView) findViewById(R.id.sliding_listview_left);
		mLeftLayout_listview.setAdapter(new ArrayAdapter<String>(this,
				R.layout.slidingmenu_left_item, mPlanetTitles));
		// listview����¼�����
		mLeftLayout_listview
				.setOnItemClickListener(new DrawerItemClickListener());
		// �����岿��
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// ��߲���
		mLeftLayout = (LinearLayout) findViewById(R.id.left_drawer);
		// �ұ߲���
		mRightLayout = (LinearLayout) findViewById(R.id.right_drawer);
	}

	private void init_actionbar() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
	}

	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// AdapterView<?> parent, View view, int position, long id
			selectItem(position);
			Toast.makeText(MainActivity.this, position + "", Toast.LENGTH_SHORT)
					.show();
		}

	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment = new Fragment();
		Bundle args = new Bundle();
		args.putInt("position", position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		if (position == 0) {
			fragment = new Myfragment1();
//			fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).
		} else if (position == 1) {
			fragment = new Myfragment2();
		}
		// Fragment fragment = new PlanetFragment();
		// Bundle args = new Bundle();
		// args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
		// fragment.setArguments(args);
		//
		// FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager.beginTransaction()
		// .replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mLeftLayout_listview.setItemChecked(position, true);
		setTitle(mPlanetTitles[position]);
		mDrawerLayout.closeDrawer(mLeftLayout);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
