package observer;

import resource.implementation.Entity;

public interface Publisher {
    void addSubscriber(Subscriber sub);
    void removeSubscriber(Subscriber sub);
    void notifySubscribers(Entity entity);
}
