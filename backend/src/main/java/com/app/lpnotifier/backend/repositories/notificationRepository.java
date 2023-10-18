package com.app.lpnotifier.backend.repositories;

import com.app.lpnotifier.backend.model.notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public interface notificationRepository extends JpaRepository<notification, String> {
    notification findNotificationByCorreo(String correo);

    @Query("SELECT p.correo FROM notification p WHERE p.compraventa = TRUE OR p.nd = TRUE OR p.obra = TRUE OR p.otros_servicios = TRUE OR p.seguros = TRUE OR p.servicios_de_aprovisionamiento = TRUE OR p.suministros = TRUE OR p.concesion = TRUE")
    ArrayList<String> findPreferencesByCorreo();

//    @Query("SELECT p.concesion, p.compraventa, p.seguros, p.nd, p.suministros, p.servicios_de_aprovisionamiento, p.obra, p.otros_servicios FROM notification p WHERE p.correo = :correo")
//    List<String> findLicitationsByCorreo(String correo);

    @Query("SELECT p.concesion, p.compraventa, p.seguros, p.nd, p.suministros, p.servicios_de_aprovisionamiento, p.obra, p.otros_servicios FROM notification p WHERE p.correo = :correo")
    List<Object[]> findLicitationsByCorreo(String correo);

    @Query("SELECT SUM(CASE WHEN n.compraventa = TRUE THEN 1 ELSE 0 END) AS compraventa," +
            " SUM(CASE WHEN n.nd = TRUE THEN 1 ELSE 0 END) AS nd," +
            " SUM(CASE WHEN n.obra = TRUE THEN 1 ELSE 0 END) AS obra," +
            " SUM(CASE WHEN n.otros_servicios = TRUE THEN 1 ELSE 0 END) AS otros_servicios," +
            " SUM(CASE WHEN n.seguros = TRUE THEN 1 ELSE 0 END) AS seguros," +
            " SUM(CASE WHEN n.servicios_de_aprovisionamiento = TRUE THEN 1 ELSE 0 END) AS servicios_de_aprovisionamiento," +
            " SUM(CASE WHEN n.suministros = TRUE THEN 1 ELSE 0 END) AS suministros," +
            " SUM(CASE WHEN n.concesion = TRUE THEN 1 ELSE 0 END) AS concesion" +
            " FROM notification n")
    List<Integer[]> countnotificationBynotification();

}
