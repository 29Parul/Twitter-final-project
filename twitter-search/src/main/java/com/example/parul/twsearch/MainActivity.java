package com.example.parul.twsearch;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.parul.twsearch.util.ActivityHelper;


public class MainActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActivityHelper.navigateTo(this, new com.example.parul.twsearch.WelcomeFragment(), false, R.id.container_main);
		getFragmentManager().addOnBackStackChangedListener(this);
		shouldDisplayHomeUp();
	}

	@Override
	public void onBackStackChanged() {
		shouldDisplayHomeUp();
	}

	public void shouldDisplayHomeUp() {
		boolean canback = getFragmentManager().getBackStackEntryCount() > 0;
		getSupportActionBar().setDisplayHomeAsUpEnabled(canback);
	}

	@Override
	public boolean onNavigateUp() {
		getFragmentManager().popBackStack();
		return true;
	}

}
