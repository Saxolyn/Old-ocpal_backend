package dev9.lapco.repository;

import dev9.lapco.constant.ERole;
import dev9.lapco.entity.RoleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<RoleEntity, String> {
    Optional<RoleEntity> findByRole(ERole name);
}