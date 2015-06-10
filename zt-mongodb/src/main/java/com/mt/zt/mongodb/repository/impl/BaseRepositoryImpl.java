package com.mt.zt.mongodb.repository.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mt.zt.mongodb.repository.addition.impl.AggregationOperationBulider;

public abstract class BaseRepositoryImpl<T, ID extends Serializable> {

	@Autowired
	protected MongoTemplate mongoTemplate;
	
	public Query getIdQuery(ID id) {
		Query query = new Query();
		ObjectId oid = new ObjectId(String.valueOf(id));
		query.addCriteria(Criteria.where("_id").is(oid));
		return query;
	}
	
	protected Query bulidPageQuery(Integer limit,Integer skip){
		Query query = new Query();
		if (limit != null) {
			query.limit(limit);
		}
		if (skip != null) {
			query.skip(skip);
		}
		return query;
	}

	/**
	protected Query getIdQuery(String keyId, ID id) {
		BasicDBObject key = null;
		ObjectId oid = new ObjectId(String.valueOf(id));
		if (keyId == null) {
			key = new BasicDBObject("_id", oid);
		} else {
			key = new BasicDBObject(keyId, id);
		}
		Query q = new BasicQuery(key);
		return q;
	}
	**/

	public T getById(ID id, Class<T> entityClass) {
		T c = mongoTemplate.findOne(getIdQuery(id), entityClass);
		return c;
	}

	protected AggregationOperationBulider buildAggreationForQueryInnerDocument(
			AggregationOperation mid, AggregationOperation u,
			AggregationOperation minner, AggregationOperation sort,
			AggregationOperation p, Integer limit, Object cusor) {
		AggregationOperationBulider ab = null;
		if (cusor == null) {
			ab = new AggregationOperationBulider(mid, u, sort, p);
		} else {
			ab = new AggregationOperationBulider(mid, u, minner, sort, p);
		}
		if (limit != null) {
			AggregationOperation l = limit(limit);
			ab.add(l);
		}
		return ab;
	}

	protected void push(ID id, Class<?> entityClass,String property,Object object){
		Query query = getIdQuery(id);
		Update push = new Update().push(property, object);
		mongoTemplate.updateFirst(query, push, entityClass);
	}
	
	protected void pull(ID id, Class<?> entityClass,String property,Object object){
		Query query = getIdQuery(id);
		Update pull = new Update().pull(property, object);
		mongoTemplate.updateFirst(query, pull, entityClass);
	}
	
	protected void pushFixed(BasicDBObject[] objects,Integer count,String property,Class<?> entityClass,Query query){
		BasicDBObject array = new BasicDBObject();
		array.put("$each", objects);
		array.append("$slice", -count);
		BasicDBObject bp = new BasicDBObject();
		BasicDBObject rb = new BasicDBObject();
		rb.append(property, array);
		bp.put("$push", rb);
		Update p = new BasicUpdate(bp);
		mongoTemplate.updateFirst(query, p, entityClass);
	}
	
}
