package com.e_comm.SolaceEventPublishing;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import com.sun.jdi.event.EventSet;


@Service
public class EventPublisher {

	private final StreamBridge streamBridge;
	
	public EventPublisher(StreamBridge streamBridge1) {
		this.streamBridge = streamBridge1;
	}
	
	public void publishLogInEvent(RegistrationEventSent event) {
		streamBridge.send("userJwtLogin-out-0", event);
	}
	
	public void publishRegistrationEvent(RegistrationEventSent event) {
		streamBridge.send("userRegistrationMail-out-0", event);
	}
	
	public void publishOrderConformationEvent(OrderConformationEventSent eventSent) {

	    boolean sent = streamBridge.send("orderConformation-out-0", eventSent);

	    System.out.println("SENT STATUS = " + sent);
	}
}
