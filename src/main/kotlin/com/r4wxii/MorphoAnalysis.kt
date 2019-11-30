package com.r4wxii

import org.chasen.mecab.Tagger
import org.chasen.mecab.Node

class MorphoAnalysis {
    init {
        System.loadLibrary("MeCab")
    }
    val tagger = Tagger("-d /usr/local/lib/mecab/dic/mecab-ipadic-neologd")
    var blockList = mutableListOf<Triple<String, String, String>>()
    fun text2morpheme(text: String): List<String> {
        if(text == "") return emptyList()
        tagger.parse(text)
        var node = tagger.parseToNode(text).next
        var morpheme: MutableList<String> = mutableListOf()
        while(node.run { next != null }) {
            morpheme.add(node.surface)
            node = node.next
        }
        return morpheme
    }
    fun makeBlock(blockList: MutableList<Triple<String, String, String>>, morpheme: List<String>) {
        when(morpheme.count()) {
            0 -> Unit
            1 -> blockList.add(Triple("_START_", morpheme[0], "_END_"))
            else -> blockList.add(Triple("_START_", morpheme[0], morpheme[1]))
        }
        if(morpheme.run { count() > 1}) {
            for (index in morpheme.indices) {
                if (index < morpheme.lastIndex - 1) {
                    blockList.add(Triple(morpheme[index], morpheme[index + 1], morpheme[index + 2]))
                } else {
                    blockList.add(Triple(morpheme[index], morpheme[index + 1], "_END_"))
                    break
                }
            }
        }
    }
}