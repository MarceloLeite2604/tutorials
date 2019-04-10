package com.github.marceloleite2604.tutorials.embedded.redis.configuration;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.embedded.RedisServer;

@Configuration
public class RedisConfiguration {

	private static final int PORTA_REDIS = 6379;

	@Bean(destroyMethod = "stop")
	public RedisServer criarRedis() throws IOException {
		RedisServer redisServer = RedisServer.builder()
				.port(PORTA_REDIS)
				//.setting("save 30 10000")
				.build();
		redisServer.start();
		return redisServer;
	}

	/*
	 * @Bean JedisConnectionFactory jedisConnectionFactory() { return new
	 * JedisConnectionFactory(); }
	 */

	/*
	 * @Bean public RedisTemplate<String, Object> redisTemplate() {
	 * RedisTemplate<String, Object> template = new RedisTemplate<>();
	 * template.setConnectionFactory(jedisConnectionFactory()); return template; }
	 */

}
