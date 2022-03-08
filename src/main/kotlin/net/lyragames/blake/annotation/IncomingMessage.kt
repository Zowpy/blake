package net.lyragames.blake.annotation

import net.lyragames.blake.context.BlakeThreadContext

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IncomingMessage(
    val value: String,
    val threadContext: BlakeThreadContext = BlakeThreadContext.SYNC
)
