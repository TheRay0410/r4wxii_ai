package com.r4wxii

class MarkovChain(private val blockList: List<Triple<String, String, String>>) {
    companion object {
        const val LENGTH_LIMIT = 140
    }

    fun genText(): String {
        val (text: String, block: Triple<String, String, String>) = findStartBlock()
        return markovChain(text, block)
    }
    private tailrec fun markovChain(text: String, block: Triple<String, String, String>): String {
        val remaining = LENGTH_LIMIT - text.length

        if (block.third == "_END_") return text + nextPhrase(remaining, block)
        return markovChain(text + nextPhrase(remaining, block), findBlock(block.third))
    }
    private fun nextPhrase(remaining: Int, block: Triple<String, String, String>): String {
        return when {
            remaining >= (block.first + block.second).length -> {
                block.first + block.second
            }
            remaining >= block.first.length -> {
                block.first
            }
            else -> {
                ""
            }
        }
    }
    private fun findStartBlock(): Pair<String, Triple<String, String, String>> {
        val startBlock = blockList.filter { it.first == "_START_" }.random()
        return Pair(startBlock.second, findBlock(startBlock.third))
    }
    private fun findBlock(firstWord: String): Triple<String, String, String> = blockList.filter { it.first == firstWord }.random()
}