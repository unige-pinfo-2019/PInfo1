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

class ItemRestServiceTest {

	private final static String baseUrl = "http://localhost:10080/item/" ;
	private final static String usrId = "usrId";
	private final static String name = "name";
	private final static String price = "price";
	private final static String category = "category";
	private final static String description = "description";
	private final static String state = "state";
	private static HttpClient client ;
	
	
	@BeforeAll
	static void setup() {
		client = HttpClients.createDefault();
	}
	
	@Test
	void getBySearchRestTest() {
		String url = baseUrl + "s/1" ;
		check(url, "[]", true) ;
	}
	
	@Test
	void addItemRestTest() {
		String url = baseUrl ;
		JSONObject json = new JSONObject();
		json.put(usrId, "1234");
		json.put(name, "étagère");
		json.put(price, "200");
		json.put(category, "mobilier");
		json.put(description, "un mobilier");
		json.put(state, "neuf");
		check(url, "[]", json.toString(), true) ;
	}
	
	@Test
	void updateItemRestTest() {
		String url = baseUrl + "updateitem" ;
		JSONObject json = new JSONObject();
		
		check(url, "[]", json.toString(), false) ;
	}
	
	@Test
	void removeItemRestTest() {
		String url = baseUrl + "removeitem" ;
		JSONObject json = new JSONObject();
		
		check(url, "[]", json.toString(), false) ;
	}
	
	@Test
	void highlightRestTest() {
		String url = baseUrl + "highlight?usrid=u123" ;
		check(url, "[]", false) ;
	}
	
	@Test
	void allItemRestTest() {
		String url = baseUrl + "allitem" ;
		check(url, "[]", true) ;
	}
	
	@Test
	void getItemRestTest() {
		String url = baseUrl + "getitem?usrid=u123" ;
		check(url, "[]", true) ;
	}
	
	@Test
	void getItemIDRestTest() {
		String url = baseUrl + "getitemID?id=i123" ;
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
