package com.zimmur.platform.manage.web.service;

public interface IBaseService<T> {

	int add(T t);
	int update(T t);
	int delete(Integer id);
	T readById(Integer id);
}
