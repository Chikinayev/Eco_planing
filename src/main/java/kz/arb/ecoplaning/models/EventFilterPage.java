package kz.arb.ecoplaning.models;

import lombok.ToString;

@ToString
public class EventFilterPage {

    public String find;
    public long totalItems;
    public int pageSize;
    public int currentPage;

    public EventFilterPage() {
    }
}
