package com.r4wxii

import jp.nephy.penicillin.PenicillinClient
import kotlinx.coroutines.*

fun main() {
    val key = KeyData()
    val client = PenicillinClient {
        account {
            application(key.ConsumerKey, key.ConsumerSecret)
            token(key.AccessToken, key.AccessTokenSecret)
        }
    }
    runBlocking  {
        client.timeline.user(screenName = "TheRay_Misc", count = 100).await().forEach { status ->
            // prints status text.
            println(status.text)
        }
    }
    client.close()
}
