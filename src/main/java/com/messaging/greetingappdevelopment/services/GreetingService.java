package com.messaging.greetingappdevelopment.services;

import com.messaging.greetingappdevelopment.model.Greeting;
import com.messaging.greetingappdevelopment.model.UserData;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {
    private final AtomicLong counter = new AtomicLong();

    public Greeting getGreeting(UserData userData) {
        long id = counter.incrementAndGet();
        Greeting greeting = new Greeting(id,"Hello World");
        return greeting;
    }
}
