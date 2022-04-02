package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.*;

import yelp.dataset.oswego.yelpbackend.algorithms.similarity.CosSim;
import yelp.dataset.oswego.yelpbackend.dataStructure.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

public class KMeans {

    /**
     * A function to initialize clusters.
     * Reference: https://www.baeldung.com/java-k-means-clustering-algorithm
     * @param businessBtree 
     * @param k number of clusters
     * @return clusters
     */
    public Map<Centroid, List<BusinessModel>> initializeClusers(BusinessBtree businessBtree, int k) {
        Map<Centroid, List<BusinessModel>> clusters = new HashMap<>();
        List<Centroid> centroids = generateRandomCentroids(businessBtree, k);
        List<BusinessModel> records = businessBtree.findAll();

        for (BusinessModel record : records) {
                Centroid centroid = nearestCentroid(record, centroids);
                assignToCluster(clusters, record, centroid);
        }

        return clusters;
    }

    /**
     * A function to generate centroids randomly
     * @param businessBtree
     * @param k
     * @return a list of random centroids
     */
    private List<Centroid> generateRandomCentroids(BusinessBtree businessBtree, int k) {
        List<Centroid> centroids = new ArrayList<>();
        List<BusinessModel> businessList = businessBtree.findRandomBusinesses(k);

        for (int i = 0; i < businessList.size(); i++) {
            centroids.add(new Centroid( businessList.get(i).getId(), businessList.get(i).getName(), businessList.get(i).getCategories()));
        }
        
        return centroids;
    }

    /**
     * A function to find the neareest centroid
     * @param record
     * @param centroids
     * @return the nearest centroid
     */
    private Centroid nearestCentroid(BusinessModel record, List<Centroid> centroids) {
        double maximumSimRate = Double.NEGATIVE_INFINITY;
        Centroid nearest = null;

        for (Centroid centroid : centroids) {
            double currentSimRate = new CosSim().calcSimRate(record.getCategories(), centroid.getCategories());
            if (currentSimRate > maximumSimRate && record.getId() != centroid.getId()) {
                maximumSimRate = currentSimRate;
                nearest = centroid;
                record.setSimilarityRate(currentSimRate);
            }
        }
        return nearest;
    }

    /**
     * A function to assign records to clusters based on centroids. 
     * Only records with simRate > 75% will pass.
     * Each clusters will have max 25 records
     * @param clusters
     * @param record
     * @param centroid
     */
    private void assignToCluster(Map<Centroid, List<BusinessModel>> clusters, BusinessModel record, Centroid centroid) {
        List<BusinessModel> records = clusters.get(centroid);
        if (records == null) {
            records = new ArrayList<>();
        }
        if (records.size() < 25) {
            if (record.getSimilarityRate() > 0.75) {
                records.add(record);
            }
            Collections.sort(records, Collections.reverseOrder());
            clusters.put(centroid, records);
        }
    }

}
