package dev9.lapco.repository;

import dev9.lapco.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    @Query(value =  "{$and: [{'username' : ?0, 'password': ?1}]}")
    AccountEntity findAccountByCondition (String username, String password);

    @Query("{$and:[{'phone_number' : ?0, 'isDelete': ?1}]}")
    Optional<AccountEntity>  findAccount (String phoneNumber, Boolean isDelete);

    @Query("{'isDelete': ?0}")
    List<AccountEntity> findAll (Boolean isDelete);


    @Query("{ 'phone_number' : ?0 }")
    @Update("{ '$set' : { 'isDelete' : true } }")
    void softDelete(String phoneNumber);
}
