package net.lyragames.blake.jedis

import com.google.gson.JsonParser
import net.lyragames.blake.Blake
import net.lyragames.blake.annotation.IncomingMessage
import net.lyragames.blake.context.BlakeThreadContext
import redis.clients.jedis.JedisPubSub


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class JedisListener(private val blake: Blake): JedisPubSub() {

    override fun onMessage(channel: String, message: String) {

        if (!blake.channel.equals(channel, false)) return

        val args = message.split("//")

        if (args.size != 2) return

        val command = blake.subscriberContainer.messages[args[0]] ?: return

        val data = JsonParser.parseString(args[1])

        if (data.isJsonNull) return

        command.value.isAccessible = true

        val incomingMessage = command.value.getAnnotation(IncomingMessage::class.java)

        if (incomingMessage.threadContext == BlakeThreadContext.SYNC) {
            command.value.invoke(command.key, data.asJsonObject)
        }else if (incomingMessage.threadContext == BlakeThreadContext.ASYNC) {
            blake.thread.execute { command.value.invoke(command.key, data.asJsonObject) }
        }

    }
}