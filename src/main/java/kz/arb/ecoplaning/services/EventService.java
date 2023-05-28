package kz.arb.ecoplaning.services;

import kz.arb.ecoplaning.models.Event;
import kz.arb.ecoplaning.models.EventList;

import java.util.List;

public interface EventService {

    List<EventList> getEventListByUser(Long userID);

}
