package com.darrenatherton.droidcommunity.features.feed.mapper

import com.darrenatherton.droidcommunity.data.reddit.Subreddit
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.domain.subscription.Subscription
import com.darrenatherton.droidcommunity.features.feed.FeedSingleViewItem
import com.darrenatherton.droidcommunity.features.feed.SubscriptionFeedItem

/**
 * Functions used to transform Reddit objects from domain layer to presentation layer for the feed view.
 */
internal fun convertFeedSubscriptionsDomainToView(subscriptions: List<Subscription>): List<SubscriptionFeedItem> {
    return subscriptions
            .map { convertSubscriptionDomainToView(it) }
            .sortedBy { it.order }
}

private fun convertSubscriptionDomainToView(subscription: Subscription) = with(subscription) {
    when (subscription) {
        is Subscription.Reddit -> {
            SubscriptionFeedItem.Reddit(key, title, order)
        }
        is Subscription.Twitter -> {
            SubscriptionFeedItem.Twitter(key, title, order)
        }
    }
}

internal fun convertRedditListingItemsToFeedTile(items: List<RedditListingItem>): List<FeedSingleViewItem.Reddit> {
    return items.map { item -> convertRedditListingItemToViewItem(item) }
}

private fun convertRedditListingItemToViewItem(item: RedditListingItem): FeedSingleViewItem.Reddit = with(item) {
    FeedSingleViewItem.Reddit(
            title,
            Subreddit.getReadableLabelFromSuffix(subreddit),
            author,
            submitted,
            numComments
    )
}