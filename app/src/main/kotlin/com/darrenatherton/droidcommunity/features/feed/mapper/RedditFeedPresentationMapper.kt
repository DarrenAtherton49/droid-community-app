package com.darrenatherton.droidcommunity.features.feed.mapper

import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.data.reddit.Subreddit
import com.darrenatherton.droidcommunity.domain.reddit.RedditListingItem
import com.darrenatherton.droidcommunity.domain.subscription.Subscription
import com.darrenatherton.droidcommunity.features.feed.entity.FeedSingleViewItem
import com.darrenatherton.droidcommunity.features.feed.entity.SubscriptionViewItem
import javax.inject.Inject

/**
 * Class used to transform Reddit objects from domain layer to presentation layer.
 */
@PerScreen
class RedditFeedPresentationMapper @Inject constructor() {

    internal fun convertSubscriptionsDomainToView(subscriptions: List<Subscription>): List<SubscriptionViewItem> {
        return subscriptions
                .map { convertSubscriptionDomainToView(it) }
                .sortedBy { it.order }
    }

    private fun convertSubscriptionDomainToView(subscription: Subscription) = with(subscription) {
        when (subscription) {
            is Subscription.Reddit -> {
                SubscriptionViewItem.Reddit(key, title, order)
            }
            is Subscription.Twitter -> {
                SubscriptionViewItem.Twitter(key, title, order)
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
}