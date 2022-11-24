package com.laboratory.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tests")
public class TestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;

	@NotNull
	private String description;

	@OneToMany(mappedBy = "tests", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<AppoinmentModel> appoinments = new HashSet<>();

	public TestModel() {
		super();
	}

	public TestModel(int id, @NotNull String name, @NotNull String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public TestModel(int id, @NotNull String name, @NotNull String description, Set<AppoinmentModel> appoinments) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.appoinments = appoinments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<AppoinmentModel> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(Set<AppoinmentModel> appoinments) {
		
		this.appoinments = appoinments;
	}

}
