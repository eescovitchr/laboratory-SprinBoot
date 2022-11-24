package com.laboratory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laboratory.models.AppoinmentModel;
import com.laboratory.repositories.AppoinmentRepository;

@Service
public class AppoinmentService {
	
	@Autowired
	AppoinmentRepository appoinmentrepository;
	
	public List<AppoinmentModel> getList(){
		return (List<AppoinmentModel>) appoinmentrepository.findAll();
	}
	
	public AppoinmentModel getById(Integer id) {
		return appoinmentrepository.findById(id).get();
	}
	
	public void post(AppoinmentModel appoinment) {
		appoinmentrepository.save(appoinment);
	}
	
	public void delete(Integer id) {
		appoinmentrepository.deleteById(id);
	}


}
