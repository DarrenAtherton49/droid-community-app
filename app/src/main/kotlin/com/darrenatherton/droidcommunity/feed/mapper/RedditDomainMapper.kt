package com.darrenatherton.droidcommunity.feed.mapper

import com.darrenatherton.droidcommunity.feed.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.reddit.data.RedditLink
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
        FeedItem.Reddit(title)
    }
}