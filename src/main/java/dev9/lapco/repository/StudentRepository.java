package dev9.lapco.repository;

import dev9.lapco.entity.StudentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<StudentEntity,String> {

    StudentEntity findByPhoneNumber(String phoneNumber);

}
