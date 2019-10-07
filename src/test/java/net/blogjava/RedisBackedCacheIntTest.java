package net.blogjava;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;

public class RedisBackedCacheIntTest {

	private RedisBackedCache underTest;
	
	@Rule
	public GenericContainer redis = new GenericContainer<>("redis:5.0.3-alpine").withExposedPorts(6379);
	
	@Before
	public void setUp() throws Exception {
		String address = redis.getContainerIpAddress();
		Integer port = redis.getFirstMappedPort();
		
		underTest = new RedisBackedCache(address, port);
	}

	@Test
	public void testSimplePutAndGet() {
		underTest.put("test", "example");
		
		String retrieved = underTest.get("test");
		assertEquals("example", retrieved);
	}

}
