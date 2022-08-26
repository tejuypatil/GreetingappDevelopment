package com.messaging.greetingappdevelopment.controller;

import com.messaging.greetingappdevelopment.model.Greeting;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

public class GreetingController {
        private static final String template ="Hello , %s!";
        private final AtomicLong counter = new AtomicLong();

        //curl -X GET "http://localhost:8080/greeting"
        @GetMapping("/greeting/param")
        public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name)
        {
            return new Greeting(counter.incrementAndGet(),String.format(template,name));

        }
}
