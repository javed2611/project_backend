package com.jk.threads;


import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import com.jk.entity.Order;
import com.jk.listner.SpringContext;
import com.jk.util.PaymentReminder;

public class PaymentRemindersChildThread implements Callable<List<Order>>{
	
	private List<Order> orders;
	
	private PaymentReminder getPaymentReminderUtility() {
        return SpringContext.getBean(PaymentReminder.class);
    }
	
	@Override
	public List<Order> call() throws Exception {
		sendReminder(orders);
		return orders;
	}

	private void sendReminder(List<Order> orders) {
		if(Objects.nonNull(orders) && !orders.isEmpty()) {
			for (Order order : orders) {
				getPaymentReminderUtility().sendPaymentReminder(order);
			}
		}
	}
	
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
