package com.dayone.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

<<<<<<< HEAD
import java.time.Duration;

=======
>>>>>>> c283280 (Initial commit)
@RequiredArgsConstructor
@Configuration
public class CacheConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
<<<<<<< HEAD
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory){
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig()
                /*serialization, 직렬화하기 */
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()))
                /*.entryTtl(Duration.of(원하는기간.ex"ofHours(5L)"))*/;//캐시 유효기간 설정.

                return RedisCacheManager.RedisCacheManagerBuilder
=======
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.RedisCacheManagerBuilder
>>>>>>> c283280 (Initial commit)
                        .fromConnectionFactory(redisConnectionFactory)
                        .cacheDefaults(conf)
                        .build();
    }

<<<<<<< HEAD

    //redis와 서버와의 연결 관리. 요건 레디스와 커넥션을 맺을 수 있는 커넥션 팩토리만 생성된 상태
    //이걸 캐시에 적용시켜 사용하기 위해서는 캐시 매니저 빈 추가 생성필요 = 위에다 만든거
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration(); //클러스터나 센티널이 아니라 싱글이라 요거
        conf.setHostName(this.host);
        conf.setPort(this.port);
        //이렇게 생성한 redis config정보를 LettuceConnectionFactory에 설정정보로 넣어서 인스턴스 생성
=======
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(this.host);
        conf.setPort(this.port);
>>>>>>> c283280 (Initial commit)
        return new LettuceConnectionFactory(conf);
    }
}
