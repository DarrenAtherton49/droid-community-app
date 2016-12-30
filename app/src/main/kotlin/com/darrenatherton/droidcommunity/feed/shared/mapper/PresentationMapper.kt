package com.darrenatherton.droidcommunity.feed.shared.mapper

import com.darrenatherton.droidcommunity.feed.reddit.entity.Subreddit
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedItem
import com.darrenatherton.droidcommunity.feed.shared.entity.FeedViewItem
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class used to transform Reddit objects from domain layer to presentation layer.
 */
@Singleton
class PresentationMapper @Inject constructor() {

    internal fun convertAndSortForPresentation(items: List<FeedItem>): List<FeedViewItem> {
        val redditList = items.filter { it is FeedItem.Reddit }
        val twitterList = items.filter { it is FeedItem.Twitter }
        val sortedList: MutableList<FeedViewItem> = ArrayList()

        //todo iterate through reddit and twitter lists. For each 1 twitter item added to sorted list,
        // add 2 reddit items after. At the same time call the 'convert' functions below to convert items
        // to presentation layer

        return sortedList.toList()
    }

    private fun convertRedditItemToViewItem(item: FeedItem.Reddit): FeedViewItem.Reddit = with(item) {
        FeedViewItem.Reddit(
                title,
                Subreddit.getReadableLabelFromSuffix(subreddit),
                author,
                submitted,
                numComments
        )
    }

    private fun convertTwitterItemToViewItem(item: FeedItem.Twitter): FeedViewItem.Twitter = with(item) {
        FeedViewItem.Twitter(
                title
        )
    }
}