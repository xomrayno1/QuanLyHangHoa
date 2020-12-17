package com.tampro.dao.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.hibernate.query.Query;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.InvoiceDAO;
import com.tampro.entity.Invoice;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InvoiceDAOImpl	extends BaseDAOImpl<Invoice> implements InvoiceDAO<Invoice> {

	public long getCountInvoiceByNowDate(int type) {
		// TODO Auto-generated method stub
		StringBuilder queryCount = new StringBuilder();
		queryCount.append("SELECT COUNT(*) FROM ")
		.append(getGenericName()).append(" as model where model.activeFlag = 1  ")
		.append(" and Year(model.createDate) =:year ");
		 
		if(type != 0) {
			queryCount.append(" and model.type =:type ");
			
		}
		Query queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		queryNumber.setParameter("year", LocalDateTime.now().getYear());
		 
		if(type != 0) {
			queryNumber.setParameter("type",  type);
			
		}
		long count = (Long) queryNumber.uniqueResult();
		
		
		return count;
	}

	@Override
	public List<Map<String , Object>>  getBarChart(int type , int year) {
		// TODO Auto-generated method stub
		String Sql = " SELECT month(i.createDate) as month , sum(i.price * i.quantity) as price  FROM invoice as i  "
				+ " where year(i.createDate)  = ? and i.type = ? group by month(i.createDate) "
				+ " order by (month) asc ";
		//List<Map<String , BigDecimal>> barCharts = new ArrayList<Map<String,BigDecimal>>();
		Query  query = factory.getCurrentSession().createNativeQuery(Sql);
		query.setParameter(1, year);
		query.setParameter(2, type);
		List<Map<String , Object>> barCharts  = 
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
		
		return barCharts ;
	}

	@Override
	public List<Map<String, Object>> getCountBarChart(int type, int year) {
		// TODO Auto-generated method stub
		String Sql = " SELECT month(i.createDate) as month , count(*) as count  FROM invoice as i  "
				+ " where year(i.createDate)  = ? and i.type = ? group by month(i.createDate) "
				+ " order by (month) asc ";
		Query  query = factory.getCurrentSession().createNativeQuery(Sql);
		query.setParameter(1, year);
		query.setParameter(2, type);
		List<Map<String , Object>> barCharts  = 
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
		return barCharts ;
	}
	@Override
	public List<Map<String, Object>> getPieChart(int type, int year) {
		// TODO Auto-generated method stub
		String Sql = " SELECT count(*) as count,p.code as code FROM invoice as i  "
				+ " inner join product p  on p.id = i.product_id "
				+ " where year(i.createDate)  = ? and i.type = ? "
				+ " group by p.name order by  count  desc limit 10 ";
		Query  query = factory.getCurrentSession().createNativeQuery(Sql);
		query.setParameter(1, year);
		query.setParameter(2, type);
		List<Map<String , Object>> pieCharts  = 
				query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE).list();
		return pieCharts ;
	}
	 

}
