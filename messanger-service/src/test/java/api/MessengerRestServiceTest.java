package api;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

class MessengerRestServiceTest {

	private final static String baseUrl = "http://localhost:13080/messenger/" ;
	private static HttpClient client ;
	
	
	@BeforeAll
	static void setup() {
		client = HttpClients.createDefault();
	}
	
	@Test
	void allMessengerRestTest() {
		String url = baseUrl + "allmessenger";
		check(url, "[]");
	}
	
	@Test
	void addMessengerRestTest() {
		String url = baseUrl ;
		JSONObject json = new JSONObject();
		
		check(url, "[]", json.toString()) ;
	}
	
	@Test
	void getMessengerRestTest() {
		String url = baseUrl + "getmessenger?sendId=u123&receiveId=u124";
		check(url, "[]");
	}
	
	@Test
	void getInfoRestTest() {
		String url = baseUrl + "getinfo?usrId=u123";
		check(url, "[]");
	}
	
	
	private void check(String url, String expected) {
		HttpGet request = new HttpGet(url);
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
	
	private void check(String url, String expected, String jsonStr) {
		try {
			StringEntity strEnt = new StringEntity(jsonStr);
			strEnt.setContentType(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
			HttpPost request = new HttpPost(url);
			request.setEntity(strEnt);
			request.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
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
