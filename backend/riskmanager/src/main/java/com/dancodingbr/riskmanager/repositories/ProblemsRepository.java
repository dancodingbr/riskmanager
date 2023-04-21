package com.dancodingbr.riskmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dancodingbr.riskmanager.models.Problem;

@Repository
public interface ProblemsRepository extends CrudRepository<Problem, Long> {

	List<Problem> findAll();
}
