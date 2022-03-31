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
    public Map<Record, List<Record>> initializeClusers(BusinessBtree businessBtree, int amount) {
        for (int i = 0; i < amount; i++) {
            System.out.println("EACH: " +generateRandomCentroids(businessBtree, amount).get(i));
        }
        return null;
    }

    private List<Centroid> generateRandomCentroids(BusinessBtree businessBtree, int amount) {
        List<Centroid> centroids = new ArrayList<>();
        List<BusinessModel> businessList = businessBtree.getRandomBusinesses(amount);

        for (int i = 0; i < businessList.size(); i++) {
            centroids.add(new Centroid(businessList.get(i).getId(), businessList.get(i).getName(), businessList.get(i).getCategories()));
        }
        return centroids;
    }
}
