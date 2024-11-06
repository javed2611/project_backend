package com.jk.service;

import com.jk.dto.WatiResponse;

public interface WatiService {

	public WatiResponse sendDeliveryNotification(String phno,String name, String orderTrackingNumber);

	public WatiResponse sendPaymentReminder(String phno,String name, String orderTrackingNumber);

}
