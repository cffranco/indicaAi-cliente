package br.net.mecsoft.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.net.mecsoft.dto.ClienteDTO;
import br.net.mecsoft.exception.ResourceNotFoundException;
import br.net.mecsoft.model.Cliente;
import br.net.mecsoft.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
    private ClienteRepository repository;

   // @Autowired
   // private ProfissaoService profissaoService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = repository.findAll();
        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<ClienteDTO> obterCliente(Long id) {
        return repository.findById(id)
                .map(cliente -> modelMapper.map(cliente, ClienteDTO.class));
    }

    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
    	
    	Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
        cliente = repository.save(cliente);
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public Optional<ClienteDTO> atualizarCliente(Long id, ClienteDTO clienteDTO) {
    	
        Optional<Cliente> clienteExistente = repository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = modelMapper.map(clienteDTO, Cliente.class);
            cliente.setId(id);
            cliente = repository.save(cliente);
            return Optional.of(modelMapper.map(cliente, ClienteDTO.class));
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
    }

    public void excluirCliente(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Cliente não encontrado com o ID: " + id);
        }
    }

}
