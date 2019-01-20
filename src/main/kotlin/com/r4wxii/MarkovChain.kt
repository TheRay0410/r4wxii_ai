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
        println(blockList.filter { it.first == firstWord })
        val block = blockList.filter { it.first == firstWord }[Random.nextInt(blockList.filter { it.first == firstWord }.indices)]
        return block
    }
    fun makeBlock(blockList: MutableList<Triple<String, String, String>>, morphene: List<String>) {
        blockList.add(Triple("_START_", morphene[0], morphene[1]))
        for(index in morphene.indices) {
            if(index < morphene.lastIndex - 1) {
                blockList.add(Triple(morphene[index], morphene[index + 1], morphene[index + 2]))
            } else {
                blockList.add(Triple(morphene[index], morphene[index + 1], "_END_"))
                break
            }
        }
    }
}