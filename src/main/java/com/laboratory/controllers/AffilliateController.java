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

import com.laboratory.models.AffilliateModel;
import com.laboratory.services.AffilliateService;

@RestController
@RequestMapping("/api/controller/affilliates")
public class AffilliateController {
	
	@Autowired
	AffilliateService affilliateservice;
	
	@GetMapping()
	public ResponseEntity<List<AffilliateModel>> getList() {
		if (affilliateservice.getList().isEmpty()) {
			return new ResponseEntity<List<AffilliateModel>>(HttpStatus.NO_CONTENT);
		} else {
			List<AffilliateModel> affilliatesList = affilliateservice.getList();
			return new ResponseEntity<List<AffilliateModel>>(affilliatesList, HttpStatus.OK);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<AffilliateModel> getById(@PathVariable Integer id) {
		try {
			AffilliateModel afilliate = affilliateservice.getById(id);
			return new ResponseEntity<AffilliateModel>(afilliate, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<AffilliateModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> post(@RequestBody @Valid AffilliateModel affilliate) {
		affilliateservice.post(affilliate);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<?> put(@RequestBody AffilliateModel affilliate, @PathVariable Integer id) {
		try {
			AffilliateModel affilliateActualizado = affilliateservice.getById(id);
			affilliateActualizado.setName(affilliate.getName());
			affilliateActualizado.setAge(affilliate.getAge());
			affilliateActualizado.setEmail(affilliate.getEmail());
			affilliateservice.post(affilliateActualizado);
			return new ResponseEntity<>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<AffilliateModel>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try {
			affilliateservice.delete(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
