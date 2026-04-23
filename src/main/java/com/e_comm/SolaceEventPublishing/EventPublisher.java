package com.e_comm.SolaceEventPublishing;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;


@Service
public class EventPublisher {

	private final StreamBridge streamBridge;
	
	public EventPublisher(StreamBridge streamBridge1) {
		this.streamBridge = streamBridge1;
	}
	
	public void publish(EventSent event) {
		streamBridge.send("userJwtLogin-out-0", event);
	}
}
