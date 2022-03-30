package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.Map;

import lombok.Data;

@Data
/* ref: https://www.baeldung.com/java-k-means-clustering-algorithm */
public class Centroid {
    /* centroid has the same content as the features */
    private final Map<String, Double> coordinates;
}
