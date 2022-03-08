package net.lyragames.blake

import net.lyragames.blake.container.BlakeSubscriberContainer
import net.lyragames.blake.credential.RedisCredential


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class BlakeBuilder {

    private var redisCredential: RedisCredential? = null
    private var channel: String = ""
    private val blakeSubscriberContainer = BlakeSubscriberContainer()

    fun setCredentials(redisCredential: RedisCredential): BlakeBuilder {
        this.redisCredential = redisCredential

        return this
    }

    fun setChannel(channel: String): BlakeBuilder {
        this.channel = channel

        return this
    }

    fun register(subscriber: Any): BlakeBuilder {
        blakeSubscriberContainer.load(subscriber)

        return this
    }

    fun build(): Blake {
        return Blake(RedisCredential("127.0.0.1", 6379, false, ""), channel, blakeSubscriberContainer)
    }
}