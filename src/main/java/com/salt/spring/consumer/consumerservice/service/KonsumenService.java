package com.salt.spring.consumer.consumerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import com.salt.spring.consumer.consumerservice.model.Konsumen;

@Service
public class KonsumenService {
    
    @Value("${api.url}")
    private String apiURL;

    private RestTemplate restTemplate;

    @Autowired
    public void ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Konsumen>getKonsumen(){
        ResponseEntity<Konsumen[]> response = restTemplate.exchange(
            apiURL + "/konsumen", HttpMethod.GET, null, Konsumen[].class);
        
        return Arrays.asList(response.getBody());
    }

    public Konsumen getKonsumenById(Integer id){
        ResponseEntity<Konsumen> response = restTemplate.exchange(
            apiURL + "/konsumen/" + id, HttpMethod.GET, null, Konsumen.class);
        
        return response.getBody();
    }


    public String createKonsumen(Konsumen konsumen){
        HttpEntity<Konsumen> request = new HttpEntity<Konsumen>(konsumen);

        String konsumenCreateResponse = restTemplate
               .postForObject(apiURL + "/konsumen", request, String.class);
        
        return konsumenCreateResponse;
    }

    public void updateKonsumen(Konsumen konsumen) {
        HttpEntity<Konsumen> request = new HttpEntity<Konsumen>(konsumen);

        restTemplate.exchange(
            apiURL + "/konsumen/" + konsumen.getId(), 
            HttpMethod.PUT, 
            request, 
            Void.class);
    }

    public void deleteKonsumen(Integer id) {
        restTemplate.exchange(
            apiURL + "/konsumen/" + id, 
            HttpMethod.DELETE, 
            null, 
            Void.class);
    }

}
