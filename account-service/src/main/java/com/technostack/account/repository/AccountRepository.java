package com.technostack.account.repository;

import ma.baridmedia.imdae.account.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends MongoRepository<Account,String > {
    Account findByUsername(String username);
}
