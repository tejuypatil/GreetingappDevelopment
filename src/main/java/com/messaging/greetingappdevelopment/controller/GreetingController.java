package com.messaging.greetingappdevelopment.controller;

import com.messaging.greetingappdevelopment.model.Greeting;
import com.messaging.greetingappdevelopment.model.UserData;
import com.messaging.greetingappdevelopment.services.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
@RestController
public class GreetingController {
        private static final String template ="Hello , %s!";
        private final AtomicLong counter = new AtomicLong();

        @Autowired
        private GreetingService greetingService;

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
        @GetMapping("/greet")
        public  Greeting greeting(@RequestParam(value = "FirstName", defaultValue = "")String fname,
                                  @RequestParam(value = "LastName",defaultValue = "")String lname    )
        {

                UserData userData = new UserData();
                userData.setFirstName(fname);
                userData.setLastName(lname);


                return greetingService.getGreeting(userData);
        }


        /**
         * UC4
         * saves new greeting to database
         * this is POST call with UserData as RequestBody
         * */
        @PostMapping("/greetService")
        public Greeting greeting(@RequestBody UserData userData)
        {
                return  greetingService.addGreeting(userData);
        }


        /**
         * UC5
         * gets the greeting with given id from database
         * this is GET call with id as PathVariable
         * */
        @GetMapping("/greetService/{id}")
        public Greeting greeting(@PathVariable long id)
        {
                return  greetingService.getGreetingById(id);
        }


        /**
         * UC6
         * gets all greetings from database
         * this is GET call
         * */
        @GetMapping("/greetService")
        public List<Greeting> greetingFindAll()
        {
                return  greetingService.getAllGreetings();
        }

        /**
         * UC7
         * updates the greeting with given id in database
         * this is PUT call with new UserData and greeting_id
         * */
        @PutMapping("/greetService/{id}")
        public Greeting greeting(@RequestBody UserData userData, @PathVariable long id)
        {
                return  greetingService.updateGreeting(userData,id);
        }

        /**
         * UC8
         * deletes the greeting with given id from database
         * this is DELETE call
         * */
        @DeleteMapping("/greetService/{id}")
        public void greetingDelete(@PathVariable long id)
        {
                greetingService.deleteGreeting(id);
        }
}
