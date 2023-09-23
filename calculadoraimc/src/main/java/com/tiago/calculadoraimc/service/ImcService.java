package com.tiago.calculadoraimc.service;

import com.tiago.calculadoraimc.model.ImcRecord;
import java.util.List;
import java.util.Optional;

public interface ImcService {
    ImcRecord calcularESalvarImc(Double peso, Double altura);
    List<ImcRecord> listarRegistros();
    Optional<ImcRecord> buscarRegistroPorId(Long id);
    void deletarRegistro(Long id);
    ImcRecord atualizarRegistro(Long id, ImcRecord novoRegistro);
}



