package com.example.parul.twsearch;

import android.app.Application;
import android.content.Context;

import com.example.parul.twsearch.api.ApiConstants;
import com.example.parul.twsearch.api.TwitterApiService;
import com.example.parul.twsearch.api.TwitterServiceProvider;
import com.example.parul.twsearch.util.BusProvider;
import com.squareup.otto.Bus;

import retrofit.RestAdapter;


public class TwitterSearchApplication extends Application{
	private static TwitterSearchApplication mInstance;
	private static Context mAppContext;

	private TwitterServiceProvider mTwitterService;
	private Bus bus = BusProvider.getInstance();

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		this.setAppContext(getApplicationContext());
		mTwitterService = new TwitterServiceProvider(buildApi(), bus);
		bus.register(mTwitterService);
		bus.register(this);
	}

	private TwitterApiService buildApi() {
		return new RestAdapter.Builder()
				.setEndpoint(ApiConstants.TWITTER_SEARCH_URL)
				.build()
				.create(TwitterApiService.class);
	}



	public static TwitterSearchApplication getInstance(){
		return mInstance;
	}
	public static Context getAppContext() {
		return mAppContext;
	}
	public void setAppContext(Context mAppContext) {
		this.mAppContext = mAppContext;
	}


}
