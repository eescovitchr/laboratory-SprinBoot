package com.laboratory.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.controllers.AppoinmentController;
import com.laboratory.models.*;
import com.laboratory.services.*;
import com.laboratory.repositories.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@WebMvcTest(AppoinmentController.class)
public class AppoinmentControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private AppoinmentService appoinmentService;

	@MockBean
	private AffilliateService affilliateService;

	@MockBean
	private TestService testService;

	@MockBean
	private AppoinmentRepository appoinmentRepository;

	@MockBean
	private AffilliateRepository affilliateRepository;

	@MockBean
	private TestRepository testRepository;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	void testGetById() throws Exception {
		when(appoinmentService.getById(1)).thenReturn(DatosPruebas.Appoinment01().orElseThrow());

		mockMVC.perform(get("/api/controller/appoinments/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.date").value("04/12/2022")).andExpect(jsonPath("$.hour").value("08:00"));

		verify(appoinmentService).getById(1);
	}

	@Test
	void testGetList() throws JsonProcessingException, Exception {
		List<AppoinmentModel> appoinmentList = Arrays.asList(DatosPruebas.Appoinment01().orElseThrow(),
				DatosPruebas.Appoinment02().orElseThrow());

		when(appoinmentService.getList()).thenReturn(appoinmentList);

		mockMVC.perform(get("/api/controller/appoinments").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].date").value("04/12/2022")).andExpect(jsonPath("$[0].hour").value("08:00"))
				.andExpect(jsonPath("$[1].date").value("05/12/2022")).andExpect(jsonPath("$[1].hour").value("08:00"));

		verify(appoinmentService, times(2)).getList();

	}

	@Test
	void TestPost() throws JsonProcessingException, Exception {

		mockMVC.perform(post("/api/controller/appoinments").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isBadRequest()).andReturn();

	}

	@Test
	void TestDelete() throws JsonProcessingException, Exception {

		mockMVC.perform(delete("/api/controller/appoinments/1").contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

	}

}
