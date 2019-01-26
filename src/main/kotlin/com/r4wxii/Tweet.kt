package com.r4wxii

import jp.nephy.penicillin.PenicillinClient
import jp.nephy.penicillin.core.emulation.EmulationMode
import jp.nephy.penicillin.extensions.complete
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

class Tweet {
    val key = KeyData()
    val morphoAnalysis = MorphoAnalysis()
    val markovChain = MarkovChain()
    val client = PenicillinClient {
        account {
            application(key.ConsumerKey, key.ConsumerSecret)
            token(key.AccessToken, key.AccessTokenSecret)
        }
        emulationMode = EmulationMode.TwitterForiPhone

        maxRetries = 5
        retry(1, TimeUnit.SECONDS)
    }
    suspend fun getTweet(user: String) {
        client.timeline.user(screenName = user, count = 200,includeRTs = false,excludeReplies = true).await().forEach { status ->
            morphoAnalysis.makeBlock(morphoAnalysis.blockList,morphoAnalysis.text2morphene(status.text.replace("""http(s)?://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?|\s#(w*[一-龠_ぁ-ん_ァ-ヴーｦ-ﾟａ-ｚＡ-Ｚa-zA-Z0-9]+|[a-zA-Z0-9_]+|[a-zA-Z0-9_]w*)""".toRegex(),"")))
        }
    }
    suspend fun post() {
        client.statuses.update(status = markovChain.genText(morphoAnalysis.blockList)).await()
    }
}