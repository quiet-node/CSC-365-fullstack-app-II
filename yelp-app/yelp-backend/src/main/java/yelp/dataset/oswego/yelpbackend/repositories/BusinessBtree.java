package yelp.dataset.oswego.yelpbackend.repositories;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class BusinessBtree implements Serializable{
    /* 
    *   - POJO will be wiped off by garbge collector after a running session
    *   - Needs to store on disk for disk => transfrom POJO into byte stream
    *   - java.io.Serializable does such job
    */

    private static final int M = 4; // max childrend per B-tree node = M - 1 
    private int nodeCounts; //  number of BNodes 
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
    public BusinessBNode search(int key) {
        if (this.root == null)
            return null;
        else
            return this.root.search(key);
    }

    // ref: https://www.geeksforgeeks.org/insert-operation-in-b-tree/
    // The main function that inserts a new key 
    public void insert(int key) {
        if (root == null) { // if tree is empty
            // init new root
            root = new BusinessBNode(this.minDeg, true);
            root.BKeys[0] = key;
            root.BKeyNum = 1;
        } else { // tree is not empty
            // If root is full, then tree grows in height
            if (root.BKeyNum == (2 * minDeg - 1)) {
                // init new root
                BusinessBNode newRoot = new BusinessBNode(minDeg, false);

                // make old root as child of new root
                newRoot.BChild[0] = root;

                // split the old root and move 1 key to the new root
                newRoot.splitChild(0, root);

                // New root has two children now. Decide which of the two children is going to have new key
                int index = 0;
                if (newRoot.BKeys[0] < key) 
                    index++; 
                newRoot.BChild[index].addToNonFullNode(key);

                // change root for the whole tree
                root = newRoot;
            }
        }
    }
}
