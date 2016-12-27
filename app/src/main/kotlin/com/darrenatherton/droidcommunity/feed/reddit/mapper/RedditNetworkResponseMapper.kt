package com.darrenatherton.droidcommunity.feed.reddit.mapper

import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditListing
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditResponse
import java.util.*
import javax.inject.Inject

/**
 * Class used to transform data from Reddit Service responses to valid data layer objects.
 */
class RedditNetworkResponseMapper @Inject constructor() {

    /**
     *
     * @param listingResponse A reddit service response containing a listing
     * @return A list of {@link RedditListing}
     */
    fun transformListingResponseToLinkCollection(listingResponse: RedditResponse<RedditListing>): List<RedditLink> {
        val links: MutableList<RedditLink> = ArrayList()
        listingResponse.data.children.forEach {
            if (it is RedditLink) {
                links.add(it)
            }
        }
        return links
    }
}