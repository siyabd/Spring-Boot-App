package com.digital.academy.cloudDime.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.Points;
import com.digital.academy.cloudDime.entity.User;

@Service
@Transactional
@Component
public interface PointsRepository extends CrudRepository<Points,Integer>{
	Points findByUser(User user);

}
