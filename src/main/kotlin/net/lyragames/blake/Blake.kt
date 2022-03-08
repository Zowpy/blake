package net.lyragames.blake

import com.google.gson.JsonObject
import net.lyragames.blake.container.BlakeSubscriberContainer
import net.lyragames.blake.credential.RedisCredential
import net.lyragames.blake.jedis.JedisHandler
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class Blake(
    val credential: RedisCredential = RedisCredential("127.0.0.1", 6379, false, ""),
    val channel: String = "",
    var subscriberContainer: BlakeSubscriberContainer
)
{

    val thread: Executor = Executors.newSingleThreadExecutor()
    private var jedisHandler: JedisHandler = JedisHandler(this)

    private fun write(payload: String, data: String) {
        jedisHandler.publish("${payload}//${data}")
    }

    fun write(payload: String, data: JsonObject) {
        write(payload, data.toString())
    }

    fun write(payload: String) {
        write(payload, "{}")
    }
}