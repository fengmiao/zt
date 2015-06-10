package com.mt.zt.vo.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class RspResultVo<T> {

	// private String method;

	// private String uri;

	// private String path;

	// private Map<String, Object> params;

	private List<T> entities;

	private Date timestamp;

	// private long duration;

	private ErrorVo error;

	private int count;

	private Integer skip;

	// private String cusor;

	private boolean success;

	/**
	 * public RspResultVo(HttpServletRequest request){ this.timestamp = new
	 * Date(); //this.params = new HashMap<String, Object>(); this.entities =
	 * new ArrayList<>(); //this.method = request.getMethod(); //this.uri =
	 * request.getServletPath(); this.success = true; }
	 **/

	public RspResultVo() {
		this.timestamp = new Date();
		// this.params = new HashMap<String, Object>();
		this.entities = new ArrayList<>();
		this.success = false;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public void addEntity(List<T> objs) {
		if (CollectionUtils.isNotEmpty(objs)) {
			this.entities.addAll(objs);
			this.count = this.count + objs.size();
		}
	}

	public void addOneEntity(T obj) {
		if (obj != null) {
			this.entities.add(obj);
			this.count = this.count + 1;
		}
	}

	public ErrorVo getError() {
		return error;
	}

	public void setError(ErrorVo error) {
		this.error = error;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

}
