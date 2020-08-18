package com.digital.academy.cloudDime.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.InterestedUsers;
import com.digital.academy.cloudDime.entity.User;

@Service
@Transactional
@Component
public interface InterestedUsersRepository extends CrudRepository<InterestedUsers,Integer>{
	
	ArrayList<InterestedUsers> findByUser(User user);

}
