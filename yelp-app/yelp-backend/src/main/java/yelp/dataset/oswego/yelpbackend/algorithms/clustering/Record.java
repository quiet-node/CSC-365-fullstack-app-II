package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.Map;

import lombok.Data;

@Data
/* ref: https://www.baeldung.com/java-k-means-clustering-algorithm */
public class Record {
    private final String businesName; 
    private final Map<String, Double> businessFeatures; // {bA=simRate1, bB=simRate2, bC=simRate3}
}
