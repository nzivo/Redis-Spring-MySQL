package com.example.redis.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
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
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer() {
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisJsonSerializer =
                new GenericJackson2JsonRedisSerializer();
        return genericJackson2JsonRedisJsonSerializer;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

}
