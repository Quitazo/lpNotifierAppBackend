package com.app.lpnotifier.backend.services;

import com.app.lpnotifier.backend.model.licitation;

import java.util.List;

public interface lpService {

    List<licitation> findLicitationByTipoDeContrato(List<String> tipo_de_contrato);

    List<String> countlicitationsByTipoDeContrato();

    List<licitation> list_all_lp();

    List<licitation> findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(List<String> tipo_de_contrato);

    List<String> countlicitationByTipoDeContrato(List<String> tiposDeContrato);
}
