package com.dancodingbr.riskmanager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dancodingbr.riskmanager.models.AnalyzedResult;

@Repository
public interface AnalyzedResultRepository extends CrudRepository<AnalyzedResult, Long> {

	@Query("select ar from AnalyzedResult ar where upper(ar.problem) = :problem")
	List<AnalyzedResult> findAllByProblem(@Param("problem") String problem);

}
