package lab10;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Header implements Serializable {
    private static final long serialVersionUID = 1L;
    private int index;
    private String currentHash;
    private String previousHash;
    private String merkleRoot;
    private long timestamp;

    public int getIndex() {
        return index;
    }

    public String getCurrentHash() {
        return currentHash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Header {" +
                "\n\tindex=" + index +
                ", \n\tcurrentHash='" + currentHash + '\'' +
                ", \n\tpreviousHash='" + previousHash + '\'' +
                ", \n\tmerkleRoot='" + merkleRoot + '\'' +
                ", \n\ttimestamp=" + format.format(new Date(timestamp)) +
                "\n }";
    }
}
