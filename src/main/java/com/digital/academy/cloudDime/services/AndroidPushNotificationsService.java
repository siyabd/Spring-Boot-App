package com.digital.academy.cloudDime.services;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



 
@Service
public class AndroidPushNotificationsService {
 
	private static final String FIREBASE_SERVER_KEY = "AAAAIvkqSLE:APA91bH5A03KBJMpHRhE6f329ltNGqY9wuJ83N8N1x7qDWpnqMUCLeVyiRHKHcjBXEd"
			+ "4XSsvSusty-7r9xMUmmThL25LzecxiYTFJZ9urS4cii0PBCx_l0KG_G_d76lIcr0lMmnOh9VG";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity) {
 
		RestTemplate restTemplate = new RestTemplate();
 
 
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
 
		String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
 
		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
