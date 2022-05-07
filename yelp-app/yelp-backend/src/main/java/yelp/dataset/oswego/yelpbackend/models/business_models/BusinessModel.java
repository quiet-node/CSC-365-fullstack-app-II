package yelp.dataset.oswego.yelpbackend.models.business_models;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Nam (Logan) Nguyen
 * @college: SUNY Oswego
 * @since Spring 2022
 * @version 2.0
 * @link: https://github.com/lgad31vn/CSC-365
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="yelp")
public class BusinessModel implements Comparable<BusinessModel>, Serializable {
    @Id
    private long id;
    private String  business_id, name, address;
    private double stars, reviews, similarityRate;
    private ArrayList<String> categories;

    public BusinessModel(long id) {
        this.id = id;
    }

    @Override
    public int compareTo(BusinessModel b) {
        return Double.compare(this.getSimilarityRate(), b.similarityRate);
    }
}
