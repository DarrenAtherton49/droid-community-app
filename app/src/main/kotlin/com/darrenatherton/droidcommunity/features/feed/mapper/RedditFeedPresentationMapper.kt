package com.darrenatherton.droidcommunity.features.feed.mapper

import com.darrenatherton.droidcommunity.common.injection.scope.PerScreen
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewGroupItem
import com.darrenatherton.droidcommunity.features.feed.entity.FeedViewSingleItem
import com.darrenatherton.droidcommunity.reddit.entity.RedditListingItem
import com.darrenatherton.droidcommunity.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.reddit.entity.Subscription
import com.darrenatherton.droidcommunity.twitter.entity.TweetItem
import javax.inject.Inject

/**
 * Class used to transform Reddit objects from domain layer to presentation layer.
 */
@PerScreen
class RedditFeedPresentationMapper @Inject constructor() {

    internal fun convertSubscriptionsToFeedItemGroups(subscriptions: List<Subscription>): List<FeedViewGroupItem> {
        return subscriptions.map { convertSubscriptionToFeedItemGroup(it) }
    }

    private fun convertSubscriptionToFeedItemGroup(subscription: Subscription): FeedViewGroupItem {
        return when (subscription) {
            is Subscription.Reddit -> {
                FeedViewGroupItem.Reddit(subscription.title, subscription.subreddit)
            }
            is Subscription.Twitter -> {
                FeedViewGroupItem.Twitter(subscription.title)
            }
        }
    }

    internal fun convertRedditListingItemsToFeedTile(items: List<RedditListingItem>): List<FeedViewSingleItem.Reddit> {
        return items.map { item -> convertRedditListingItemToViewItem(item) }
    }

    private fun convertRedditListingItemToViewItem(item: RedditListingItem): FeedViewSingleItem.Reddit = with(item) {
        FeedViewSingleItem.Reddit(
                title,
                Subreddit.getReadableLabelFromSuffix(subreddit),
                author,
                submitted,
                numComments
        )
    }

    private fun convertTweetItemToViewItem(item: TweetItem): FeedViewSingleItem.Twitter = with(item) {
        FeedViewSingleItem.Twitter(
                message
        )
    }
}