package com.digital.academy.cloudDime.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.TransactionalHistory;
import com.digital.academy.cloudDime.entity.User;
@Service
@Transactional
@Component
public interface HistoryRepository extends CrudRepository<TransactionalHistory,Integer>{
	ArrayList<TransactionalHistory> findByUser(User user);

}
