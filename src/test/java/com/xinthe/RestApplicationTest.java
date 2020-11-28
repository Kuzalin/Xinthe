package com.xinthe;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.xinthe.bean.FinalResponse;


@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
public class RestApplicationTest{
	
	private final short responseCodeSuccessGet = 200;
	
	private final String testResult = "SUCCESS";
	
	@Autowired
	private MockMvc  mockMvc;
	
	@DisplayName("Service test for employee list")
	@Test
	public void getEmployeeListForPlaceSuccess() throws Exception
	{
		
		String url="/employee/place/plce1";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status=result.getResponse().getStatus();
		assertEquals(responseCodeSuccessGet, status);
		String content = result.getResponse().getContentAsString();
		FinalResponse finalResponse = new Gson().fromJson(content, FinalResponse.class);				
		assertEquals(finalResponse.getResponseCode(),testResult);
		
	}
	
	@DisplayName("Service test for Salary Range for competency")
	@Test
	public void getSalaryRangeForCompetency() throws Exception
	{
		
		String url="/employee/Competency/java";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status=result.getResponse().getStatus();
		assertEquals(responseCodeSuccessGet, status);
		String content = result.getResponse().getContentAsString();
		FinalResponse finalResponse = new Gson().fromJson(content, FinalResponse.class);				
		assertEquals(finalResponse.getResponseCode(),testResult);
		
	}
	
	
	@DisplayName("Service test for Hike Update till 55%")
	@Test
	public void getHikeTill55() throws Exception
	{
		
		String url="/employee/place/plce1/salary/42";
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(url)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status=result.getResponse().getStatus();
		assertEquals(responseCodeSuccessGet, status);
		String content = result.getResponse().getContentAsString();
		FinalResponse finalResponse = new Gson().fromJson(content, FinalResponse.class);				
		assertEquals(finalResponse.getResponseCode(),testResult);
		
	}
		
	
	

}

