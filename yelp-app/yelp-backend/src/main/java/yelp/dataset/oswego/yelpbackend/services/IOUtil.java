package yelp.dataset.oswego.yelpbackend.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import lombok.NoArgsConstructor;
import yelp.dataset.oswego.yelpbackend.algorithms.btree.BusinessBtree;

@NoArgsConstructor
public class IOUtil {
    private final String bTree = System.getProperty("user.dir") + "/yelp-app/yelp-dataset/btree.bin";
    private final String bTreeRoot = System.getProperty("user.dir") + "/yelp-app/yelp-dataset/btree-root.bin";

    protected void writeBtree(BusinessBtree businessBtree) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(bTree));
        oos.writeObject(businessBtree);
        System.out.println("Successfully write bTree to btree.bin!");
        oos.close();
    }

    protected BusinessBtree readBtree() throws IOException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(bTree));
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


    protected void writeNode(BusinessBtree businessBtree) throws IOException {
        // Get the file
        RandomAccessFile raf = new RandomAccessFile(bTreeRoot, "rw");
        // Register FileChannel to operate read and write
        FileChannel wChannel = raf.getChannel();

        // Allocate bytes to ByteBuffer to store message inside a buffer
        ByteBuffer wBuffer = ByteBuffer.allocate(4096); // 4KB

        // Write information to wBuffer
        // wBuffer.putInt(businessBtree.getRoot().getBKeyNum());
        wBuffer.put("writing node...".getBytes());
        
         
        
        // ByteBuffer::flip() is used to flip BB from "reading from I/O"(put) to "writing to I/O"(get) after a sequence of put
        wBuffer.flip();

        // Acctually write to file from buffer
        wChannel.write(wBuffer);

        // clean up
        wBuffer.clear();
        wChannel.close();
        raf.close();

        System.out.println("Success!");

    }

    protected void readNode() throws IOException {
        // Get the file
        RandomAccessFile raf = new RandomAccessFile(bTreeRoot, "rw");

        // Register FileChannel to operate read and write
        FileChannel fChannel = raf.getChannel();

        // Allocaate bufferSize 
        int bufferSize = 4096; // 4KB
        if (bufferSize > fChannel.size()) {
            bufferSize = (int) fChannel.size();
        }
        
        // Register ByteBuffer to store message inside a buffer
        ByteBuffer buff = ByteBuffer.allocate(bufferSize);

        // Read the file to buffer
        fChannel.read(buff);
        
        // Flip the buffer
        buff.flip();

        System.out.println("Reading from buffer: " +new String(buff.array()));
    }

}
