package se.ams.track.config;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import se.ams.track.model.JobAdvertisement;


@Configuration
public class RedisConfiguration {

    @Inject
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public JacksonJsonRedisSerializer<JobAdvertisement> jacksonJsonRedisJsonSerializer() {
        JacksonJsonRedisSerializer<JobAdvertisement> jacksonJsonRedisJsonSerializer = new JacksonJsonRedisSerializer<>(JobAdvertisement.class);
        return jacksonJsonRedisJsonSerializer;
    }

    @Bean
    public RedisTemplate<String, JobAdvertisement> redisTemplate() {
        RedisTemplate<String, JobAdvertisement> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnFactory);
        redisTemplate.setKeySerializer(stringRedisSerializer());
        redisTemplate.setValueSerializer(jacksonJsonRedisJsonSerializer());

        return redisTemplate;
    }
}
