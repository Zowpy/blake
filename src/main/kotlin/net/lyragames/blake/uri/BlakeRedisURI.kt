package net.lyragames.blake.uri

import net.lyragames.blake.credential.RedisCredential
import javax.print.attribute.IntegerSyntax


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class BlakeRedisURI(private val uri: String) {

    fun toCredential(): RedisCredential {

        val credentials = uri.replace("redis://", "").split(":")

        // redis://127.0.0.1:6467/

        val host = credentials[0]
        val port = credentials[1].toInt()

        val newURI = uri.replace("redis://${host}:${port}/", "")

        if (newURI.isEmpty()) {
            return RedisCredential(host, port, false, "")
        }

        return RedisCredential(host, port, true, newURI)
    }
}