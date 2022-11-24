package com.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.models.TestModel;

@Repository
public interface TestRepository extends JpaRepository<TestModel, Integer> {

}
