package com.tiago.calculadoraimc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;


import com.tiago.calculadoraimc.model.ImcRecord;
import com.tiago.calculadoraimc.service.ImcService;
import com.tiago.calculadoraimc.repository.ImcRecordRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imc")
public class ImcController {
//TIAGO XIMENES COSTA - Trabalho da disciplina Linguagem de Programação - Uninter - RU 3818596
    @Autowired
	private ImcRecordRepository repository;
	
    private ImcService imcService;

    public ImcController(ImcService imcService) {
        this.imcService = imcService;
    }

    @PostMapping
    public ResponseEntity<ImcRecord> calcularIMC(@RequestBody ImcRecord novoRegistro) {
        ImcRecord resultado = imcService.calcularESalvarImc(novoRegistro.getPeso(), novoRegistro.getAltura());
        return ResponseEntity.ok(resultado);
    
    }

    @GetMapping
    public ResponseEntity<List<ImcRecord>> listarRegistros() {
    	List<ImcRecord> registros = imcService.listarRegistros();
    	return ResponseEntity.ok(registros);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ImcRecord> buscarRegistroPorId(@PathVariable Long id) {
    	Optional<ImcRecord> registro = imcService.buscarRegistroPorId(id);
    	return registro.map(ResponseEntity::ok)
    	        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> deletarRegistro(@PathVariable Long id) {
        imcService.deletarRegistro(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = {"/{id}"})
    public ResponseEntity<ImcRecord> atualizarRegistro(@PathVariable Long id, @RequestBody ImcRecord novoRegistro) {
        Optional<ImcRecord> registroExistente = imcService.buscarRegistroPorId(id);
        
        if (registroExistente.isPresent()) {
            ImcRecord registro = registroExistente.get();
            
            if (novoRegistro.getPeso() != null) {
                registro.setPeso(novoRegistro.getPeso());
            }
            
            if (novoRegistro.getAltura() != null) {
                registro.setAltura(novoRegistro.getAltura());
            }
            
            double novoImc = registro.getPeso() / (registro.getAltura() * registro.getAltura());
            registro.setImc(novoImc);
            
            ImcRecord registroAtualizado = imcService.atualizarRegistro(id, registro);
            return ResponseEntity.ok(registroAtualizado);
        }
        
        return ResponseEntity.notFound().build();
    }
}


