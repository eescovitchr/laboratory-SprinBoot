package com.laboratory.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laboratory.controllers.AffilliateController;
import com.laboratory.models.AffilliateModel;
import com.laboratory.services.AffilliateService;

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

@WebMvcTest(AffilliateController.class)
public class AffilliatesControllerTest {

	@Autowired
	private MockMvc mockMVC;

	@MockBean
	private AffilliateService affilliateService;

	ObjectMapper objectMapper;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
	}

	@Test
	void testGetById() throws Exception {
		when(affilliateService.getById(1)).thenReturn(DatosPruebas.Affilliate01().orElseThrow());

		mockMVC.perform(get("/api/controller/affilliates/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.name").value("Elliot Escovitch")).andExpect(jsonPath("$.age").value(25))
				.andExpect(jsonPath("$.email").value("eescovitchr@gmail.com"));

		verify(affilliateService).getById(1);
	}

	@Test
	void testGetList() throws JsonProcessingException, Exception {
		List<AffilliateModel> affilliateList = Arrays.asList(DatosPruebas.Affilliate01().orElseThrow(),
				DatosPruebas.Affilliate02().orElseThrow());

		when(affilliateService.getList()).thenReturn(affilliateList);

		mockMVC.perform(get("/api/controller/affilliates").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id").value(1)).andExpect(jsonPath("$[0].name").value("Elliot Escovitch"))
				.andExpect(jsonPath("$[0].age").value(25))
				.andExpect(jsonPath("$[0].email").value("eescovitchr@gmail.com"))
				.andExpect(jsonPath("$[1].name").value("Manuela Cardona")).andExpect(jsonPath("$[1].age").value(27))
				.andExpect(jsonPath("$[1].email").value("manuecardona33@gmail.com"))
				.andExpect(content().json(objectMapper.writeValueAsString(affilliateList)));

		verify(affilliateService, times(2)).getList();

	}

	@Test
	void TestPost() throws JsonProcessingException, Exception {

		String jsonRequest = objectMapper.writeValueAsString(DatosPruebas.Affilliate01().orElseThrow());

		mockMVC.perform(
				post("/api/controller/affilliates").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isCreated()).andReturn();

	}

	@Test
	void TestDelete() throws JsonProcessingException, Exception {

		String jsonRequest = objectMapper.writeValueAsString(DatosPruebas.Affilliate01().orElseThrow());

		mockMVC.perform(delete("/api/controller/affilliates/1").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk()).andReturn();

	}

}
