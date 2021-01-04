package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {
    public SampleOutput sampleOutput;
    SampleController () {
        sampleOutput = new SampleOutput();
    }
    @GetMapping("/status/{id}")
    public SampleOutput getStatus(@PathVariable("id") int id) {
        return sampleOutput;
    }

    @PostMapping("/request")
    public String post(@RequestBody SampleInput request) {
        System.out.println("Received the message " + request.body);
        sampleOutput.body = request.body;
        final String uri = "http://localhost:8091/request";
        RestTemplate restTemplate = new RestTemplate();
        SampleServiceInput sampleServiceInput = new SampleServiceInput();
        sampleServiceInput.body = request.body;
        sampleServiceInput.callback = "http://localhost:8090/callback/1";
        System.out.println("Posting the message to the service");
        String result = restTemplate.postForObject(uri, sampleServiceInput, String.class);
        return result;
    }

    @PostMapping("/callback/{id}")
    public String postCallback(@RequestBody String serviceResponse, @PathVariable("id") int id) {
        System.out.println("Received the status from service for id " + id +" : " + serviceResponse);
        return serviceResponse;
    }

    @PutMapping("/callback/{id}")
    public SampleServiceResponse putCallback(@RequestBody SampleServiceResponse serviceResponse, @PathVariable("id") int id) {
        System.out.println("Received the status from service for id " + id +" : " + serviceResponse.status);
        sampleOutput.detail = serviceResponse.detail;
        sampleOutput.status = serviceResponse.status;
        return serviceResponse;
    }
}
