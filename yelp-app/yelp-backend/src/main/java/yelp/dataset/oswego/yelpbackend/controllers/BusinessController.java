package yelp.dataset.oswego.yelpbackend.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;
import yelp.dataset.oswego.yelpbackend.repositories.BusinessRepository;
import yelp.dataset.oswego.yelpbackend.services.RestService;

@RestController
@RequestMapping("/yelpdata")
@CrossOrigin
public class BusinessController {
    @Autowired
    private BusinessRepository repo; // repo to store data

    @GetMapping("/get-all-businesses")
    public ResponseEntity<List<BusinessModel>> getAllBusinesses() {
        List<BusinessModel> allBusinesses = repo.findAll();
        if (allBusinesses == null) 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        
        return new ResponseEntity<>(allBusinesses, HttpStatus.OK);
    }

    @GetMapping("/{businessName}")
    public ResponseEntity<List<BusinessModel>> getBusinessByName(@PathVariable String businessName) {
        List<BusinessModel> business = repo.findByName(businessName);
        if (business == null) 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    @GetMapping("/similar/{businessName}")
    public ResponseEntity<List<BusinessModel>> getSimilarBusinesses(@PathVariable String businessName) {
        List<BusinessModel> allBusinesses = repo.findAll();
        if (allBusinesses == null) 
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        
        BusinessModel targetB = repo.findByName(businessName).get(0);
        List<BusinessModel> similarBusinesses = new RestService().getSimilarBusinesses(allBusinesses, targetB);

        return  new ResponseEntity<>(similarBusinesses, HttpStatus.OK);
    }


    
}
