package com.tiago.calculadoraimc.service;

import com.tiago.calculadoraimc.model.ImcRecord;
import com.tiago.calculadoraimc.repository.ImcRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImcServiceImpl implements ImcService {

    private ImcRecordRepository imcRecordRepository;

    @Autowired
    public ImcServiceImpl(ImcRecordRepository imcRecordRepository) {
        this.imcRecordRepository = imcRecordRepository;
    }

    @Override
    public ImcRecord calcularESalvarImc(Double peso, Double altura) {
        double imc = peso / (altura * altura);

        ImcRecord imcRecord = new ImcRecord();
        imcRecord.setPeso(peso);
        imcRecord.setAltura(altura);
        imcRecord.setImc(imc);

        return imcRecordRepository.save(imcRecord);
    }

    @Override
    public List<ImcRecord> listarRegistros() {
        return imcRecordRepository.findAll();
    }

    @Override
    public Optional<ImcRecord> buscarRegistroPorId(Long id) {
        return imcRecordRepository.findById(id);
    }

    @Override
    public void deletarRegistro(Long id) {
        imcRecordRepository.deleteById(id);
    }

    @Override
    public ImcRecord atualizarRegistro(Long id, ImcRecord novoRegistro) {
        Optional<ImcRecord> registroExistente = imcRecordRepository.findById(id);
        if (registroExistente.isPresent()) {
            ImcRecord registro = registroExistente.get();
            registro.setPeso(novoRegistro.getPeso());
            registro.setAltura(novoRegistro.getAltura());
            registro.setImc(novoRegistro.getImc());
            
            return imcRecordRepository.save(registro);
        }
        return null;
    }
}



