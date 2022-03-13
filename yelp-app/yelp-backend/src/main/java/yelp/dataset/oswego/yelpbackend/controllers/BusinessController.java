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

import yelp.dataset.oswego.yelpbackend.models.BusinessModel;
import yelp.dataset.oswego.yelpbackend.repositories.BusinessRepository;
import yelp.dataset.oswego.yelpbackend.similarity.CosSim;

@RestController
@RequestMapping("/yelpdata")
@CrossOrigin
public class BusinessController {
    @Autowired
    private BusinessRepository repo; // repo to store data

    @GetMapping("/allbusinesses")
    public List<BusinessModel> getAllBusinesses() {
        return repo.findAll();
    }

    @GetMapping("/{businessName}")
    public List<BusinessModel> getBusinessByName(@PathVariable String businessName) {
        return repo.findByName(businessName);
    }

    @GetMapping("/similar/{businessName}")
    public List<BusinessModel> getSimilarBusinesses(@PathVariable String businessName) {
        // init cosSim
        CosSim cosSim = new CosSim();

        // allBs:List<BusinessModel> => List of all businesses
        List<BusinessModel> allBs = repo.findAll();

        // similarBs:List<BusinessModel> => List of similar businesses
        List<BusinessModel> similarBs = new ArrayList<BusinessModel>();

        //targetB:BusinessModel => the business associate with the name passed in the url
        BusinessModel targetB = repo.findByName(businessName).get(0);

        //  loop through b to calculate cosSim
        for (BusinessModel b : allBs) {
            double cosSimRate = cosSim.calcSimRate(targetB, b);
            b.setSimilarityRate(cosSimRate);  
                if (b.getSimilarityRate() >= 0.55 && b.getSimilarityRate() <= 1) {
                           similarBs.add(b);
                }
        }
        Collections.sort(similarBs, Collections.reverseOrder());
        
        return similarBs;
    }

    @GetMapping("/")
    public List<BusinessModel> sortedBs(@PathVariable String businessName) {
        // init cosSim
        CosSim cosSim = new CosSim();

        // allBs:List<BusinessModel> => List of all businesses
        List<BusinessModel> allBs = repo.findAll();

        // similarBs:List<BusinessModel> => List of similar businesses
        List<BusinessModel> similarBs = new ArrayList<BusinessModel>();

        //targetB:BusinessModel => the business associate with the name passed in the url
        BusinessModel targetB = repo.findByName(businessName).get(0);

        //  loop through b to calculate cosSim
        for (BusinessModel b : allBs) {
            double cosSimRate = cosSim.calcSimRate(targetB, b);
            b.setSimilarityRate(cosSimRate);  
                if (b.getSimilarityRate() >= 0.65 && b.getSimilarityRate() <= 1) {
                           similarBs.add(b);
                }
        }
        return similarBs;

    }

    
}
