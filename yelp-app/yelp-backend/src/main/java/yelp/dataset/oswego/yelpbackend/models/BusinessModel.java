package yelp.dataset.oswego.yelpbackend.models;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="yelp")
public class BusinessModel implements Comparable<BusinessModel> {
    @Id
    private long id;

    String  business_id, name, address;
    double stars, reviews;
    
    double similarityRate;

    ArrayList<String> categories;

    @Override
    public int compareTo(BusinessModel b) {
        
        return Double.compare(this.getSimilarityRate(), b.similarityRate);
        
    }



    
}
