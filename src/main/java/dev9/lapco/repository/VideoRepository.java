package dev9.lapco.repository;

import dev9.lapco.entity.VideoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends MongoRepository<VideoEntity,String> {

}
