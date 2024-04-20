package com.markus.accumulation.core.cache;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.markus.accumulation.core.util.JsonUtil;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.markus.accumulation.core.util.JsonUtil.toObj;

/**
 * @author: markus
 * @date: 2024/4/20 12:34 PM
 * @Description: Redis 客户端
 * @Blog: https://markuszhang.com
 * It's my honor to share what I've learned with you!
 */
public class RedisClient {

    private static final Charset CODE = StandardCharsets.UTF_8;
    private static final String KEY_PREFIX = "markus-";

    private static RedisTemplate<String, String> redisTemplate;

    public static void register(RedisTemplate<String, String> redisTemplate) {
        RedisClient.redisTemplate = redisTemplate;
    }

    public static void nullCheck(Object... args) {
        for (Object arg : args) {
            if (Objects.isNull(arg)) {
                throw new IllegalArgumentException("redis argument can not be null!");
            }
        }
    }

    public static <T> byte[] valBytes(T val) {
        if (val instanceof String) {
            return ((String) val).getBytes(CODE);
        } else {
            return JsonUtil.toStr(val).getBytes(CODE);
        }
    }

    /**
     * 生成缓存 key，二进制形式存储
     *
     * @param key
     * @return
     */
    public static byte[] keyBytes(String key) {
        nullCheck(key);
        key = KEY_PREFIX + key;
        return key.getBytes(CODE);
    }

    public static byte[][] keyBytes(List<String> keys) {
        byte[][] bytes = new byte[keys.size()][];
        int index = 0;
        for (String key : keys) {
            bytes[index++] = keyBytes(key);
        }
        return bytes;
    }

    /**
     * 查询缓存
     */
    public static String getStr(String key) {
        return redisTemplate.execute((RedisCallback<String>) connection -> {
            byte[] val = connection.get(keyBytes(key));
            return val == null ? null : new String(val);
        });
    }

