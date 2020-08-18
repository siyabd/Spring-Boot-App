package com.digital.academy.cloudDime.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.Events;
import com.digital.academy.cloudDime.entity.User;

@Service
@Transactional
@Component
public interface EventsRepository extends CrudRepository<Events,Integer> {
	ArrayList<Events> findByDateBetween(Date start,Date end);
	ArrayList<Events> findByDateBefore(Timestamp time);
	ArrayList<Events> findByLocation(String branch);
	ArrayList<Events> findAll();
	
	ArrayList<Events> findByUser(User user);
	
	
}
