package com.algaworks.algafood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.exception.ClientApiException;
import com.algaworks.algafood.client.model.RestauranteModel;
import com.algaworks.algafood.client.model.input.RestauranteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteClient {

	private static final String RESOURCE_PATH = "/restaurantes";

	private String baseUrl;

	private RestTemplate restTemplate;

	public List<RestauranteModel> listar() {

		try {
			URI resourceUri = URI.create(baseUrl + RESOURCE_PATH);

			RestauranteModel[] restaurantesArray = restTemplate.getForObject(resourceUri, RestauranteModel[].class);

			return Arrays.asList(restaurantesArray);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}

	public RestauranteModel buscarPorId(Long restauranteId) {
		try {

			URI resourceUri = URI.create(baseUrl + RESOURCE_PATH + "/" + restauranteId);

			RestauranteModel restauranteModel = restTemplate.getForObject(resourceUri, RestauranteModel.class);

			return restauranteModel;

		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}

	public RestauranteModel cadastrar(RestauranteInput restauranteInput) {

		try {
			URI resourceUri = URI.create(baseUrl + RESOURCE_PATH);
			RestauranteModel restaurante = restTemplate.postForObject(resourceUri, restauranteInput,
					RestauranteModel.class);

			return restaurante;
		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}

	}

	public void atualizar(Long idRestaurante, RestauranteInput restauranteInput) {

		try {

			URI resourceUri = URI.create(baseUrl + RESOURCE_PATH + "/" + idRestaurante);
			restTemplate.put(resourceUri, restauranteInput);

		} catch (HttpClientErrorException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}

}
