package se.ams.track.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import se.ams.track.model.JobAdvertisement;


@Configuration
public class RedisConfiguration {

    @Autowired
    private JedisConnectionFactory jedisConnFactory;

    @Bean
    public StringRedisSerializer stringRedisSerializer() {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        return stringRedisSerializer;
    }

    @Bean
    public Jackson2JsonRedisSerializer<JobAdvertisement> jacksonJsonRedisJsonSerializer() {
        Jackson2JsonRedisSerializer<JobAdvertisement> jacksonJsonRedisJsonSerializer = new Jackson2JsonRedisSerializer<>(JobAdvertisement.class);
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
