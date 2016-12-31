package com.darrenatherton.droidcommunity.reddit.mapper

import com.darrenatherton.droidcommunity.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.reddit.entity.RedditListing
import com.darrenatherton.droidcommunity.reddit.entity.RedditResponse
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
        return listingResponse.data.children
                .map { it as RedditLink }
                .filter { !it.stickied } //todo instead of removing, store them and use in first item carousel?
    }
}