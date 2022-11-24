package com.laboratory.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laboratory.models.TestModel;
import com.laboratory.services.TestService;

@RestController
@RequestMapping("/api/controller/test")
public class TestController {
	
	@Autowired
	TestService testservice;

	@GetMapping()
	public ResponseEntity<List<TestModel>> getList() {
		if (testservice.getList().isEmpty()) {
			return new ResponseEntity<List<TestModel>>(HttpStatus.NO_CONTENT);
		} else {
			List<TestModel> testList = testservice.getList();
			return new ResponseEntity<List<TestModel>>(testList, HttpStatus.OK);
		}
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<TestModel> getById(@PathVariable Integer id) {
		try {
			TestModel test = testservice.getById(id);
			return new ResponseEntity<TestModel>(test, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<TestModel>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping()
	public ResponseEntity<?> post(@RequestBody @Valid TestModel test) {
		testservice.post(test);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<?> put(@RequestBody TestModel test, @PathVariable Integer id) {
		try {
			TestModel testActualizado = testservice.getById(id);
			testActualizado.setName(test.getName());
			testActualizado.setDescription(test.getDescription());
			testservice.post(testActualizado);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<TestModel>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			testservice.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
