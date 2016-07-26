package com.example.parul.twsearch.event;

import com.example.parul.twsearch.api.TweetList;


public class SearchTweetsEventOk {

	public final TweetList tweetsList;

	public SearchTweetsEventOk(TweetList tweets) {
		this.tweetsList = tweets;
	}

}
