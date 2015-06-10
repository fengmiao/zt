package com.mt.zt.mongodb.repository.addition.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.mongodb.core.aggregation.AggregationOperation;

public class AggregationOperationBulider {

	protected final List<AggregationOperation> operations;
	
	public AggregationOperationBulider(){
		operations = new ArrayList<AggregationOperation>();
	}
	
	public AggregationOperationBulider(AggregationOperation... operations){
		this.operations = new ArrayList<AggregationOperation>();
		this.operations.addAll(Arrays.asList(operations));
	}
	
	public void add(AggregationOperation operation){
		this.operations.add(operation);
	}
	
	public List<AggregationOperation> get(){
		return operations;
	}
	
}
