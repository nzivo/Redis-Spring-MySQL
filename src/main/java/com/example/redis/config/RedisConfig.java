package com.example.redis.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
@PropertySource("classpath:application.properties")
public class RedisConfig extends CachingConfigurerSupport {
   @Autowired
   private Environment env;

   @Bean
   public LettuceConnectionFactory redisConnectionFactory() {
	    RedisStandaloneConfiguration redisConf = new RedisStandaloneConfiguration();
        return new LettuceConnectionFactory(redisConf);
   }

   @Bean
   public RedisCacheManager cacheManager() {
	    RedisCacheManager rcm = RedisCacheManager.create(redisConnectionFactory());
	    rcm.setTransactionAware(true);
   	    return rcm;
   }

    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        final RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

}
