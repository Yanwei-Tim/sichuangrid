package com.tianque.mongodb.demo;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;

//@Repository("hotelDAO")
public class HotelDAO<T> {
	// @Autowired
	private Datastore datastore;

	public Key<T> save(T entity) {
		return datastore.save(entity);
	}

	public List<T> find(Class<T> clazz) {
		return (List<T>) datastore.find(clazz).offset(0).limit(5).asList();
	}
}
