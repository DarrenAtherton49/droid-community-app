package com.darrenatherton.droidcommunity.reddit.service

import android.util.Log
import com.darrenatherton.droidcommunity.reddit.entity.RedditObject
import com.darrenatherton.droidcommunity.reddit.entity.RedditObjectWrapper
import com.google.gson.*
import java.lang.reflect.Type

class RedditObjectDeserializer : JsonDeserializer<RedditObject> {

    val TAG: String = RedditObjectDeserializer::class.java.simpleName

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): RedditObject? {
        if (!json.isJsonObject) {
            return null
        }

        try {
            val wrapper: RedditObjectWrapper = Gson().fromJson(json, RedditObjectWrapper::class.java)
            return context.deserialize(wrapper.data, wrapper.kind.derivedClass)
        } catch (e: JsonParseException) {
            Log.e(TAG, "Failed to deserialize", e)
            return null
        }
    }
}