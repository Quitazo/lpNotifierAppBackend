package com.app.lpnotifier.backend.repositories;

import com.app.lpnotifier.backend.model.licitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface licitationRepository extends JpaRepository<licitation, String> {

    @Query("SELECT l FROM licitation l WHERE l.tipoDeContrato IN (:tiposDeContrato)")
    List<licitation> findLicitationByTipoDeContrato(List<String> tiposDeContrato);

    @Query("select l.tipoDeContrato, count(*) as cantidad from licitation l group by l.tipoDeContrato order by cantidad desc")
    List<String> countlicitationsByTipoDeContrato();

    @Query("SELECT l FROM licitation l WHERE l.tipoDeContrato IN (:tiposDeContrato) ORDER BY l.fecha_publicacion DESC")
    List<licitation> findTop10ByTipoDeContratoOrderByFechaPublicacionDesc(List<String> tiposDeContrato);

    @Query("SELECT l.tipoDeContrato, COUNT(*) AS cantidad FROM licitation l WHERE l.tipoDeContrato IN (:tiposDeContrato) GROUP BY l.tipoDeContrato")
    List<String> countlicitationByTipoDeContrato(List<String> tiposDeContrato);

}
