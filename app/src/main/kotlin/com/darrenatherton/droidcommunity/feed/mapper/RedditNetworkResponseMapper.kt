package com.darrenatherton.droidcommunity.feed.mapper

import com.darrenatherton.droidcommunity.feed.reddit.data.RedditLink
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditListing
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditResponse
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform data from Reddit Service responses to valid data layer objects.
 */
@Singleton
class RedditNetworkResponseMapper @Inject constructor() {

    /**
     *
     * @param listingResponse A reddit service response containing a listing
     * @return A list of {@link RedditListing}
     */
    internal fun convertLinksResponseToData(listingResponse: RedditResponse<RedditListing>): List<RedditLink> {
        return listingResponse.data.children.map { it as RedditLink }
    }
}