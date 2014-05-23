package edu.npu.shop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import edu.npu.shop.dao.OrderItemDAO;
import edu.npu.shop.domain.OrderItem;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {	
	@Autowired
	@Qualifier("orderItemDaoJdbc")
	private OrderItemDAO orderItemDao;
	
	public void purchaseOrderItem(OrderItem orderItem) {

		orderItemDao.insertOrderItem(orderItem);
	}	
}
