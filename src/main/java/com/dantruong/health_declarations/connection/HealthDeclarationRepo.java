package com.dantruong.health_declarations.connection;

import com.dantruong.health_declarations.entity.HealthDeclaration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthDeclarationRepo extends JpaRepository<HealthDeclaration, Integer> {
    HealthDeclaration findByHealthDeclarantId(Integer id);
    List<HealthDeclaration> findAllByHealthDeclarantId(Integer id);
}
