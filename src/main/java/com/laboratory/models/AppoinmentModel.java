package com.laboratory.models;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "appoinments")
public class AppoinmentModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private LocalDate date;

	@JsonFormat(pattern = "HH:mm")
	@NotNull
	private LocalTime hour;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "tests_id")
	@JsonProperty(access = Access.READ_WRITE)
	private TestModel tests;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "affilliate_id")
	@JsonProperty(access = Access.READ_WRITE)
	private AffilliateModel affilliates;

	public AppoinmentModel() {
		super();
	}

	public AppoinmentModel(int id, @NotNull LocalDate date, @NotNull LocalTime hour) {
		super();
		this.id = id;
		this.date = date;
		this.hour = hour;
	}
		

	public AppoinmentModel(int id, @NotNull LocalDate date, @NotNull LocalTime hour, TestModel tests,
			AffilliateModel affilliates) {
		super();
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.tests = tests;
		this.affilliates = affilliates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public TestModel getTests() {
		return tests;
	}

	public void setTests(TestModel tests) {
		this.tests = tests;
	}

	public AffilliateModel getAffilliates() {
		return affilliates;
	}

	public void setAffilliates(AffilliateModel affilliates) {
		this.affilliates = affilliates;
	}

}
