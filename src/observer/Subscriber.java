package observer;

import resource.implementation.Entity;

public interface Subscriber {
    void update(Entity entity);
}
