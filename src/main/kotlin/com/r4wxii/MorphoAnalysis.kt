package com.r4wxii

import org.chasen.mecab.Tagger
import org.chasen.mecab.Node

class MorphoAnalysis {
    init {
        System.loadLibrary("MeCab")
    }
    val tagger = Tagger("-d /usr/local/lib/mecab/dic/mecab-ipadic-neologd")
    var blockList = mutableListOf<Triple<String, String, String>>()
    fun Node.isNull(): Boolean = this.next == null
    fun text2morphene(text: String): List<String> {
        tagger.parse(text)
        var node = tagger.parseToNode(text).next
        var morphene: MutableList<String> = mutableListOf()
        while(!node.isNull()) {
            morphene.add(node.surface)
            node = node.next
        }
        return morphene
    }
    fun List<String>.isOnly(): Boolean = this.count() == 1
    fun makeBlock(blockList: MutableList<Triple<String, String, String>>, morphene: List<String>) {
        blockList.add(if(morphene.isOnly()) {
            Triple("_START_", morphene[0], "_END_")
        } else {
            Triple("_START_", morphene[0], morphene[1])
        })
        if(morphene.isOnly()) {
            for (index in morphene.indices) {
                if (index < morphene.lastIndex - 1) {
                    blockList.add(Triple(morphene[index], morphene[index + 1], morphene[index + 2]))
                } else {
                    blockList.add(Triple(morphene[index], morphene[index + 1], "_END_"))
                    break
                }
            }
        }
    }
}