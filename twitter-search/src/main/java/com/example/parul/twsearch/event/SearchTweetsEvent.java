package com.example.parul.twsearch.event;

public class SearchTweetsEvent {

	public final String hashtag;
	public final String twitterToken;

	public SearchTweetsEvent(String twitterToken, String hashtag) {
		this.hashtag = hashtag;
		this.twitterToken = twitterToken;
	}


}
