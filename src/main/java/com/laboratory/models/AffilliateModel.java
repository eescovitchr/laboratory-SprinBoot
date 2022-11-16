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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "affilliates")
public class AffilliateModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	private String name;
	@NotNull
	private Integer age;
	@NotBlank
	private String email;

	@OneToMany(mappedBy = "affilliates", cascade = CascadeType.ALL)
	private Set<AppoinmentModel> appoinments = new HashSet<>();

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<AppoinmentModel> getAppoinments() {
		return appoinments;
	}

	public void setAppoinments(Set<AppoinmentModel> appoinments) {
		this.appoinments = appoinments;
	}

}
