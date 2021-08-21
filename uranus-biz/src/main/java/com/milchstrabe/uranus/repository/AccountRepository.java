package com.milchstrabe.uranus.repository;

import com.milchstrabe.uranus.domain.AccountType;
import com.milchstrabe.uranus.domain.po.Account;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AccountRepository {

    private static final String COLLECTION_NAME = "account";

    @Resource
    private MongoTemplate mongoTemplate;

    public void addAccount(Account account){
        mongoTemplate.save(account, COLLECTION_NAME);
    }

    public Account findAccountBy(AccountType type,String account){
        Query query = Query.query(Criteria.where(type.name().toLowerCase()).is(account));
        return mongoTemplate.findOne(query, Account.class, COLLECTION_NAME);
    }


    public Account findAccountById(String id){
        Query query = Query.query(Criteria.where("id").is(id));
        Account account = mongoTemplate.findOne(query, Account.class, COLLECTION_NAME);
        return account;
    }
}
