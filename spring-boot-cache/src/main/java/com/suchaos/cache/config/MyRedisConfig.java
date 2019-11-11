package com.suchaos.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

/**
 * RedisConfig
 *
 * @author suchao
 * @date 2019/11/11
 */
@Configuration
public class MyRedisConfig {

//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
//            throws UnknownHostException {
////        RedisTemplate<Object, Object> template = new RedisTemplate<>();
////        template.setConnectionFactory(redisConnectionFactory);
////        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
////        template.setDefaultSerializer(jackson2JsonRedisSerializer);
////        return template;
//        // 1.创建 redisTemplate 模版
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        // 2.关联 redisConnectionFactory
//        template.setConnectionFactory(redisConnectionFactory);
//        // 3.创建 序列化类
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        // 4.设置可见度
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        // 5.启动默认的类型
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        // 6.序列化类，对象映射设置
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 7.设置 value 的转化格式和 key 的转化格式
//        template.setValueSerializer(jackson2JsonRedisSerializer);
//        template.setKeySerializer(new StringRedisSerializer());
//        template.afterPropertiesSet();
//        return template;
//    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
