package com.milchstrabe.uranus.repository;

import com.milchstrabe.uranus.domain.po.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {

    private static final String COLLECTION_NAME = "refresh_token";

    @Autowired
    private MongoTemplate mongoTemplate;

    public RefreshToken findRefreshTokenByAccountId(Long accountId){
        Query query = Query.query(Criteria.where("id").is(accountId));
        RefreshToken refreshToken = mongoTemplate.findOne(query, RefreshToken.class, COLLECTION_NAME);
        return refreshToken;
    }

    public void addRefreshToken(RefreshToken refreshToken){
        mongoTemplate.save(refreshToken,COLLECTION_NAME);
    }
}
