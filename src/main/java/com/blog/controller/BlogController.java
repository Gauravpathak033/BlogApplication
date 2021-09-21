package com.blog.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blog.dto.Post;
import com.blog.dto.User;
import com.blog.service.BlogService;

@RestController
public class BlogController {

	@Autowired
	BlogService blogService;
	
	@GetMapping("/admin")
	public ResponseEntity<?> getUserPostDetails()  {
		
		// STEP 1 : 
		//below code is to get the Posts from "https://jsonplaceholder.typicode.com/posts"  API
		List<Post> postList = blogService.getPostDetails();
		
		//Step 2 :
		//below code is to get the Users from "https://jsonplaceholder.typicode.com/users"  API
		User[] objects =blogService.getUserDetails();
		
		//Step 3 :
		//below code is to combine the User details and their related posts together as per requirement  
		List<User> userlist = new ArrayList<User>();
		for (User obj : objects) {
			User user = new User();
			user.setAddress(obj.getAddress());
			user.setCompany(obj.getCompany());
			user.setEmail(obj.getEmail());
			user.setId(obj.getId());
			user.setPhone(obj.getPhone());
			user.setUsername(obj.getUsername());
			user.setWebsite(obj.getWebsite());
			List<Post> list = new ArrayList<Post>();
			for (Post postlist : postList) {
				if (obj.getId() == postlist.getUserId()) {
					list.add(postlist);
				}
			}
			user.setPost(list);
			userlist.add(user);
		}
		return ResponseEntity.ok(userlist);  // Returning the List of Users with their related posts

	}
	
}
