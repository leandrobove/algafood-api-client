package com.algaworks.algafood.client.application;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.api.exception.ClientApiException;

public class ListagemRestaurantesMain {

	public static void main(String[] args) {

		try {

			RestTemplate restTemplate = new RestTemplate();

			RestauranteClient client = new RestauranteClient("http://localhost:8080", restTemplate);

			// exibir lista de restaurantes
			client.listar().stream().forEach((restaurante) -> System.out.println(restaurante));

		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
}
