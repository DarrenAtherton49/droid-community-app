package com.darrenatherton.droidcommunity.reddit.entity

import com.darrenatherton.droidcommunity.common.util.emptyString
import com.google.gson.JsonElement

data class RedditResponse<out T>(val data: T)

data class RedditObjectWrapper(val kind: RedditType, val data: JsonElement)

enum class RedditType(val derivedClass: Class<*>) {
    t1(RedditComment::class.java),
    t3(RedditLink::class.java),
    Listing(RedditListing::class.java)
}

open class RedditObject

class RedditListing(
        val children: List<RedditObject>,
        val after: String?,
        val before: String?,
        val modhash: String
)

open class RedditSubmission(
        val author: String = emptyString,
        val title: String = emptyString,
        val num_comments: Int = 0,
        val created: Long = 0,
        val thumbnail: String = emptyString,
        val url: String = emptyString
) : RedditObject()

class RedditLink(
        val domain: String,
        val subreddit: String,
        val selftext_html: String,
        val selftext: String,
        val link_flair_text: String,
        val clicked: Boolean,
        val hidden: Boolean,
        val permalink: String,
        val stickied: Boolean,
        val visited: Boolean
) : RedditSubmission()

class RedditComment(
        val replies: RedditObject? = null,
        val subreddit_id: String = emptyString,
        val parent_id: String = emptyString,
        val controversiality: Int = 0,
        val body: String = emptyString,
        val body_html: String = emptyString,
        val link_id: String = emptyString,
        val depth: Int = 0
) : RedditSubmission()

enum class Subreddit(val label: String, val urlSuffix: String) {
    ANDROIDDEV("/r/AndroidDev", "androiddev"),
    ANDROID("/r/Android", "android"),
    ANDROIDAPPS("/r/AndroidApps", "androidapps"),
    KOTLIN("/r/Kotlin", "kotlin"),
    JAVA("/r/Java", "java"),
    MATERIAL_DESIGN("/r/MaterialDesign", "materialdesign");

    companion object {
        fun getReadableLabelFromSuffix(suffix: String): String {
            values().forEach {
                if (it.urlSuffix.contentEquals(suffix)) {
                    return it.label
                }
            }
            return "/r/$suffix"
        }
    }
}

enum class RedditFilterType(private val text: String) {
    HOT("hot"),
    NEW("new"),
    TOP("top");

    override fun toString() = text
}