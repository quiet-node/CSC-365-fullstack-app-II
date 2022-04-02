package yelp.dataset.oswego.yelpbackend.services;

import java.util.*;

import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

public class RestService {
    
    public List<BusinessModel> getSimilarBusinesses(List<BusinessModel> allBusinesses , BusinessModel targetB) {
        CosSim cosSim = new CosSim();
        List<BusinessModel> similarBusinesses = new ArrayList<BusinessModel>();
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
