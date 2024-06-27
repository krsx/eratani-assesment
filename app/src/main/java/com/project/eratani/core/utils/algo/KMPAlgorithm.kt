package com.project.eratani.core.utils.algo

class KMPAlgorithm {
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

    fun search(text: String, pattern: String): Boolean {
        val lps = computeLPSArray(pattern)
        var i = 0
        var j = 0

        while (i < text.length) {
            if (pattern[j].equals(text[i], ignoreCase = true)) {
                i++
                j++
            }
            if (j == pattern.length) {
                return true
            } else if (i < text.length && !pattern[j].equals(text[i], ignoreCase = true)) {
                if (j != 0) {
                    j = lps[j - 1]
                } else {
                    i++
                }
            }
        }
        return false
    }
}
