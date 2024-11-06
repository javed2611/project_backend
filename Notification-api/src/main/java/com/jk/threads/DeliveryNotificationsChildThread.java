package com.jk.threads;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import com.jk.entity.Order;
import com.jk.listner.SpringContext;
import com.jk.util.NotificationUtil;

public class DeliveryNotificationsChildThread implements Callable<List<Order>>{
	
	private List<Order> orders;
	
	private NotificationUtil getNotificationsUtility() {
        return SpringContext.getBean(NotificationUtil.class);
    }
	
	@Override
	public List<Order> call() throws Exception {
		sendDeliveryNotifications(orders);
		return orders;
	}

	private void sendDeliveryNotifications(List<Order> orders) {
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			for (Order order : orders) {
				getNotificationsUtility().sendDeliveryNotification(order);
			}
		}
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
