package com.laboratory.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.laboratory.models.AppoinmentModel;

@Repository
public interface  AppoinmentRepository extends JpaRepository<AppoinmentModel, Integer> {
	
	@Query(value = "SELECT * FROM appoinments WHERE date = :date ORDER BY affilliate_id", nativeQuery = true)
	List<AppoinmentModel>  findByDate(@Param("date") LocalDate date);

}
