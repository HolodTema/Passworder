package com.terabyte.passworder.util

import java.security.MessageDigest

class HashManager {

    fun hash(input: String): String {
        return hashInSHA256(input)
    }

    private fun hashInSHA256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return digest.joinToString()
    }
}