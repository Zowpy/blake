package net.lyragames.blake.jedis

import net.lyragames.blake.Blake
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class JedisHandler(
    private val blake: Blake
)
{

    lateinit var jedisPool: JedisPool

    init {
        connect()
    }

    private fun connect() {
        try {
            jedisPool = JedisPool(blake.credential.host, blake.credential.port)
            val jedis = jedisPool.resource

            if (blake.credential.authentication) jedis.auth(blake.credential.password)

            Thread { jedis.subscribe(JedisListener(blake), blake.channel) }.start()

            jedis.connect()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun publish(string: String) {
        var jedis: Jedis? = null
        try {
            jedis = jedisPool.resource

            if (blake.credential.authentication) jedis.auth(blake.credential.password)

            jedis.publish(blake.channel, string)
        }finally {
            if (jedis != null) {
                jedis.close()
            }
        }
    }
}