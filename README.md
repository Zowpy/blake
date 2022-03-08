# blake
 
Simple Jedis API coded in kotlin.

## Documentation

# Setting Up Blake

# Credentials
```java
val redisCredentials = RedisCredential("127.0.0.1", 6379, false, "") # normal mode
```

The Jedis API supports URIs 

URI format: "redis://host:port/password"
 
Using URI mode:
```java
 val redisCredentials = BlakeRedisURI("redis://127.0.0.1:6379/").toCredential()
 ```
 
Creating the Blake instance

```java
val blake = BlakeBuilder()
  .setCredentials(redisCredentials)
  .setChannel("test") # redis channel name
  .register(this) # registers the given instance
  .build() # builds the blake instance
```

# Using Blake

```java
@IncomingMessage("test", BlakeThreadContext.ASYNC) # first parameter is the payload, the second option is the thread context, you can choose between ASYNC & SYNC
fun test() {
  println("test")
}
```