    /**
     * 设置缓存
     */
    public static void setStr(String key, String value) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.set(keyBytes(key), valBytes(value));
            return null;
        });
    }

    /**
     * 删除缓存
     */
    public static void del(String key) {
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(keyBytes(key)));
    }

    /**
     * 设置缓存有效期
     * 时间单位 秒
     */
    public static void expire(String key, Long expire) {
        redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.expire(keyBytes(key), expire));
    }

    /**
     * 带过期时间的缓存写入
     */
    public static Boolean setStrWithExpire(String key, String value, Long expire) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setEx(keyBytes(key), expire, valBytes(value)));
    }

    /**
     * hash 结构数据 读取 hkey 下的所有内容
     */
    public static <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
        Map<byte[], byte[]> records = redisTemplate.execute((RedisCallback<Map<byte[], byte[]>>) connection -> connection.hGetAll(keyBytes(key)));
        if (records == null) {
            return Collections.emptyMap();
        }

        Map<String, T> result = Maps.newHashMapWithExpectedSize(records.size());
        for (Map.Entry<byte[], byte[]> entry : records.entrySet()) {
            if (entry.getKey() == null) {
                continue;
            }
            result.put(Arrays.toString(entry.getKey()), toObj(entry.getValue(), clazz));
        }
        return result;
    }

    /**
     * hash 结构数据 读取 hkey 下的 key 内容
     */
    public static <T> T hGet(String key, String field, Class<T> clazz) {
        return redisTemplate.execute((RedisCallback<T>) connection -> {
            byte[] record = connection.hGet(keyBytes(key), valBytes(field));
            if (record == null) {
                return null;
            }
            return toObj(record, clazz);
        });
    }

    /**
     * 自增
     */
    public static Long hIncr(String key, String field, Integer cnt) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.hIncrBy(keyBytes(key), valBytes(field), cnt));
    }

    /**
     * hDel 删除
     */
    public static Long hDel(String key, String field) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.hDel(keyBytes(key), valBytes(field)));
    }

    /**
     * hMSet 批量获取 hash 数据
     */
    public static <T> void hMSet(String key, Map<String, T> fields) {
        Map<byte[], byte[]> value = Maps.newHashMapWithExpectedSize(fields.size());
        for (Map.Entry<String, T> entry : fields.entrySet()) {
            value.put(valBytes(entry.getKey()), valBytes(entry.getValue()));
        }
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.hMSet(keyBytes(key), value);
            return null;
        });
    }

    /**
     * hMGet 批量获取 数据
     */
    public static <T> List<T> hMSet(String key, List<String> fields, Class<T> clazz) {
        return redisTemplate.execute((RedisCallback<List<T>>) connection -> {
            byte[][] fieldBytes = new byte[fields.size()][];
            List<byte[]> values = connection.hMGet(keyBytes(key), fieldBytes);
            return byteListToObjectList(values, clazz);
        });
    }

    /**
     * sIsMember 判断数据是否存在于 set 中
     */
    public static <T> Boolean sIsMember(String key, T value) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.sIsMember(keyBytes(key), valBytes(value)));
    }

    /**
     * sGetAll 获取 set 中所有的数据
     */
    public static <T> Set<T> sIsMember(String key, Class<T> clazz) {
        return redisTemplate.execute((RedisCallback<Set<T>>) connection -> {
            Set<byte[]> records = connection.sMembers(keyBytes(key));
            if (CollectionUtils.isEmpty(records)) {
                return Collections.emptySet();
            }
            Set<T> result = Sets.newHashSetWithExpectedSize(records.size());
            for (byte[] record : records) {
                result.add(JsonUtil.toObj(new String(record, CODE), clazz));
            }
            return result;
        });
    }

    /**
     * sAdd 添加 set 数据
     */
    public static <T> Long sAdd(String key, T... fields) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[][] fieldBytes = new byte[fields.length][];
            int index = 0;
            for (T field : fields) {
                fieldBytes[index++] = valBytes(field);
            }
            return connection.sAdd(keyBytes(key), fieldBytes);
        });
    }

    /**
     * sDel 删除 set 中的数据
     */
    public static <T> Long sDel(String key, T... fields) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[][] fieldBytes = new byte[fields.length][];
            int index = 0;
            for (T field : fields) {
                fieldBytes[index++] = valBytes(field);
            }
            return connection.sRem(keyBytes(key), fieldBytes);
        });
    }

    /**
     * lPush 在列表的头部插入一个或多个数据
     */
    public static <T> Long lPush(String key, T... fields) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[][] fieldBytes = new byte[fields.length][];
            int index = 0;
            for (T field : fields) {
                fieldBytes[index++] = valBytes(field);
            }
            return connection.lPush(keyBytes(key), fieldBytes);
        });
    }

    /**
     * rPush 在列表的尾部插入一个或多个数据
     */
    public static <T> Long rPush(String key, T... fields) {
        return redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[][] fieldBytes = new byte[fields.length][];
            int index = 0;
            for (T field : fields) {
                fieldBytes[index++] = valBytes(field);
            }
            return connection.rPush(keyBytes(key), fieldBytes);
        });
    }

    /**
     * lRange 返回列表中指定区间内的数据
     */
    public static <T> List<T> lRange(String key, long start, long end, Class<T> clazz) {
        return redisTemplate.execute((RedisCallback<List<T>>) connection -> {
            List<byte[]> records = connection.lRange(keyBytes(key), start, end);
            return byteListToObjectList(records, clazz);
        });
    }

    /**
     * lTrim 让列表只保持指定区间内的数据，不在指定区间之内的数据都将被删除
     */
    public static <T> void lTrim(String key, long start, long end, Class<T> clazz) {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.lTrim(keyBytes(key), start, end);
            return null;
        });
    }


    @SuppressWarnings("unchecked")
    private static <T> T toObj(byte[] ans, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) new String(ans, CODE);
        }
        return JsonUtil.toObj(new String(ans, CODE), clazz);
    }

    private static <T> List<T> byteListToObjectList(List<byte[]> records, Class<T> clazz) {
        if (CollectionUtils.isEmpty(records)) {
            return Collections.emptyList();
        }
        List<T> targetValues = Lists.newArrayListWithCapacity(records.size());
        for (byte[] record : records) {
            targetValues.add(JsonUtil.toObj(new String(record, CODE), clazz));
        }
        return targetValues;
    }


}