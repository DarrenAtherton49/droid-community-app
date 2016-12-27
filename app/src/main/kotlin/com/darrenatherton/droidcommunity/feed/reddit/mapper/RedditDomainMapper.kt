package com.darrenatherton.droidcommunity.feed.reddit.mapper

import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditFeedItem
import com.darrenatherton.droidcommunity.feed.reddit.entity.RedditLink
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform Reddit objects from data layer to domain layer.
 */
@Singleton
class RedditDomainMapper @Inject constructor() {

    internal fun convertLinksDataToDomain(linksList: List<RedditLink>): List<RedditFeedItem> {
        return linksList.map { link -> convertLinkDataItemToDomain(link) }
    }

    private fun convertLinkDataItemToDomain(link: RedditLink): RedditFeedItem = with(link) {
        RedditFeedItem(title)
    }
}