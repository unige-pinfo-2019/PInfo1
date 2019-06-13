package test.java.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class StatisticRestServiceTest {
	
	private final static String baseUrl = "http://localhost:14080/statistic/" ;
	private static HttpClient client ;
	
	
	@BeforeAll
	public static void setup() {
		client = HttpClients.createDefault();
	}
	
	@Test
	public void getUserTest() {
		String url = baseUrl + "getuser?usrid=u123" ;
		check(url, "Error : there is no user with id u123", false) ;
	}

	@Test
	public void getItemTest() {
		String url = baseUrl + "getitem?itemid=i123" ;
		check(url, "Error : there is no item with id i123", false) ;
	}
	
	@Test
	public void allUserTest() {
		String url = baseUrl + "alluser" ;
		check(url, "[]", true) ;
	}
	
	@Test
	public void allItemTest() {
		String url = baseUrl + "allitem" ;
		check(url, "[]", true) ;
	}
	
	@Test
	public void topCatTest() {
		String url = baseUrl + "topcat?ncategories=3" ;
		check(url, "[\"LIVRE\",\"MOBILITE\",\"ELECTRONIQUE\"]", true) ;
	}
	
	@Test
	public void topUserCatTest() {
		String url = baseUrl + "topusercat?usrid=u123&ncategories=3" ;
		check(url, "[]", true) ;
	}
	
	@Test
	public void topItemCatTest() {
		String url = baseUrl + "topitemcat?category=MOBILIER&nitems=8" ;
		check(url, "[]", true) ;
	}
	
	@Test
	public void topItemTest() {
		String url = baseUrl + "topitem?nitems=8" ;
		check(url, "[]", true) ;
	}
	
	private void check(String url, String expected, boolean isJson) {
		HttpGet request = new HttpGet(url);
		if (!isJson)
			request.setHeader(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);
		else
			request.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON);
		try {
			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity() ;
			BufferedReader rd = null ;
			if (entity != null)
				rd = new BufferedReader(new InputStreamReader(entity.getContent()));
			String line = null ;
			if (rd != null)
				line = rd.readLine();
			if (line != null)
				assertEquals(expected, line);
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
	}

}
