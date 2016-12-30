package com.darrenatherton.droidcommunity.feed.reddit.mapper

import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedItem
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform Reddit objects from data layer to domain layer.
 */
@Singleton
class RedditDomainMapper @Inject constructor() {

    internal fun convertLinksDataToDomain(items: List<RedditLink>): List<FeedItem.Reddit> {
        return items.map { link -> convertLinkDataItemToDomain(link) }
    }

    private fun convertLinkDataItemToDomain(link: RedditLink): FeedItem.Reddit = with(link) {
        FeedItem.Reddit(title, subreddit, author, "replace me", Integer.toString(num_comments))
    }
}