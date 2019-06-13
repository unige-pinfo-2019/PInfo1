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
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

class UserRestServiceTest {

	private final static String baseUrl = "http://localhost:12080/user/" ;
	private static HttpClient client ;
	
	
	@BeforeAll
	static void setup() {
		client = HttpClients.createDefault();
	}
	
	@Test
	void allUserRestTest() {
		String url = baseUrl + "alluser";
		check(url, "[]");
	}
	
	@Test
	void getUserRestTest() {
		String url = baseUrl + "getuser?id=u123";
		check(url, "[]");
	}
	
	@Test
	void addUserRestTest() {
		String url = baseUrl ;
		JSONObject json = new JSONObject();
		check(url, "[]", json.toString(), true) ;
	}
	
	@Test
	void incrementReportRestTest() {
		String url = baseUrl ;
		JSONObject json = new JSONObject();
		
		check(url, "[]", json.toString(), false) ;
	}
	
	@Test
	void updateImageRestTest() {
		String url = baseUrl ;
		JSONObject json = new JSONObject();
		
		check(url, "[]", json.toString(), false) ;
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
	
	private void check(String url, String expected, String jsonStr, boolean isPost) {
		try {
			StringEntity strEnt = new StringEntity(jsonStr);
			strEnt.setContentType(new BasicHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON));
			HttpRequestBase request = null ;
			if (isPost) {
				request = new HttpPost(url);
				((HttpPost)request).setEntity(strEnt);
			}
			else {
				request = new HttpPut(url);
				((HttpPut)request).setEntity(strEnt);
			}
			
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
