package com.laboratory.tests;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.Optional;

import com.laboratory.models.TestModel;
import com.laboratory.models.AffilliateModel;
import com.laboratory.models.AppoinmentModel;

public class DatosPruebas {
	
	public static Optional<TestModel> Test01(){
		return Optional.of(new TestModel(1,"Examen glucosa","La prueba de glucosa en la sangre mide los niveles de glucosa en la sangre."));
	}
	
	public static Optional<TestModel> Test02(){
		return Optional.of(new TestModel(2,"Examen tuberculosis","Es una prueba que permite detectar la infección con tuberculosis."));
	}
	
	public static Optional<AffilliateModel> Affilliate01(){
		return Optional.of(new AffilliateModel(1,"Elliot Escovitch",25,"eescovitchr@gmail.com"));
	}
	
	public static Optional<AffilliateModel> Affilliate02(){
		return Optional.of(new AffilliateModel(2,"Manuela Cardona",27,"manuecardona33@gmail.com"));
	}
	
	public static Optional<AppoinmentModel> Appoinment01(){
		return Optional.of(new AppoinmentModel(1,LocalDate.of(2022, 12, 4),LocalTime.of(8,0)));
	}
	
	public static Optional<AppoinmentModel> Appoinment02(){
		return Optional.of(new AppoinmentModel(2,LocalDate.of(2022, 12, 5),LocalTime.of(8,0)));
	}

}
