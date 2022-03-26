package yelp.dataset.oswego.yelpbackend.repositories;

import lombok.Data;

@Data
public class BusinessBNode {
    // private BusinessModel[] BKeys;  // Array of business key
    protected int[] BKeys;  // Array of business key
    protected BusinessBNode[] BChild;  // Array of business children
    protected int BMinDeg; // Minimum degree (defines the range for number of keys)
    protected int BKeyNum; // number of business keys
    protected boolean BIsLeaf; 

    public BusinessBNode(int BMinDeg, boolean BIsLeaf) {
        this.BMinDeg = BMinDeg;
        this.BIsLeaf = BIsLeaf;
        this.BKeys = new int[2 * BMinDeg - 1];
        this.BChild = new BusinessBNode[2 * BMinDeg];
        this.BKeyNum = 0;
    }


    // A function to traverse all nodes in a subtree rooted with this node
    protected void traverse() {
        
        // There are n keys and n+1 children, traverse through n keys and first n children
        int i = 0;
        for (i = 0; i < this.BKeyNum; i++) {
            
            // If this is not leaf, then before printing BKey[i], traverse the subtree rooted with child BChild[i].
            if (!this.BIsLeaf) BChild[i].traverse();
            
            System.out.print(BKeys[i] + " ");
        }
 
        // Print the subtree rooted with last child
        if (!BIsLeaf) BChild[i].traverse();

    }

    // A function to search a key in the subtree rooted with this node.
    protected BusinessBNode search(int key) { // returns NULL if k is not present.
 
        // Find the first key greater than or equal to k
        int i = 0;
        while (i < this.BKeyNum && key > BKeys[i])
            i++;
 
        // If the found key is equal to k, return this node
        if (BKeys[i] == key)
            return this;
 
        // If the key is not found here and this is a leaf node => null
        if (BIsLeaf) return null;
 
        // Go to the appropriate child
        return BChild[i].search(key);
 
    }

    // ref: https://www.geeksforgeeks.org/insert-operation-in-b-tree/
    // add a new key to a non-full node
    protected void addKey(int key) {

        // Init tail
        int tail = BKeyNum - 1;

        // if this is a leaf node
        if (BIsLeaf) {
            // find the appropriate location of the new key 
            while (tail >= 0 && BKeys[tail] > key) {
                BKeys[tail+1] = BKeys[tail]; // move all greater keys to one place ahead to make room for new key
                tail--;
            }

            // Add the new key
            BKeys[tail+1] = key;
            BKeyNum += 1;
        } else { // if this node is not a leaf
            
            // first find the appropriate child for the new key
            while (tail >= 0 && BKeys[tail] > key) tail--;
            
            if (BChild[tail+1].BKeyNum == (2 * BMinDeg -1)) { // if the child is full

                // split the child if full
                splitChild(tail+1, BChild[tail+1]);

                // After split, the middle key of BChild[tail] goes up to the node above
                // Bchild[tail] is splitted into two children
                // find the appropriate child to add the new key
                if (BKeys[tail+1] < key) tail++;
            }
            BChild[tail+1].addKey(key);
        }

    }

    // ref: https://www.geeksforgeeks.org/insert-operation-in-b-tree/
    // split the child newNode => newNode must be full to split
    protected void splitChild(int pos, BusinessBNode splittedNode) {

        // create a new node to store (t-1) keys of splittedNode
        BusinessBNode newNode = new BusinessBNode(splittedNode.BMinDeg, splittedNode.BIsLeaf);
        newNode.BKeyNum = BMinDeg - 1;
        
        // copy the last (BMinDeg - 1) "keys" of splittedNode to newNode
        for (int i = 0; i < BMinDeg - 1; i++) {
            newNode.BKeys[i] = splittedNode.BKeys[i+BMinDeg];
            splittedNode.BKeys[i+BMinDeg] = 0;
        }

        // copy the last BMinDeg "children" of splittedNode to newNode
        if (!splittedNode.BIsLeaf) {
            for (int i = 0; i < BMinDeg; i++) {
                newNode.BChild[i] = splittedNode.BChild[i+BMinDeg];
            }
        }

        // reduce the number of keys in splittedNode
        splittedNode.BKeyNum = BMinDeg - 1;        
        
        // create new space for new child in this node
        for (int i = BKeyNum; i >= pos + 1; i --) {
            BChild[i+1] = BChild[i];
        }
        
        // link the new child to newNode
        BChild[pos+1] = newNode;

        // move a key from splittedNode to newNode. Find the location of new key and move all greater keys one space ahead
        for (int i =  BKeyNum - 1;i >= pos; i--) {
            BKeys[i+1] = BKeys[i];
        }

        // copy the middle key of splittedNode to newNode
        BKeys[pos] = splittedNode.BKeys[BMinDeg-1];
        splittedNode.BKeys[BMinDeg-1] = 0;

        // increment BKeyNum
        BKeyNum += 1;

    }
    
}
