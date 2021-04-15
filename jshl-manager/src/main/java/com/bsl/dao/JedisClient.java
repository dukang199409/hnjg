package com.bsl.dao;
public interface JedisClient {
	String get(String key);
	String set(String key, String value);
	String hget(String hkey, String key);
	Long hset(String hkey, String key, String value);
	long incr(String key);
	long expire(String key, int second);
	long ttl(String key);
	long del(String key);
	long hdel(String key, String field);
	boolean close();
}
