package com.mt.zt.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.zt.im.IChatGroupHandler;
import com.mt.zt.im.model.ImChatGroupModel;
import com.mt.zt.mongodb.model.ZoneInfo;
import com.mt.zt.mongodb.repository.IZoneRepository;
import com.mt.zt.service.IZoneManageService;
import com.mt.zt.vo.entry.ZoneInfoEo;

@Service("zoneManageService")
public class ZoneManageServiceImpl  implements IZoneManageService{

	@Autowired
	private  IZoneRepository zoneRepository;
	
	@Autowired
	private IChatGroupHandler chatGroupHandler;
	
	private final static String ownerName = "";
	
	@Override
	public void addZoneInfo(ZoneInfoEo zone) {
		ZoneInfo zi = new ZoneInfo();
		zi.setName(zone.getName());
		zi.setAddress(zone.getAddress());
		ImChatGroupModel group = new ImChatGroupModel();
		ImChatGroupModel model = new ImChatGroupModel();
		model.setGroupname(zone.getName());
		model.setDesc(zone.getName());
		model.setPublic(true);
		model.setApproval(true);
		model.setMaxusers(500);
		model.setOwner(ownerName);
		String groupId = chatGroupHandler.addChatGroup(group);
		zi.setImGroup(groupId);
		zoneRepository.saveZoneInfo(zi);
	}

	@Override
	public void addZoneWifi(String zoneId, String wifiMac) {
		zoneRepository.addZoneWifi(zoneId, wifiMac);
	}

	@Override
	public Map<String,Boolean> queryMarkWifis(String[] wifimacs) {
		Map<String,Boolean> marks  = new HashMap<>();
		if (!ArrayUtils.isEmpty(wifimacs)) {
			for (String mac : wifimacs) {
				ZoneInfo zone = zoneRepository.findByWifiMac(mac);
				if (zone != null) {
					marks.put(mac, true);
				}else{
					marks.put(mac, false);
				}
			}
		}
		return marks;
	}

}
