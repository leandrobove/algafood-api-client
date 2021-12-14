package com.algaworks.algafood.client.application;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.api.exception.ClientApiException;
import com.algaworks.algafood.client.model.RestauranteModel;

public class BuscaRestaurantePorIdMain {

	public static void main(String[] args) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			RestauranteClient client = new RestauranteClient("http://localhost:8080", restTemplate);

			// buscar restaurante id 1
			RestauranteModel restauranteAtual = client.buscarPorId(1L);
			
			System.out.println(restauranteAtual);
			
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());

				e.getProblem().getObjects().stream().forEach(p -> System.out.println("- " + p.getUserMessage()));

			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}

	}
}
