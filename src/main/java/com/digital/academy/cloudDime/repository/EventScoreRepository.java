package com.digital.academy.cloudDime.repository;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.digital.academy.cloudDime.entity.EventScore;
import com.digital.academy.cloudDime.entity.User;
@Service
@Transactional
@Component
public interface EventScoreRepository extends CrudRepository<EventScore,Integer> {
	EventScore findByUser(User user);
	
}
