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

    @Query(value = "{'phone_number' : ?0,'isDeleted': false}")
    Optional<AccountEntity>  findAccount (String phoneNumber);

    @Query(value = "{'isDeleted': false}")
    List<AccountEntity> findAllAccount ();

    @Query("{ 'phone_number' : ?0 }")
    @Update("{ '$set' : { 'isDeleted' : true } }")
    void softDelete(String phoneNumber);

    Optional<AccountEntity> findAccountByPhoneNumberAndIsLocked(String phoneNumber, Boolean isLock);
}
