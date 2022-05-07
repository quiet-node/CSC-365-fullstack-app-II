package yelp.dataset.oswego.yelpbackend.algorithms.clustering;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: Nam (Logan) Nguyen
 * @college: SUNY Oswego
 * @since Spring 2022
 * @version 2.0
 * @link: https://github.com/lgad31vn/CSC-365
 */

@Data
@AllArgsConstructor
public class Centroid {
    private final long id;
    private final String businessName;
    private final ArrayList<String> categories;
}
