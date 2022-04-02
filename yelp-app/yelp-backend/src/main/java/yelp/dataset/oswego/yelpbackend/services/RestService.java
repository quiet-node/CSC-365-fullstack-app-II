package yelp.dataset.oswego.yelpbackend.services;

import java.util.*;

import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

public class RestService {
    
    /**
     * A function to get the similar businesses to the target business
     * @param allBusinesses
     * @param targetB
     * @return a list of similar businesses
     */
    public List<BusinessModel> getSimilarBusinesses(List<BusinessModel> allBusinesses , BusinessModel targetB) {
        List<BusinessModel> similarBusinesses = new ArrayList<BusinessModel>();
        CosSim cosSim = new CosSim();

        for (BusinessModel business : allBusinesses) {
            double cosSimRate = cosSim.calcSimRate(targetB.getCategories(), business.getCategories());
            business.setSimilarityRate(cosSimRate);  
                if (business.getSimilarityRate() >= 0.75 && business.getSimilarityRate() <= 1) {
                           similarBusinesses.add(business);
                }
        }

        Collections.sort(similarBusinesses, Collections.reverseOrder());
        return similarBusinesses;
    }
}
