package com.laboratory.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import com.laboratory.models.AppoinmentModel;
import com.laboratory.models.TestModel;
import com.laboratory.models.AffilliateModel;
import com.laboratory.repositories.AffilliateRepository;
import com.laboratory.repositories.AppoinmentRepository;
import com.laboratory.repositories.TestRepository;
import com.laboratory.services.AffilliateService;
import com.laboratory.services.AppoinmentService;
import com.laboratory.services.TestService;

@RestController
@RequestMapping("/api/controller/appoinments")
public class AppoinmentController {
	
	@Autowired
	AppoinmentService appoinmentservice;
	
	@Autowired
	AppoinmentRepository appoinmentrepository;
	
	
	@Autowired
	AffilliateRepository affilliaterepository;
	
	@Autowired
	AffilliateService affilliateservice;
	
	@Autowired
	TestRepository testrepository;
	
	@Autowired
	TestService testservice;
	
	@GetMapping()
	public ResponseEntity<List<AppoinmentModel>> getList() {
		if (appoinmentservice.getList().isEmpty()) {
			return new ResponseEntity<List<AppoinmentModel>>(HttpStatus.NO_CONTENT);
		} else {
			List<AppoinmentModel> appoinmentsList = appoinmentservice.getList();
			return new ResponseEntity<List<AppoinmentModel>>(appoinmentsList, HttpStatus.OK);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AppoinmentModel> getById(@PathVariable Integer id) {
		try {
			AppoinmentModel appoinment = appoinmentservice.getById(id);
			return new ResponseEntity<AppoinmentModel>(appoinment, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<AppoinmentModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/affilliates/{affilliate_id}")
	public ResponseEntity<Set<AppoinmentModel>> getByAffilliateId(@PathVariable Integer affilliate_id) {		
		
		try {			
			AffilliateModel affilliate= affilliaterepository.findById(affilliate_id).orElseThrow();
			Set<AppoinmentModel> appoinment = affilliate.getAppoinments();			
			return new ResponseEntity<Set<AppoinmentModel>>(appoinment,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Set<AppoinmentModel>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(path = "/{day}/{month}/{year}")
	public ResponseEntity<List<AppoinmentModel>> getByDate(@PathVariable int day,@PathVariable int month, @PathVariable int year) {				
			
		LocalDate date = LocalDate.of(year, month, day);
		List<AppoinmentModel>  appoinment = appoinmentrepository.findByDate(date);
		
		if (appoinment.isEmpty()) {
			return new ResponseEntity<List<AppoinmentModel>>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<AppoinmentModel>>(appoinment, HttpStatus.OK);
		}

	}
	
	@PostMapping()
	public ResponseEntity<?> post(@Valid @RequestBody AppoinmentModel appoinment) {
		Optional<TestModel> testOptional = testrepository.findById(appoinment.getTests().getId());
		Optional<AffilliateModel> affilliateOptional = affilliaterepository.findById(appoinment.getAffilliates().getId());
		
		appoinment.setTests(testOptional.get());
		appoinment.setAffilliates(affilliateOptional.get());
		
		appoinmentrepository.save(appoinment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> put(@RequestBody AppoinmentModel appoinment, @PathVariable Integer id) {
		try {
			
			Optional<TestModel> testActulizado = testrepository.findById(appoinment.getTests().getId());
			Optional<AffilliateModel> affilliateActulizado = affilliaterepository.findById(appoinment.getAffilliates().getId());
			
			AppoinmentModel appoinmentActualizado = appoinmentservice.getById(id);
			
			appoinmentActualizado.setTests(testActulizado.get());
			appoinmentActualizado.setAffilliates(affilliateActulizado.get());
			
			appoinmentActualizado.setDate(appoinment.getDate());
			appoinmentActualizado.setHour(appoinment.getHour());
			appoinmentservice.post(appoinmentActualizado);
			
			
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<AppoinmentModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			appoinmentservice.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
