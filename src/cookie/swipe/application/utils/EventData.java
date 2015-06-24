package cookie.swipe.application.utils;

import cookie.swipe.application.utils.ObservableLinkedHashSetPriorityQueue.ObservableLinkedHashSetPriorityQueueEvent;

public class EventData {
	
	private ObservableLinkedHashSetPriorityQueueEvent eventType;
	private int bIndex;
	private int eIndex;
	private Object arg;
	private boolean hasIndx;

	public EventData(ObservableLinkedHashSetPriorityQueueEvent eventType, int bIndex, int eIndex, Object arg) {
		this.eventType = eventType;
		this.bIndex = bIndex;
		this.eIndex = eIndex;
		this.arg = arg;
		hasIndx = true;
	}
	
	public EventData(ObservableLinkedHashSetPriorityQueueEvent eventType) {
		this.eventType = eventType;
		hasIndx = false;
	}
	
	public EventData(ObservableLinkedHashSetPriorityQueueEvent eventType, int bIndex, int eIndex) {
		this(eventType, bIndex, eIndex, null);
	}

	public ObservableLinkedHashSetPriorityQueueEvent getType() {
		return this.eventType;
	}

	public int getBeginIndex() {
		return bIndex;
	}
	
	public int getEndIndex() {
		return eIndex;
	}
	
	public Object getArg() {
		return this.arg;
	}

	public boolean hasIndexes() {
		return hasIndx;
	}
}
