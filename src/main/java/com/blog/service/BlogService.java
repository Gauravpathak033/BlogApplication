package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blog.dto.Post;
import com.blog.dto.User;

@Service
public class BlogService {
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${postUri}")
	private String postUri;
	
	@Value("${userUri}")
	private String userUri;
	
	//Get the Posts from "https://jsonplaceholder.typicode.com/posts"  API
	public List<Post> getPostDetails()
	{
		Post post = new Post();
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Post> postEntity = new HttpEntity<Post>(post, headers);
		ResponseEntity<List<Post>> postResult = restTemplate.exchange(postUri, HttpMethod.GET, postEntity,
				new ParameterizedTypeReference<List<Post>>() {});
		return postResult.getBody();
	}
	
	//Get the Users from "https://jsonplaceholder.typicode.com/users"  API
	public User[] getUserDetails()
	{
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(userUri, User[].class);
		return responseEntity.getBody();
	}
	
}
