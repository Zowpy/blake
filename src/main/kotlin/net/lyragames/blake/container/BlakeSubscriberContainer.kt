package net.lyragames.blake.container

import net.lyragames.blake.annotation.IncomingMessage
import net.lyragames.blake.entry.BlakeEntry
import java.lang.reflect.Method


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class BlakeSubscriberContainer
{

    val messages: MutableMap<String, BlakeEntry<Any, Method>> = mutableMapOf()

    fun load(subscriber: Any) {
        for (method in subscriber::class.java.declaredMethods) {
            if (method.isAnnotationPresent(IncomingMessage::class.java)) {
                val message = method.getAnnotation(IncomingMessage::class.java)

                messages[message.value] = BlakeEntry(subscriber, method)
            }
        }
    }

}