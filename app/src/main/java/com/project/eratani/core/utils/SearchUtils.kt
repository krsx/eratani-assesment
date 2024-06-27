package com.project.eratani.core.utils

import com.project.eratani.core.utils.algo.KMPAlgorithm

object SearchUtils {
    fun searchKeywords(
        keywords: String, data: List<String>
    ): List<String> {
        val keywordList = keywords.split(" ").map { it.trim() }.filter { it.isNotEmpty() }
        val foundData = mutableListOf<String>()

        for (item in data) {
            var found = false
            for (keyword in keywordList) {
                if (kmpSearch(item, keyword).isNotEmpty()) {
                    found = true
                    break
                }
            }
            if (found) {
                foundData.add(item)
            }
        }

        return foundData
    }

    private fun kmpSearch(text: String, pattern: String): List<Int> {
        val indices = mutableListOf<Int>()
        val lps = computeLPSArray(pattern)
        var i = 0 // index for text
        var j = 0 // index for pattern

        while (i < text.length) {
            if (pattern[j].equals(text[i], ignoreCase = true)) {
                i++
                j++
            }
            if (j == pattern.length) {
                indices.add(i - j)
                j = lps[j - 1]
            } else if (i < text.length && !pattern[j].equals(text[i], ignoreCase = true)) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
        return indices
    }

    private fun computeLPSArray(pattern: String): IntArray {
        val lps = IntArray(pattern.length)
        var length = 0
        var i = 1
        lps[0] = 0

        while (i < pattern.length) {
            if (pattern[i].equals(pattern[length], ignoreCase = true)) {
                length++
                lps[i] = length
                i++
            } else {
                if (length != 0) {
                    length = lps[length - 1]
                } else {
                    lps[i] = 0
                    i++
                }
            }
        }
        return lps
    }
}