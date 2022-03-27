package yelp.dataset.oswego.yelpbackend.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.NoArgsConstructor;
import yelp.dataset.oswego.yelpbackend.algorithms.btree.BusinessBtree;

@NoArgsConstructor
public class IOUtil {
    private final String fileDest = System.getProperty("user.dir") + "/yelp-app/yelp-dataset/btree.bin";

    protected void wrteBtree(BusinessBtree businessBtree) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDest));
        oos.writeObject(businessBtree);
        oos.close();
    }

    protected BusinessBtree readBtree() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDest));
        try {
            BusinessBtree bTree = (BusinessBtree) ois.readObject();
            ois.close();
            return bTree;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            ois.close();
            return null;
        }
    }

}
