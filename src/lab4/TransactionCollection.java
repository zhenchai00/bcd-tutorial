package lab4;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lab2.hasher;

public class TransactionCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    private int size = 10;
    private String merkleRoot;
    private List<String> transactionList;

    TransactionCollection() {
        transactionList = new ArrayList<String>();
    }
    
    public void add(String transaction) {
        if (transactionList.size() < size) {
            transactionList.add(transaction);
            merkleRoot = calculateMerkleRoot();
        }
    }

    public String getMerkleRoot() {
        return this.merkleRoot;
    }
    
    public List<String> getTransactionList() {
        return this.transactionList;
    }

    private String calculateMerkleRoot() {
        List<String> tempList = new ArrayList<>(transactionList);
        while (tempList.size() > 1) {
            List<String> newList = new ArrayList<>();
            for (int i = 0; i < tempList.size(); i += 2) {
                String left = tempList.get(i);
                String right = (i + 1 < tempList.size()) ? tempList.get(i + 1) : left;
                newList.add(hasher.sha256(left + right));
            }
            tempList = newList;
        }
        return tempList.isEmpty() ? "" : tempList.get(0);
    }

    @Override
    public String toString() {
        return "TransactionCollection {" +
                "\n\tsize=" + size +
                ", \n\tmerkleRoot='" + merkleRoot + '\'' +
                ", \n\ttransactionList=" + transactionList +
                "\n}";
    }
}
