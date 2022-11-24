package com.laboratory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratory.models.AffilliateModel;

@Repository
public interface AffilliateRepository extends JpaRepository<AffilliateModel, Integer> {

}
