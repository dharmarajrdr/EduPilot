package com.edupilot.backend.config.Redis;

import com.edupilot.backend.dto.response.BaseResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Configuration class for Redis-based caching in the application.
 * This config sets up a RedisCacheManager with the following settings:
 * - Entries expire after 10 minutes
 * - Null values are not cached
 * - Values are serialized using Jackson (for BaseResponseDto)
 */
@Configuration
public class RedisConfig {

    /**
     * Configures and provides a RedisCacheManager bean.
     *
     * @param connectionFactory the Redis connection factory (auto-configured by Spring)
     * @return RedisCacheManager with custom settings
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        // Define Redis cache configuration
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))  // Set time-to-live for cache entries to 10 minutes
                .disableCachingNullValues()        // Avoid caching null values to prevent unnecessary usage
                .serializeValuesWith(              // Use Jackson serializer for serializing values
                        RedisSerializationContext.SerializationPair.fromSerializer(
                                new Jackson2JsonRedisSerializer<>(BaseResponseDto.class)
                        )
                );

        // Build and return the RedisCacheManager using the defined configuration
        return RedisCacheManager
                .builder(connectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .build();
    }
}
