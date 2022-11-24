package com.laboratory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.models.AffilliateModel;
import com.laboratory.repositories.AffilliateRepository;

@Service
public class AffilliateService {

	@Autowired
	AffilliateRepository affilliaterepository;

	public List<AffilliateModel> getList() {
		return (List<AffilliateModel>) affilliaterepository.findAll();
	}

	public AffilliateModel getById(Integer id) {
		return affilliaterepository.findById(id).get();
	}

	public void post(AffilliateModel afilliate) {
		affilliaterepository.save(afilliate);
	}

	public void delete(Integer id) {
		affilliaterepository.deleteById(id);
	}

}
