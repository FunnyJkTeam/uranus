package com.milchstrabe.uranus.repository;

import com.milchstrabe.uranus.domain.po.Device;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class DeviceRepository {

    private static final String COLLECTION_NAME = "device";

    @Resource
    private MongoTemplate mongoTemplate;

    public Device findDeviceByDeviceId(String deviceId){
        Query query = Query.query(Criteria.where("deviceId").is(deviceId));
        Device device = mongoTemplate.findOne(query, Device.class);
        return device;
    }

    public void addDevice(Device device){
        Device isSuccess = mongoTemplate.save(device, COLLECTION_NAME);
    }


}
