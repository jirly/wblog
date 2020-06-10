package com.jirly.sso;

import com.jirly.sso.controller.HelloController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
class SsoApplicationTests {
	private MockMvc mvc;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
	}

	@Test
	void contextLoads() {
		Map<String,Object> user = new HashMap<>();
		user.put("name","小明");
		user.put("secret","xxxx");
		redisTemplate.opsForValue().set("test",user);
		Map<String,Object> test = (Map<String, Object>) redisTemplate.opsForValue().get("test");
	}



}
