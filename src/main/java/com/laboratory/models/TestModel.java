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

@Entity
@Table(name = "tests")
public class TestModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String nombre;

	@NotNull
	private String description;

	@OneToMany(mappedBy = "tests", cascade = CascadeType.ALL)
	private Set<AppoinmentModel> appoinments = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
