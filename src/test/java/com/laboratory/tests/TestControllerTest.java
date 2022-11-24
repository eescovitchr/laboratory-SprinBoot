package com.laboratory.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.controllers.TestController;
import com.laboratory.models.TestModel;
import com.laboratory.services.TestService;

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

@WebMvcTest(TestController.class)
public class TestControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private TestService testService;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	void testGetById() throws Exception {
		when(testService.getById(1)).thenReturn(DatosPruebas.Test01().orElseThrow());

		mockMVC.perform(get("/api/controller/test/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Examen glucosa")).andExpect(jsonPath("$.description")
						.value("La prueba de glucosa en la sangre mide los niveles de glucosa en la sangre."));

		verify(testService).getById(1);
	}

	@Test
	void testGetList() throws JsonProcessingException, Exception {
		List<TestModel> testList = Arrays.asList(DatosPruebas.Test01().orElseThrow(),
				DatosPruebas.Test02().orElseThrow());

		when(testService.getList()).thenReturn(testList);

		mockMVC.perform(get("/api/controller/test").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].name").value("Examen glucosa"))
				.andExpect(jsonPath("$[0].description")
						.value("La prueba de glucosa en la sangre mide los niveles de glucosa en la sangre."))
				.andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[1].name").value("Examen tuberculosis"))
				.andExpect(jsonPath("$[1].description")
						.value("Es una prueba que permite detectar la infección con tuberculosis."))
				.andExpect(content().json(objectMapper.writeValueAsString(testList)));

		verify(testService, times(2)).getList();

	}

	@Test
	void TestPost() throws JsonProcessingException, Exception {

		String jsonRequest = objectMapper.writeValueAsString(DatosPruebas.Test01().orElseThrow());

		mockMVC.perform(post("/api/controller/test").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	void TestDelete() throws JsonProcessingException, Exception {

		String jsonRequest = objectMapper.writeValueAsString(DatosPruebas.Test01().orElseThrow());

		mockMVC.perform(
				delete("/api/controller/test/1").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();

	}

}
