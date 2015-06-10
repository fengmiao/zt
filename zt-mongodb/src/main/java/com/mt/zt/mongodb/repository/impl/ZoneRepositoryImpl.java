package com.mt.zt.mongodb.repository.impl;



import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mt.zt.mongodb.model.ZoneInfo;
import com.mt.zt.mongodb.repository.IZoneRepository;

@Repository("zoneRepository")
public class ZoneRepositoryImpl extends BaseRepositoryImpl<ZoneInfo, String> implements IZoneRepository {

	@Override
	public ZoneInfo findByWifiMac(String linkWifiMac) {
		Query query = new Query().addCriteria(Criteria.where("wifis").is(linkWifiMac));
		return mongoTemplate.findOne(query, ZoneInfo.class,"zone_info");
	}

	@Override
	public void saveZoneInfo(ZoneInfo zi) {
		mongoTemplate.save(zi,"zone_info");
	}

	@Override
	public void addZoneWifi(String zoneId, String wifi) {
		Query query = getIdQuery(zoneId);
		Update update = new Update().addToSet("wifis", wifi);
		mongoTemplate.updateFirst(query, update, "zone_info");
	}

}
