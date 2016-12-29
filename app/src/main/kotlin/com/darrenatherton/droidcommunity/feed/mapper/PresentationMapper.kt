package com.darrenatherton.droidcommunity.feed.mapper

import com.darrenatherton.droidcommunity.feed.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.entity.FeedViewItem
import com.darrenatherton.droidcommunity.feed.reddit.data.Subreddit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform Reddit objects from domain layer to presentation layer.
 */
@Singleton
class PresentationMapper @Inject constructor() {

    internal fun convertFeedItemsDomainToPresentation(items: List<FeedItem>): List<FeedViewItem> {
        return items.map { item -> convertFeedItemToFeedViewItem(item) }
    }

    private fun convertFeedItemToFeedViewItem(item: FeedItem): FeedViewItem {
        return when (item) {
            is FeedItem.Reddit -> convertRedditItemToViewItem(item)
        }
    }

    private fun convertRedditItemToViewItem(item: FeedItem.Reddit): FeedViewItem.Reddit = with(item) {
        FeedViewItem.Reddit(title, Subreddit.getReadableLabelFromSuffix(subreddit))
    }
}