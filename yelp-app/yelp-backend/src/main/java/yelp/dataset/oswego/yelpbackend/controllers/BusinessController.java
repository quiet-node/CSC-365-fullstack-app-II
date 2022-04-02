package yelp.dataset.oswego.yelpbackend.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;
import yelp.dataset.oswego.yelpbackend.repositories.BusinessRepository;

@RestController
@RequestMapping("/yelpdata")
@CrossOrigin
public class BusinessController {
    @Autowired
    private BusinessRepository repo; // repo to store data

    @GetMapping("/get-all-businesses")
    public List<BusinessModel> getAllBusinesses() {
        return repo.findAll();
    }

    @GetMapping("/{businessName}")
    public List<BusinessModel> getBusinessByName(@PathVariable String businessName) {
        return repo.findByName(businessName);
    }

    @GetMapping("/similar/{businessName}")
    public List<BusinessModel> getSimilarBusinesses(@PathVariable String businessName) {
        CosSim cosSim = new CosSim();

        List<BusinessModel> allBusinesses = repo.findAll();

        List<BusinessModel> similarBusinesses = new ArrayList<BusinessModel>();

        BusinessModel targetB = repo.findByName(businessName).get(0);

        for (BusinessModel b : allBusinesses) {
            double cosSimRate = cosSim.calcSimRate(targetB.getCategories(), b.getCategories());
            b.setSimilarityRate(cosSimRate);  
                if (b.getSimilarityRate() >= 0.55 && b.getSimilarityRate() <= 1) {
                           similarBusinesses.add(b);
                }
        }

        Collections.sort(similarBusinesses, Collections.reverseOrder());
        
        return similarBusinesses;
    }


    
}
