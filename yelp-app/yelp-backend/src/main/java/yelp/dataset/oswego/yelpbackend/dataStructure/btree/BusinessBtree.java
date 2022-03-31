package yelp.dataset.oswego.yelpbackend.dataStructure.btree;

import java.io.Serializable;

import lombok.Getter;
import yelp.dataset.oswego.yelpbackend.models.BusinessModel;

@Getter
public class BusinessBtree implements Serializable{
    /* 
    *   - POJO will be wiped off by garbge collector after a running session
    *   - Needs to store on disk for disk => transfrom POJO into byte stream
    *   - java.io.Serializable does such job
    */

    private BusinessBNode root;
    private int minDeg; 

    public BusinessBtree(int minDeg) {
        this.root = null;
        this.minDeg = minDeg;
    }

    // function to traverse the tree
    public void traverse() {
        if (this.root != null)
            this.root.traverse();
        System.out.println();
    }

    // function to search a key 
    public BusinessBNode findNode(BusinessModel key) {
        if (this.root == null)
            return null;
        else
            return this.root.findNode(key);
    }

    // function to search a key 
    public BusinessModel searchKey(BusinessModel key) {
        if (this.root == null)
            return null;
        else
            return this.root.searchKey(key);
    }

    /* 
    * ref: https://www.geeksforgeeks.org/insert-operation-in-b-tree/
    * Inserts a new key to BusinessBTree
    */
    public void insert(BusinessModel key) {
        if (root == null) { // if tree is empty
            // init new root
            root = new BusinessBNode(this.minDeg, true);
            root.BKeys.set(0, key);
            root.BKeyNum = 1;
        } else { // tree is not empty
            // If root is full, then tree grows in height
            if (root.BKeyNum == (2 * minDeg - 1)) {
                
                // init new root
                BusinessBNode newRoot = new BusinessBNode(minDeg, false);

                // make old root as child of new root
                newRoot.BChild.set(0, root);

                // split the old root and move 1 key to the new root
                newRoot.splitChild(0, root);

                // New root has two children now. Decide which of the two children is going to have new key
                int index = 0;
                if (newRoot.BKeys.get(0).getId() < key.getId()) 
                    index++; 
                newRoot.BChild.get(index).addKey(key);

                // change root for the whole tree
                root = newRoot;
            }
            else { // if root is not full, addKey key
                root.addKey(key);
            }
        }
    }
}
