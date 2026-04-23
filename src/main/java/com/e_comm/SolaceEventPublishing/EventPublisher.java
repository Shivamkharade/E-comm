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
	
	public void publishLogInEvent(EventSent event) {
		streamBridge.send("userJwtLogin-out-0", event);
	}
	
	public void publishRegistrationEvent(EventSent event) {
		streamBridge.send("userRegistrationMail-out-0", event);
	}
}
