package com.test.quest.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.security.cert.X509Certificate;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.test.quest.dto.FoodDTO;

public class FoodProvider {
	private static final Logger log = LogManager.getLogger(FoodProvider.class);

	private String baseUrl;

	public List<FoodDTO> getFoodListFromProvider(String vendorType) throws URISyntaxException, Exception {
		List<FoodDTO> foodList = new ArrayList<>();
		switch (vendorType) {
		case "fruit":
			baseUrl = "https://942384f8-3bde-4c28-afc1-d0d3b7045786.mock.pstmn.io/fruits";
			break;
		case "vegetable":
			baseUrl = "https://6f09336b-9208-4385-ab15-61362072f2d1.mock.pstmn.io/vegetables";
			break;
		case "grain":
			baseUrl = "https://e97131ec-e35b-48d3-9604-adb51e0a93b3.mock.pstmn.io/grains";
			break;

		default:
			break;
		}
		try {
			ResponseEntity<FoodDTO[]> result = getRestTemplate().getForEntity(new URI(baseUrl), FoodDTO[].class);

			if (result.getStatusCode().equals(HttpStatus.OK)) {
				log.info("Food provider api response: {}", result.getBody());
				foodList = Arrays.asList(result.getBody());
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error occured while calling Food Provider API : {}", e);
		}
		return foodList;

	}

	public RestTemplate getRestTemplate() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {

		TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		restTemplate.setMessageConverters(messageConverters);

		return restTemplate;
	}
}
