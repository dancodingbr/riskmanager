package com.dancodingbr.riskmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dancodingbr.riskmanager.models.ActionPlan;

@Repository
public interface ActionPlansRepository extends CrudRepository<ActionPlan, Long> {

	List<ActionPlan> findAll();
}
