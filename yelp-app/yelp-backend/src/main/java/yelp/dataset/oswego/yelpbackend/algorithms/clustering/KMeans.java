package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.*;

import lombok.Data;
import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

/**
 * ref: https://www.baeldung.com/java-k-means-clustering-algorithm
 */
@Data
public class KMeans {
    /**
     * A function to initialize clusters
     * @param businessBtree 
     * @param k number of clusters
     * @return clusters
     */
    public Map<Record, List<Record>> initializeClusers(BusinessBtree businessBtree, int k) {
        return null;
    }

    /**
     * A function to generate centroids randomly
     * @param businessBtree
     * @param k
     * @return a list of random centroids
     */
    private List<Centroid> generateRandomCentroids(BusinessBtree businessBtree, int k) {
        List<Centroid> centroids = new ArrayList<>();
        List<BusinessModel> businessList = businessBtree.getRandomBusinesses(k);

        for (int i = 0; i < businessList.size(); i++) {
            centroids.add(new Centroid(businessList.get(i).getId(), businessList.get(i).getName(), businessList.get(i).getCategories()));
        }
        return centroids;
    }

    /**
     * A function to find the neareest centroid
     * @param record
     * @param centroids
     * @return the nearest centroid
     */
    private Centroid nearestCentroid(Record record, List<Centroid> centroids) {

        return null;
    }


}
