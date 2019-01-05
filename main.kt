import jp.nephy.penicillin.*

fun main() {
  val client = PenicillinClient {
      account {
          application("ConsumerKey", "ConsumerSecret")
          token("AccessToken", "AccessTokenSecret")
      }
  }
}