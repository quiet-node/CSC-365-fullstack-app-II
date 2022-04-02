package yelp.dataset.oswego.yelpbackend.services;

import java.io.IOException;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import yelp.dataset.oswego.yelpbackend.algorithms.clustering.Centroid;
import yelp.dataset.oswego.yelpbackend.algorithms.clustering.KMeans;
import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
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

    /**
     * A function to fetch random clusters
     * @return clusters
     * @throws IOException
     */
    public Map<Centroid, List<BusinessModel>> fetchClusters() throws IOException {
        BusinessBtree businessBtree = new IOService().readBtree();
        
        Map<Centroid, List<BusinessModel>> clusters = new KMeans().initializeClusers(businessBtree, new Random().nextInt(10)+5);
        if (clusters == null) 
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);

        return clusters;
    }
}
