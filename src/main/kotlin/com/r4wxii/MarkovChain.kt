package com.r4wxii

class MarkovChain {
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