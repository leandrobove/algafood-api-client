package com.algaworks.algafood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.model.RestauranteResumoModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";

	private String baseUrl;

	private RestTemplate restTemplate;

	public List<RestauranteResumoModel> listar() {

		URI resourceUri = URI.create(baseUrl + RESOURCE_PATH);

		RestauranteResumoModel[] restaurantesArray = restTemplate.getForObject(resourceUri,
				RestauranteResumoModel[].class);

		return Arrays.asList(restaurantesArray);
	}

}