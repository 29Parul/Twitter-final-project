package com.example.parul.twsearch.api;

import android.util.Log;

import com.example.parul.twsearch.TwitterSearchApplication;
import com.example.parul.twsearch.event.SearchTweetsEvent;
import com.example.parul.twsearch.event.SearchTweetsEventFailed;
import com.example.parul.twsearch.event.SearchTweetsEventOk;
import com.example.parul.twsearch.event.TwitterGetTokenEvent;
import com.example.parul.twsearch.event.TwitterGetTokenEventFailed;
import com.example.parul.twsearch.event.TwitterGetTokenEventOk;
import com.example.parul.twsearch.util.PrefsController;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.io.UnsupportedEncodingException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.parul.twsearch.util.Util.getBase64String;

public class TwitterServiceProvider {
	private static final String TAG = TwitterServiceProvider.class.getName();

	private TwitterApiService mApi;
	private Bus mBus;

	public TwitterServiceProvider(TwitterApiService api, Bus bus) {
		this.mApi = api;
		this.mBus = bus;
	}

	@Subscribe
	public void onLoadTweets(final SearchTweetsEvent event) {
		mApi.getTweetList("Bearer " + event.twitterToken, event.hashtag, new Callback<TweetList>() {
			@Override
			public void success(TweetList response, Response rawResponse) {
					mBus.post(new SearchTweetsEventOk(response));
			}

			@Override
			public void failure(RetrofitError error) {
				Log.e(TAG, error.toString(), error);
				mBus.post(new SearchTweetsEventFailed());
			}
		});
	}

	@Subscribe
	public void onGetToken(TwitterGetTokenEvent event) {
		try {
			mApi.getToken("Basic " + getBase64String(ApiConstants.BEARER_TOKEN_CREDENTIALS), "client_credentials", new Callback<TwitterTokenType>() {
				@Override
				public void success(TwitterTokenType token, Response response) {
					PrefsController.setAccessToken(TwitterSearchApplication.getAppContext(), token.accessToken);
					PrefsController.setTokenType(TwitterSearchApplication.getAppContext(), token.tokenType);
					mBus.post(new TwitterGetTokenEventOk());
				}

				@Override
				public void failure(RetrofitError error) {
					Log.e(TAG, error.toString(), error);
					mBus.post(new TwitterGetTokenEventFailed());
				}
			});
		} catch (UnsupportedEncodingException e) {
			Log.e(TAG, e.toString(), e);
		}
	}
}
