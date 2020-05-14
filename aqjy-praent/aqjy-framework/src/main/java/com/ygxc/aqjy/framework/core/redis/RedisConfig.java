package com.ygxc.aqjy.framework.core.redis;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redisConfig
 */
@Configuration
public class RedisConfig {

	@Bean
	@ConditionalOnMissingBean
	@ConfigurationProperties(RedisProperties.REDIS_CLUSTER_PREFIX)
	public RedisProperties createRedisClusterProperties(){
		return new RedisProperties();
	}

	private JedisPoolConfig jedisPoolConfig(RedisProperties redisClusterProperties){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxIdle(redisClusterProperties.getMaxIdle());
		jedisPoolConfig.setMaxTotal(redisClusterProperties.getMaxTotal());
		jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getMaxWaitMillis());
		return jedisPoolConfig;
	}

	private RedisClusterConfiguration redisClusterConfiguration(RedisProperties redisClusterProperties) {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
		String[] hostNames = redisClusterProperties.getHostName().split(";");
		Set<RedisNode> redisNodes = new HashSet<>();
		for (String item : hostNames) {
			String[] hostName = item.split(":");
			redisNodes.add(new RedisClusterNode(hostName[0], Integer.parseInt(hostName[1])));
		}
		redisClusterConfiguration.setClusterNodes(redisNodes);
		redisClusterConfiguration.setMaxRedirects(redisClusterProperties.getMaxRedirects());
		return redisClusterConfiguration;
	}

	@Bean
	@ConditionalOnMissingBean
	public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisClusterProperties) {
		JedisConnectionFactory jedisConnectionFactory = null;
		String password = redisClusterProperties.getPassword();
		String[] hostNames = redisClusterProperties.getHostName().split(";");
		if (hostNames.length > 1) {
			jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration(redisClusterProperties),
					jedisPoolConfig(redisClusterProperties));
		} else {
			String[] hostName = hostNames[0].split(":");

			jedisConnectionFactory = new JedisConnectionFactory();
			jedisConnectionFactory.setHostName(hostName[0]);
			jedisConnectionFactory.setPort(Integer.parseInt(hostName[1]));
			if (StringUtils.isNotBlank(password)) {
				jedisConnectionFactory.setPassword(password);
			}
		}
		
		jedisConnectionFactory.setDatabase(redisClusterProperties.getDatabase());
		
		return jedisConnectionFactory;
	}

	@Bean("redisTemplate")
	@ConditionalOnMissingBean
	public RedisTemplate<String, Object> redisTemplate(
			@Qualifier("jedisConnectionFactory") JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.setDefaultSerializer(new StringRedisSerializer());
		return redisTemplate;
	}


}
