package model;

import javax.management.NotificationEmitter;

public interface SubscriptionStrategy {

    public boolean apply(NotificationEvent event);
}
