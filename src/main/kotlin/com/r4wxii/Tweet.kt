package com.r4wxii

import jp.nephy.penicillin.PenicillinClient
import jp.nephy.penicillin.core.emulation.EmulationMode
import jp.nephy.penicillin.extensions.complete
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit


class Tweet {
    val key = KeyData()
    val client = PenicillinClient {
        account {
            application(key.ConsumerKey, key.ConsumerSecret)
            token(key.AccessToken, key.AccessTokenSecret)
        }
        emulationMode = EmulationMode.TwitterForiPhone

        maxRetries = 5
        retry(1, TimeUnit.SECONDS)
    }
    fun getTweet(user: String) {
        runBlocking  {
            client.timeline.user(screenName = user, count = 30,includeRTs = false,excludeReplies = true).await().forEach { status ->
                // prints status text.
                println(status)
            }
        }
    }
    fun post(text: String = "テスト") {
        client.statuses.update(status = text).complete()
    }
}