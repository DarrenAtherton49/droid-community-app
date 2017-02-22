package com.darrenatherton.droidcommunity.data.reddit.mapper

import com.darrenatherton.droidcommunity.data.reddit.RedditLink
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem

/**
 * Functions used to transform Reddit objects from data layer to domain layer.
 */

internal fun convertLinksDataToDomain(items: List<RedditLink>): List<RedditListingItem> {
    return items.map { link -> convertLinkDataItemToDomain(link) }
}

private fun convertLinkDataItemToDomain(link: RedditLink): RedditListingItem = with(link) {
    RedditListingItem(title, subreddit, author, "replace me", Integer.toString(num_comments))
}