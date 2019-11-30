package com.r4wxii

class MarkovChain {
    fun genText(blockList: List<Triple<String, String, String>>): String {
        var (text: String, block: Triple<String, String, String>) = findStartBlock(blockList)
        do {
            block = findBlock(blockList, block.third)
            text += block.first + block.second
        } while(text.length < 140 && block.third !=  "_END_")
        return text
    }
    private fun findStartBlock(blockList: List<Triple<String, String, String>>): Pair<String, Triple<String, String, String>> {
        val startBlock = blockList.filter { it.first == "_START_" }.random()
        return Pair(startBlock.second, startBlock)
    }
    private fun findBlock(blockList: List<Triple<String, String, String>>,firstWord: String): Triple<String, String, String> = blockList.filter { it.first == firstWord }.random()
}