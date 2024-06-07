package dev9.lapco.repository;

import dev9.lapco.entity.AccountEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<AccountEntity, String> {

    @Query(value =  "{$and: [{'username' : ?0, 'password': ?1}]}")
    AccountEntity findAccountByCondition (String username, String password);

    @Query("{'phone_number' : ?0}")
    Optional<AccountEntity>  findAccount (String phoneNumber);

    //Update password field
    @Query("{'_id' : ?1}")
    @Update("{'$password': ?0}")
    void updateByPassword(String password, String id);

}
