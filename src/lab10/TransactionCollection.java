package lab10;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TransactionCollection implements Serializable {
    private static final long serialVersionUID = 1L;
    private int size = 10;
    private List<String> transactionList;

    TransactionCollection() {
        transactionList = new ArrayList<String>();
    }
    
    public void add(String transaction) {
        if (transactionList.size() < size) {
            transactionList.add(transaction);
        }
    }

    public List<String> getTransactionList() {
        return this.transactionList;
    }

    @Override
    public String toString() {
        return "TransactionCollection {" +
                "\n\tsize=" + size +
                ", \n\ttransactionList=" + transactionList +
                "\n}";
    }
}
