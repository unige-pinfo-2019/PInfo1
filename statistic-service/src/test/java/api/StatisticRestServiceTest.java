package test.java.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import api.StatisticRestService;
import org.mockito.InjectMocks;

//import io.restassured.RestAssured;

@ExtendWith(MockitoExtension.class)
public class StatisticRestServiceTest {

	@InjectMocks
	private StatisticRestService statsRestService;
	
	/*
	@BeforeAll
	public static void setup() {
		
		RestAssured.baseURI = "http://localhost:28080/counterparties";

		RestAssured.port = 8080;

	}*/
	
	@Test
	void getUserStatsRestTest() {
		
	}

}
