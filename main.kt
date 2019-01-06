package com.r4wxii.r4wxii_ai

import jp.nephy.penicillin.PenicillinClient
import jp.nephy.penicillin.core.emulation.EmulationMode
import java.util.concurrent.TimeUnit

fun main() {
  val client = PenicillinClient {
    account {
        application(System.getenv("consumerKey"), System.getenv("consumerSecret"))
        token(System.getenv("accessToken"), System.getenv("accessTokenSecret"))
    } 
    emulationMode = EmulationMode.TwitterForiPhone  // Twitter for iPhone 準拠のリクエストを送る

    maxRetries = 5  // API エラー発生時のリトライを最大で5回まで行う
    retry(1, TimeUnit.SECONDS)  // API エラー発生時のリトライに1秒間隔をあける
  }
}
