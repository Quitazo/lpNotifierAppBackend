package com.app.lpnotifier.backend.repositories;

import com.app.lpnotifier.backend.model.urlPreferences;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface urlPreferencesRepository extends JpaRepository<urlPreferences, String> {

    @Query("SELECT u.concatenacion FROM urlPreferences u WHERE u.correo = :correo")
    String findConcatenacionByCorreo(@Param("correo") String correo);

}
