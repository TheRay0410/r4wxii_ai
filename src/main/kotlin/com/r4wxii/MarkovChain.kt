package com.r4wxii

import kotlin.random.Random
import kotlin.random.nextInt

class MarkovChain {
    fun genText(blockList: List<Triple<String, String, String>>): String {
        var (text: String, block: Triple<String, String, String>) = findStartBlock(blockList)
        do {
            block = findBlock(blockList, block.third)
            text += block.first + block.second
        } while(text.length < 140 && block.third !=  "_END_")
        return text
    }
    fun findStartBlock(blockList: List<Triple<String, String, String>>): Pair<String, Triple<String, String, String>> {
        val startBlock = blockList.filter { it.first == "_START_" }[Random.nextInt(blockList.filter { it.first == "_START_" }.indices)]
        val initText: String = startBlock.second + startBlock.third
        return Pair(initText, startBlock)
    }
    fun findBlock(blockList: List<Triple<String, String, String>>,firstWord: String): Triple<String, String, String> {
        val block = blockList.filter { it.first == firstWord }[Random.nextInt(blockList.filter { it.first == firstWord }.indices)]
        return block
    }
}