package com.annachi.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class UserServiceApplication 
{

	public static void main(String[] args) 
	{
		SpringApplication.run(UserServiceApplication.class, args);
	}
	@Bean	
	@LoadBalanced
	public RestTemplate restTemplate() 
	{
		   RestTemplate restTemplate = new RestTemplate();
		   List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		   MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		   converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		   messageConverters.add(converter);
		   restTemplate.setMessageConverters(messageConverters);
		   return restTemplate;
		}

}
