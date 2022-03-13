package yelp.dataset.oswego.yelpbackend.models;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Columns;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //setters, getters, toString, hashcode
@AllArgsConstructor
@NoArgsConstructor
@Table(name="yelp")
public class BusinessModel implements Comparable<BusinessModel> {
    @Id
    @GeneratedValue
    private long id; // primary key

    String  business_id, name, address;
    double stars, reviews;
    
    double similarityRate;

    ArrayList<String> categories;

    @Override
    public int compareTo(BusinessModel b) {
        
        return Double.compare(this.getSimilarityRate(), b.similarityRate);
        
    }



    
}
