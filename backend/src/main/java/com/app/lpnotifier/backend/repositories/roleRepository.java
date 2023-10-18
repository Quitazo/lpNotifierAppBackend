package com.app.lpnotifier.backend.repositories;

import com.app.lpnotifier.backend.model.rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface roleRepository extends JpaRepository<rol, Long> {
    rol findRolById(Long id);
}
