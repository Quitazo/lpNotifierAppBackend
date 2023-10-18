package com.app.lpnotifier.backend.services.impl;

import com.app.lpnotifier.backend.model.licitation;
import com.app.lpnotifier.backend.repositories.licitationRepository;
import com.app.lpnotifier.backend.services.lpService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class licitationServiceImp implements lpService {

    private final licitationRepository licitationRepository;

    @Override
    public List<licitation> findLicitationByTipoDeContrato(List<String> tipo_de_contrato){
        return licitationRepository.findLicitationByTipoDeContrato(tipo_de_contrato);
    }

    @Override
    public List<String> countlicitationsByTipoDeContrato(){
        return licitationRepository.countlicitationsByTipoDeContrato();
    }

    @Override
    public List<licitation> list_all_lp(){
        return licitationRepository.findAll();
    }

    @Override
    public List<licitation> findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(List<String> tipo_de_contrato){
        return licitationRepository.findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(tipo_de_contrato);
    }

    @Override
    public List<String> countlicitationByTipoDeContrato(List<String> tiposDeContrato){
        return licitationRepository.countlicitationByTipoDeContrato(tiposDeContrato);
    }

}
