package com.digital.academy.cloudDime.repository;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.User;

@Service
@Transactional
@Component
public interface UserRepository extends CrudRepository<User,Integer>{
	User findByEmail(String email);
	ArrayList<User> findByActiveTrue();
	ArrayList<User> findByActiveFalse();
	
	ArrayList<User> findByApprovalDateNull();
	ArrayList<User> findByApprovalDate(Timestamp date);
	ArrayList<User> findByStatus(int status);
	
	Long countByApprovalDateNull();
	Long countByApprovalDateNotNull();
	
	
		
}
