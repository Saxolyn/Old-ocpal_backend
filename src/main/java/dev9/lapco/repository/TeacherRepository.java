package dev9.lapco.repository;

import dev9.lapco.entity.TeacherEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<TeacherEntity,String> {
}
