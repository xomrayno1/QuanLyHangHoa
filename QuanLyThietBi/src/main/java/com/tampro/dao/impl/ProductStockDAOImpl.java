package com.tampro.dao.impl;
 
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.ProductStockDAO;
import com.tampro.entity.ProductInStock;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductStockDAOImpl extends BaseDAOImpl<ProductInStock>implements ProductStockDAO<ProductInStock> {

	public ProductInStock findStockByProductIdAndInventoryId(int productId, int inventoryId) {
		// TODO Auto-generated method stub
		Query query = factory.getCurrentSession()
				.createQuery(" FROM ProductInStock as model where model.product.id = :productId and model.stock.id = :stockId ");
		query.setParameter("productId", productId);
		query.setParameter("stockId", inventoryId);
		if(query.getResultList().isEmpty()) {
			return null;
		}else {
			return (ProductInStock) query.getResultList().get(0);
		}
	}

	public long getTotalProductInStock() {
		StringBuilder queryCount = new StringBuilder();
		queryCount.append("select sum(quantity) FROM ")
		.append(getGenericName()).append(" as model where model.activeFlag = 1  ");
		org.hibernate.query.Query  queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		if(queryNumber.uniqueResult() == null) {
			return 0;
		}else {
			long count = (Long) queryNumber.uniqueResult();
			return count;
		}
		 
	}

	@Override
	public long getCountProductInStockByProductAndInventory(int productId, int inventoryId) {
		// TODO Auto-generated method stub
		StringBuilder queryCount = new StringBuilder();
		queryCount.append("select sum(quantity) FROM ")
		.append(getGenericName()).append(" as model where model.activeFlag = 1  and model.product.id = :productId and model.stock.id = :stockId  ");
		org.hibernate.query.Query  queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		
		queryNumber.setParameter("productId", productId);
		queryNumber.setParameter("stockId", inventoryId);
		if(queryNumber.uniqueResult() == null) {
			return 0;
		}else {
			long count = (Long) queryNumber.uniqueResult();
			return count;
		}
	}

}
