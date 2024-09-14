package org.ywk.config;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.core.types.Expiration;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class RedisLock implements AutoCloseable{

    private RedisTemplate redisTemplate;

    private String key;

    // 避免误删锁
    private String value;

    // 单位 秒
    private int expireTime;


    public RedisLock(RedisTemplate redisTemplate, String key, int expireTime) {
        this.redisTemplate = redisTemplate;
        this.key = key;
        this.expireTime = expireTime;
        this.value = UUID.randomUUID().toString();
    }

    public void close() throws Exception {
        unLock();
    }



    public boolean getLock() {

        RedisCallback<Boolean> redisCallback = connection -> {

            // 设置 NX
            RedisStringCommands.SetOption option = RedisStringCommands.SetOption.ifAbsent();

            // 设置过期时间
            Expiration expiration = Expiration.seconds(expireTime);

            // 序列化key
            byte[] redisKey = redisTemplate.getKeySerializer().serialize(key);

            // 序列化value
            byte[] redisValue = redisTemplate.getValueSerializer().serialize(value);

            // 执行setnx操作
            Boolean result = connection.set(redisKey, redisValue, expiration, option);
            return result;
        };

        Boolean lock = (Boolean) redisTemplate.execute(redisCallback);

        return lock;

    }



    public boolean unLock() {


        String script = "if redis.call(\"GET\", KEYS[1]) == ARGV[1] then\n" +
                "    return redis.call(\"DEL\", KEYS[1])\n" +
                "else\n" +
                "    return 0\n" +
                "end";

        RedisScript<Boolean> redisScript = RedisScript.of(script, Boolean.class);

        List<String> keys = Arrays.asList(key);

        Boolean result = (Boolean) redisTemplate.execute(redisScript, keys, value);

        return result;


    }


}
