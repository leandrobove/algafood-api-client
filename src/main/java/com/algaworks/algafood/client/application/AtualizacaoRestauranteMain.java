package com.algaworks.algafood.client.application;

import org.springframework.web.client.RestTemplate;

import com.algaworks.algafood.client.api.RestauranteClient;
import com.algaworks.algafood.client.api.exception.ClientApiException;
import com.algaworks.algafood.client.model.RestauranteModel;
import com.algaworks.algafood.client.model.input.CidadeIdInput;
import com.algaworks.algafood.client.model.input.CozinhaIdInput;
import com.algaworks.algafood.client.model.input.EnderecoInput;
import com.algaworks.algafood.client.model.input.RestauranteInput;

public class AtualizacaoRestauranteMain {

	public static void main(String[] args) {

		try {
			
			RestTemplate restTemplate = new RestTemplate();

			RestauranteClient client = new RestauranteClient("http://localhost:8080", restTemplate);

			// buscar restaurante id 1
			RestauranteModel restauranteAtual = client.buscarPorId(1L);

			// atualizar nome do restaurante com id 1
			RestauranteInput restauranteInput = new RestauranteInput();
			restauranteInput.setNome("Nome restaurante atualizado!");
			restauranteInput.setTaxaFrete(restauranteAtual.getTaxaFrete());

			CozinhaIdInput cozinhaIdInput = new CozinhaIdInput();
			cozinhaIdInput.setId(restauranteAtual.getCozinha().getId());
			restauranteInput.setCozinha(cozinhaIdInput);

			EnderecoInput enderecoInput = new EnderecoInput();
			enderecoInput.setBairro(restauranteAtual.getEndereco().getBairro());
			enderecoInput.setCep(restauranteAtual.getEndereco().getCep());
			enderecoInput.setComplemento(restauranteAtual.getEndereco().getComplemento());
			enderecoInput.setLogradouro(restauranteAtual.getEndereco().getLogradouro());
			enderecoInput.setNumero(restauranteAtual.getEndereco().getNumero());

			CidadeIdInput cidadeIdInput = new CidadeIdInput();
			cidadeIdInput.setId(restauranteAtual.getEndereco().getCidade().getId());
			
			enderecoInput.setCidade(cidadeIdInput);

			restauranteInput.setEndereco(enderecoInput);

			client.atualizar(1L, restauranteInput);

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
