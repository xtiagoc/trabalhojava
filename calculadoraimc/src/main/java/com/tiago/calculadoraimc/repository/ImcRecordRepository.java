package com.tiago.calculadoraimc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tiago.calculadoraimc.model.ImcRecord;

@Repository
public interface ImcRecordRepository extends JpaRepository<ImcRecord, Long>{
	
}