package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.*;

import lombok.Data;
import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;


@Data
public class KMeans {
    /**
     * A function to initialize clusters.
     * Reference: https://www.baeldung.com/java-k-means-clustering-algorithm
     * @param businessBtree 
     * @param k number of clusters
     * @return clusters
     */
    public Map<Record, List<Record>> initializeClusers(BusinessBtree businessBtree, int k) {
        List<BusinessModel> centroids = generateRandomCentroids(businessBtree, 7);
        List<BusinessModel> records = businessBtree.findAll();

        return null;
    }

    /**
     * A function to generate centroids randomly
     * @param businessBtree
     * @param k
     * @return a list of random centroids
     */
    private List<BusinessModel> generateRandomCentroids(BusinessBtree businessBtree, int k) {
        List<BusinessModel> centroids = new ArrayList<>();
        List<BusinessModel> businessList = businessBtree.findRandomBusinesses(k);

        for (int i = 0; i < businessList.size(); i++) {
            centroids.add(businessList.get(i));
        }
        return centroids;
    }

    /**
     * A function to find the neareest centroid
     * @param record
     * @param centroids
     * @return the nearest centroid
     */
    private BusinessModel nearestCentroid(BusinessModel record, List<BusinessModel> centroids) {
        
        double maximumSimRate = Double.NEGATIVE_INFINITY;
        BusinessModel nearest = null;

        for (BusinessModel centroid : centroids) {

            double currentSimRate = new CosSim().calcSimRate(record.getCategories(), centroid.getCategories());
            
            if (currentSimRate > maximumSimRate) {
                maximumSimRate = currentSimRate;
                nearest = centroid;
                record.setSimilarityRate(currentSimRate);
            }
        }

        return nearest;
    }


}
