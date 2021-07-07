package org.acme.getting.started;

import java.time.Duration;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ReactiveGreetingService {

    public String greeting(String name) {
        return String.format("hello %s", name);
    }

}
