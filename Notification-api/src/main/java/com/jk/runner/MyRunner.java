package com.jk.runner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jk.batch.NotificationsBatch;


@Component
public class MyRunner implements CommandLineRunner{

	@Autowired
	NotificationsBatch notificationBatch;
	
	@Override
	public void run(String... args) throws Exception {
		notificationBatch.sendDeliveryNotifications();
		//notificationBatch.sendPaymentReminder();
	}

}
