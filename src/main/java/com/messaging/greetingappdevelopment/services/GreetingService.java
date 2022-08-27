package com.messaging.greetingappdevelopment.services;

import com.messaging.greetingappdevelopment.model.Greeting;
import com.messaging.greetingappdevelopment.model.UserData;
import com.messaging.greetingappdevelopment.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {
    private final AtomicLong counter = new AtomicLong();
    @Autowired
    private GreetingRepository greetingRepository;

    public Greeting getGreeting(UserData userData) {
        long id = counter.incrementAndGet();
        Greeting greeting = new Greeting(id,"Hello "+userData.getFirstName()+" "+userData.getLastName());
        return greeting;
    }
    public Greeting addGreeting(UserData userData){
        String message = "Hello " + userData.getLastName() +" "+userData.getFirstName();
        Greeting greeting=new Greeting(counter.incrementAndGet(),message);
        return greetingRepository.save(greeting);
    }

    public Greeting getGreetingById(long id)
    {
        Greeting greeting=greetingRepository.findById(id).get();
        return greeting;
    }

    public List<Greeting> getAllGreetings()
    {
        List<Greeting> greetings=greetingRepository.findAll();
        return  greetings;
    }

    public Greeting updateGreeting(UserData userData,long id) {
        Greeting greeting = greetingRepository.findById(id).get();

        String message = "Hello " + userData.getLastName() +" "+userData.getFirstName();

        greeting.setContent(message);
        Greeting greetingUpdated = greetingRepository.save(greeting);
        return greetingUpdated;
    }

    public void deleteGreeting(long id) {
        greetingRepository.deleteById(id);
    }
}
