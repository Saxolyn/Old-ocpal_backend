package dev9.lapco.repository;

import dev9.lapco.entity.RandomNumber;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomNumberRepository extends CrudRepository<RandomNumber,String> {
}
