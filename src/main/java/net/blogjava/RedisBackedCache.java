package net.blogjava;

import redis.clients.jedis.Jedis;

public class RedisBackedCache {
	private final Jedis jedis;

	public RedisBackedCache(String address, Integer port) {
		this.jedis = new Jedis(address, port);
	}

	public void put(String field, String value) {
		this.jedis.hset("default", field, value);
	}

	public String get(String field) {
		return this.jedis.hget("default", field);
	}

}
