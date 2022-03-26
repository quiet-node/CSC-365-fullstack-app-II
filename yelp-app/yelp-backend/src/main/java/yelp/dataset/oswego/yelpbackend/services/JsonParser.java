package yelp.dataset.oswego.yelpbackend.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.JSONObject;

import yelp.dataset.oswego.yelpbackend.algorithms.btree.BusinessBtree;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

public class JsonParser {

    BusinessBtree businessBtree = new BusinessBtree(8);

    public BusinessBtree jsonParser(String PATH) {
        try {
            // buffrer reader to read lines in json file
            FileReader reader = new FileReader(PATH);
            BufferedReader br = new BufferedReader(reader);
            String line = "";


            // loop through the json file
            for (int i = 0; i < 10000; i++) {
                // each line of the file is a json object
                line = br.readLine();

                // JSONify the whole line
                JSONObject bData = new JSONObject(line); 
                
                // attributes
                String name = bData.get("name").toString();
                String business_id = bData.get("business_id").toString();
                String address = bData.get("address").toString();
                Double stars = bData.getDouble("stars");
                Double reviews = bData.getDouble("review_count");
                Double similarityRate = 99999.0;

                // A list of string for categories
                ArrayList<String> bCategories = new ArrayList<String>();

                // get the values for categories-key and push them into an array
                String[] categories = bData.get("categories").toString().split(",");
                // String categoriesString = bData.get("categories").toString();

                // add each category value to bCategories list
                for (int j =0; j<categories.length; j++) {
                    bCategories.add(categories[j].trim());
                }

                // a BusinessModel instance
                BusinessModel bModel = new BusinessModel(i, business_id, name, address, stars, reviews, similarityRate, bCategories);
                // bModel.setCategories(bCategories);
                businessBtree.insert(bModel);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return businessBtree;
    }
    
}
