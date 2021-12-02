package com.algaworks.algafood.client.application;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.RestauranteClient;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {

		RestTemplate restTemplate = new RestTemplate();

		RestauranteClient client = new RestauranteClient("http://localhost:8080", restTemplate);

		//exibir lista de restaurantes
		client.listar().stream().forEach((restaurante) -> System.out.println(restaurante));
	}
}
