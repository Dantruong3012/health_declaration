package com.dantruong.health_declarations.connection;

import com.dantruong.health_declarations.entity.HealthDeclarant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HealthDeclarantRepo extends JpaRepository<HealthDeclarant, Integer> {
}
