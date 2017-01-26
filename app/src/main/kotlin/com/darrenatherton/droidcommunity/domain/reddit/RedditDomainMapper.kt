package com.darrenatherton.droidcommunity.domain.reddit

import com.darrenatherton.droidcommunity.data.reddit.RedditLink
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform Reddit objects from data layer to domain layer.
 */
@Singleton
class RedditDomainMapper @Inject constructor() {

    internal fun convertLinksDataToDomain(items: List<RedditLink>): List<RedditListingItem> {
        return items.map { link -> convertLinkDataItemToDomain(link) }
    }

    private fun convertLinkDataItemToDomain(link: RedditLink): RedditListingItem = with(link) {
        RedditListingItem(title, subreddit, author, "replace me", Integer.toString(num_comments))
    }
}