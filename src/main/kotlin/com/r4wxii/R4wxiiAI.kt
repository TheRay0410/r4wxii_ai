package com.r4wxii

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val tweet = Tweet()
    tweet.getTweet("TheRay0410")
    tweet.getTweet("TheRay_Misc")
    tweet.post()
}
