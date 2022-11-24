package com.laboratory.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.models.TestModel;
import com.laboratory.repositories.TestRepository;

@Service
public class TestService {

	@Autowired
	TestRepository testrepository;

	public List<TestModel> getList() {
		return (List<TestModel>) testrepository.findAll();
	}

	public TestModel getById(Integer id) {
		return testrepository.findById(id).get();
	}

	public void post(TestModel test) {
		testrepository.save(test);
	}

	public void delete(Integer id) {
		testrepository.deleteById(id);
	}


}
