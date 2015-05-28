package com.rest.jersey.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rest.jersey.model.Stock;

@Repository("stockDao")
public class StockDaoImpl extends CustomHibernateDaoSupport implements StockDao{
 
	public void save(Stock stock){
		getHibernateTemplate().save(stock);
	}
 
	public void update(Stock stock){
		getHibernateTemplate().update(stock);
	}
 
	public void delete(Stock stock){
		getHibernateTemplate().delete(stock);
	}
 
	public Stock findByStockCode(String stockCode){
		List list = getHibernateTemplate().find(
                     "from Stock where stockCode=?",stockCode
                );
		return list.size() > 0 ? (Stock)list.get(0) : null;
	}
 
}