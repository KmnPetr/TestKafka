package com.example.demo.controllers;

import com.example.demo.kafka.JsonKafkaProducer;
import com.example.demo.payload.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
    private JsonKafkaProducer jsonKafkaProducer;

    @Autowired
    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }
    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody User user){
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity
                .ok("Json message send to kafka topic");
    }

    @GetMapping("/fff")///api/v1/kafka/fff
    public ResponseEntity<String> fff(){
        User user=new User();
        user.setId(22);
        user.setFirstName("Ivan");
        user.setLastName("Ivanovich");
        jsonKafkaProducer.sendMessage(user);

        return ResponseEntity
                .ok("Json message send to kafka topic");
    }
}
