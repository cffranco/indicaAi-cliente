package br.net.mecsoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.net.mecsoft.dto.IndicacaoDTO;
import br.net.mecsoft.exception.ResourceNotFoundException;
import br.net.mecsoft.model.Indicacao;
import br.net.mecsoft.repository.ClienteRepository;
import br.net.mecsoft.repository.IndicacaoRepository;
import br.net.mecsoft.response.ProfissionalResponse;

@Service
public class IndicacaoService {

	@Autowired
	private IndicacaoRepository repository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	// @Autowired
	// private ProfissaoService profissaoService;

	@Autowired
	private ModelMapper modelMapper;

	public List<IndicacaoDTO> listarIndicacoes() {
		List<Indicacao> indicacoes = repository.findAll();
		return indicacoes.stream().map(indicacao -> modelMapper.map(indicacao, IndicacaoDTO.class))
				.collect(Collectors.toList());
	}

	public Optional<IndicacaoDTO> obterIndicacao(Long id) {
		return repository.findById(id).map(indicacao -> modelMapper.map(indicacao, IndicacaoDTO.class));
	}

	public IndicacaoDTO cadastrarIndicacao(IndicacaoDTO indicacaoDTO) {
		
		//verificar se indicou 
		Indicacao verificaIndicacao = repository.verifica(indicacaoDTO.getIdCliente(),
				indicacaoDTO.getIdProfissional());
		
		if (verificaIndicacao!=null) {
			throw new ResourceNotFoundException("Você ja indicou esse profissional.");
		}
		
		//validar cliente
		Integer contCli = clienteRepository.verificaCli(indicacaoDTO.getIdCliente());
		
		if(contCli==0) {
			throw new ResourceNotFoundException("Cliente não cadastrado.");
		}
		
		//buscarProfissional
		HashMap<String, String> params = new HashMap<>();
		params.put("id",indicacaoDTO.getIdProfissional().toString());
		
		//var buscaProfissional = new RestTemplate().getForEntity("http://localhost:8100/profissionais/{id}", ProfissionalResponse.class, params);
		ProfissionalResponse buscaProfissional = new ProfissionalResponse();
		try {
			RestTemplate restTemplate = new RestTemplate();
			buscaProfissional = restTemplate.getForObject("http://localhost:8100/profissionais/{id}", ProfissionalResponse.class,params);
		}catch (HttpClientErrorException e) {
		    if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
		    	throw new ResourceNotFoundException("Profissional não cadastrado!");
		    } else {
		        // Outros códigos de status HTTP podem ser tratados conforme necessário
		    	throw new ResourceNotFoundException("Erro na requisição: " + e.getStatusCode() + " - " + e.getStatusText());
		    }
		} catch (Exception e) {
		    // Lidar com outras exceções, se necessário
			throw new ResourceNotFoundException("Erro inesperado: " + e.getMessage());
		}

		Indicacao indicacao = modelMapper.map(indicacaoDTO, Indicacao.class);
		indicacao = repository.save(indicacao);
		return modelMapper.map(indicacao, IndicacaoDTO.class);
	}

	public Optional<IndicacaoDTO> atualizarIndicacao(Long id, IndicacaoDTO indicacaoDTO) {

		Optional<Indicacao> indicacaoExistente = repository.findById(id);
		if (indicacaoExistente.isPresent()) {
			Indicacao indicacao = modelMapper.map(indicacaoDTO, Indicacao.class);
			indicacao.setId(id);
			indicacao = repository.save(indicacao);
			return Optional.of(modelMapper.map(indicacao, IndicacaoDTO.class));
		} else {
			throw new ResourceNotFoundException("Indicacao não encontrado com o ID: " + id);
		}
	}

	public void excluirIndicacao(Long id) {
		if (repository.existsById(id)) {
			repository.deleteById(id);
		} else {
			throw new ResourceNotFoundException("Indicacao não encontrado com o ID: " + id);
		}
	}

}
