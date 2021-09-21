package com.blog.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.blog.dto.User;
@SpringBootTest
public class BlogServiceTest {
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private BlogService BlogService =new BlogService();
	
	@Test
	void getUserDetailsTest()
	{
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/users", User[].class);
		User[] expectedResult=responseEntity.getBody();
		User[] actualResult=BlogService.getUserDetails();
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
