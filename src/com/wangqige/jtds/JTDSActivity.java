package com.wangqige.jtds;

import java.sql.SQLException;

import com.wangqige.jtds.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class JTDSActivity extends Activity implements Button.OnClickListener {
	ListView listView;
	private DBDefine dbDefine;
	private final static String SERVER_NAME = "jdbc:jtds:sqlserver://7.231.9.238:1433/test";
	private final static String LOGIN_NAME = "wo";
	private final static String LOGIN_PASSWORD = "women123";
	private DBFactory dbFactory;
	private ListAdapter adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectAll().build());
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
		initDataBase();
	}

	private void initDataBase() {
		dbDefine = new DBDefine();
		dbDefine.setServer(SERVER_NAME);
		dbDefine.setUsername(LOGIN_NAME);
		dbDefine.setPassword(LOGIN_PASSWORD);
		dbFactory = new DBFactory(dbDefine);
	}

	private void initView() {
		((Button) findViewById(R.id.execute)).setOnClickListener(this);
		listView = (ListView) findViewById(R.id.listView);
		adapter = new ListAdapter(this);
		
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v) {
		try {
			dbFactory.selectResult();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		adapter.setPopList(dbFactory.getList());
		adapter.notifyDataSetChanged();
	}
}