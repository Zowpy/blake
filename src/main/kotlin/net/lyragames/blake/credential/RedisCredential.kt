package net.lyragames.blake.credential


/**
 * This Project is property of Zowpy © 2022
 * Redistribution of this Project is not allowed
 *
 * @author Zowpy
 * Created: 3/8/2022
 * Project: blake
 */

class RedisCredential(
    var host: String,
    var port: Int,
    var authentication: Boolean,
    var password: String
)