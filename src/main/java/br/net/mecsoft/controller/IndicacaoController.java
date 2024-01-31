package br.net.mecsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.mecsoft.dto.IndicacaoDTO;
import br.net.mecsoft.service.IndicacaoService;

@RestController
@RequestMapping("/indicacao")
public class IndicacaoController {

	@Autowired
	private IndicacaoService service;
	
	@GetMapping
    public ResponseEntity<List<IndicacaoDTO>> listarIndicacoes() {
        List<IndicacaoDTO> indicacoes = service.listarIndicacoes();
        return new ResponseEntity<>(indicacoes, HttpStatus.OK);
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<IndicacaoDTO> obterIndicacao(@PathVariable Long id) {
        return service.obterIndicacao(id)
                .map(indicacao -> new ResponseEntity<>(indicacao, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<IndicacaoDTO> cadastrarIndicacao(@RequestBody IndicacaoDTO clienteDTO) {
        IndicacaoDTO novaIndicacao = service.cadastrarIndicacao(clienteDTO);
        return new ResponseEntity<>(novaIndicacao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IndicacaoDTO> atualizarIndicacao(@PathVariable Long id, @RequestBody IndicacaoDTO clienteDTO) {
        IndicacaoDTO indicacao = service.atualizarIndicacao(id, clienteDTO)
                .orElse(null);

        if (indicacao != null) {
            return new ResponseEntity<>(indicacao, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirIndicacao(@PathVariable Long id) {
        service.excluirIndicacao(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @GetMapping("/nindicacao/{id}")
    public Integer contarIndicacao(@PathVariable Long id) {
        return service.contarIndicacao(id);
                
    }
}
