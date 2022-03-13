package yelp.dataset.oswego.yelpbackend.similarity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import lombok.NoArgsConstructor;
import yelp.dataset.oswego.yelpbackend.hashing.HashTable;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

@NoArgsConstructor
public class CosSim {
    

    private double cosSimRate;
    
    
    // catFilter:string => filter out the "&" and " "
    // For example:
    //      catA = ["Gastropubs"," Food"," Beer Gardens"," Restaurants"," Bars"," American (Traditional)"," Beer Bar"," Nightlife"," Breweries"]
    //      catB = [Restaurant, Beauty & Spa,  Coffee & Tea, Hair Salons, Food]

    // Then catFilter return a HashSet
    //      vector =  [American, Store, Gardens, Coffee, Restaurants, Beer, Nightlife, Gastropubs, Tea, Convenience, Bar, Breweries, (Traditional), Food, Bars]      

    private HashSet<String> catFilter(ArrayList<String> catA, ArrayList<String> catB) {

        HashSet<String> termMatrix = new HashSet<>();

        // filter catA
        for(String cat : catA) { // EX: cat = "Coffee & Tea"
            cat = cat.trim();
            String[] catArr = cat.split(" "); // catArr = ["Coffee", "&", "Tea"]

            for(String c : catArr) { // c = "Coffee", c="&", c="Tea"
                if (!c.equals("&")){
                    termMatrix.add(c);
                }
            }
        }

        // filter and add catB to termMatrix
        for (String cat : catB) {
            cat = cat.trim();
            String[] catArr = cat.split(" ");

            for(String c : catArr) {
                if (!c.equals("&")) {
                    termMatrix.add(c);
                }
            }
        }

        return termMatrix;
    }

    // makeVector:HashTable => vector of each category
    private HashTable makeVector(HashSet<String> termMatrix, ArrayList<String> categories) {

        // init a vector:hashtable
        HashTable vector = new HashTable(10);

        for (String term: termMatrix){  // loop through termMatrix
            term = term.trim(); // clean the term
            for(String cat : categories) {   // cat can be "Coffee & Tea"
               cat = cat.trim();
               String[] catArr = cat.split(" "); //catArr = ["Coffee", "&", "Tea"]
               for(String c:catArr) { // c = "Coffee", c ="&", c ="Tea"
                   if (term.equals(c)) { 
                        vector.add(term);
                   }

                }
            }
        }

        // Each vector is a hashtable
        return vector;
    }

    
    

    // dotProduct:double => how many similimar words
    private double calcDotProduct(ArrayList<String> catA, ArrayList<String> catB) {
        
        // init dotProd:double
        double dotProd = 0;

        // termMatrix contains all relevant words from catA and catB
        HashSet<String> termMatrix = catFilter(catA, catB);

        // init vectors:HashTable
        HashTable vectorA = makeVector(termMatrix, catA);
        HashTable vectorB = makeVector(termMatrix, catB);
        // loop through termMatrix
        for(String term : termMatrix) {
            // dotProd = x1*y1 + x2*y2
            // It masters only when term != null ie. term.value > 0
            if (vectorA.getTerm(term) > 0 && vectorB.getTerm(term) > 0) {
                    int valueA = vectorA.getTerm(term);
                    int valueB = vectorB.getTerm(term);

                    int product = valueA * valueB;
                    dotProd += product; 
            }



        }

        return dotProd;        
    }


    // Magnitude of each vector
    private double calcMagnitude(HashSet<String> termMatrix, HashTable vector) {
        
        double sumFreq = 0.0;
        
        // loop through termMatrix
        for(String term : termMatrix) {
            //It masters only when term != null ie. term.value > 0
            if (vector.getTerm(term) > 0) {
                sumFreq += Math.pow(vector.getTerm(term), 2);
            }
        }

        return Math.sqrt(sumFreq);
    }


    // Magnitude product of the 2 vectors
    private double calcMagProduct(ArrayList<String> catA, ArrayList<String> catB) {

        // termMatrix contains all relevant words from catA and catB
        HashSet<String> termMatrix = catFilter(catA, catB);

        // doctermMatrix:HashMap<String ,Integer> 
        HashTable vectorA = makeVector(termMatrix, catA);
        HashTable vectorB = makeVector(termMatrix, catB);

        double magVectorA = calcMagnitude(termMatrix, vectorA);
        double magVectorB = calcMagnitude(termMatrix, vectorB);


        return magVectorA * magVectorB;
        
    }


    // calculate simRate 
    public double calcSimRate(BusinessModel businessA, BusinessModel businessB) {
        // Cos(X, Y) = (X.Y) / (||X|| * ||Y||)
        // Pseudo: simRate = Cos(X,Y) = (dotProduct) / (Magnitude vector X * Magnitude vector Y)

        ArrayList<String> catA = businessA.getCategories();
        ArrayList<String> catB = businessB.getCategories();

        double dotProduct = calcDotProduct(catA, catB);
        double magProduct = calcMagProduct(catA, catB);

        this.cosSimRate = dotProduct / magProduct;

        return this.cosSimRate;
    }

  


}
