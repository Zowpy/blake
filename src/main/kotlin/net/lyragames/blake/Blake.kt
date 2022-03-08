package net.lyragames.blake

import net.lyragames.blake.container.BlakeSubscriberContainer
import net.lyragames.blake.credential.RedisCredential
import net.lyragames.blake.jedis.JedisHandler
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

    val thread = Executors.newSingleThreadExecutor()
    var jedisHandler: JedisHandler = JedisHandler(this)
}