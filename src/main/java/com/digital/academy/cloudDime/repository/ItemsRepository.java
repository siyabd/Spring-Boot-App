package com.digital.academy.cloudDime.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.digital.academy.cloudDime.entity.Items;

@Service
@Transactional
@Component
public interface ItemsRepository extends CrudRepository<Items,Integer>{

}
