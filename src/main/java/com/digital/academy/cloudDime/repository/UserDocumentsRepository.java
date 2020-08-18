package com.digital.academy.cloudDime.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.User;
import com.digital.academy.cloudDime.entity.UserDocuments;
@Service
@Transactional
@Component
public interface UserDocumentsRepository extends CrudRepository<UserDocuments,Integer>{
	
	User findByUser(User user);
	
}
