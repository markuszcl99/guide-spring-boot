package com.markus.accumulation.core;

import com.markus.accumulation.core.cache.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: markus
 * @date: 2024/4/20 10:25 PM
 * @Description:
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisClientTest {

    @Test
    public void testRedisSet() {
        RedisClient.setStr("markus", "markus");
    }

    @Test
    public void testRedisDel() {
        RedisClient.del("markus");
    }


}
