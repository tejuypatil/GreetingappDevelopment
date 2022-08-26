package com.messaging.greetingappdevelopment.controller;

import com.messaging.greetingappdevelopment.model.Greeting;
import com.messaging.greetingappdevelopment.model.UserData;
import com.messaging.greetingappdevelopment.services.GreetingService;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;
@RestController
public class GreetingController {
        private static final String template ="Hello , %s!";
        private final AtomicLong counter = new AtomicLong();

        /**
         * UC1_curl -X GET "http://localhost:8080/greeting"
         * */
        @GetMapping("/greeting")
        public Greeting getGreeting(@RequestParam(value = "name", defaultValue = "World") String name)
        {
                return new Greeting(counter.incrementAndGet(),String.format(template,name));
        }
        /**
         * Post: curl http://localhost:8080/greeting
         * @param data this {@link UserData} object
         * */
        @PostMapping("/greeting")
        public Greeting getGreeting(@RequestBody UserData data){
                Greeting g=new Greeting(5,"Hi "+data.getFirstName()+".This is POST");
                return  g;
        }
        /**
         * Post : curl http://localhost:8080/greeting/hi (In request body passing JSON object)
         * @param data this is {@link UserData} with lastName
         * */
        @PostMapping("/greeting/hi")
        public Greeting getGreetingHi(@RequestBody UserData data){
        return new Greeting(counter.incrementAndGet(),String.format(template,data.getLastName()));
        }

        /** Put: curl http://localhost:8080/greeting/put?name=Tejaswini*/
        @PutMapping("/greeting/put")
        public Greeting putGreeting(@RequestParam(value ="name")String name){
                return new Greeting(counter.incrementAndGet(),String.format(template,name));
        }
        @GetMapping("/service")
        public  Greeting greeting(@RequestParam(value = "name", defaultValue = "World")String name){
                GreetingService greetingService = new GreetingService();
                UserData userData = new UserData();
                return greetingService.getGreeting(userData);
        }
}
