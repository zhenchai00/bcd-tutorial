package lab10;

import java.io.Serializable;
import java.sql.Timestamp;
import lab2.hasher;

public class Block implements Serializable {
    private static final long serialVersionUID = 1L;
    private Header header;
    private TransactionCollection transactionCollection;

    Block(String previousHash) {
        header = new Header();
        header.setTimestamp(new Timestamp(System.currentTimeMillis()).getTime());
        header.setPreviousHash(previousHash);
        String info = String.join("+", Integer.toString(header.getIndex()), Long.toString(header.getTimestamp()), header.getPreviousHash());
        String blockHash = hasher.sha256(info);
        header.setCurrentHash(blockHash);
    }

    public TransactionCollection getTransactions() {
        return this.transactionCollection;
    }

    public void setTransactions(TransactionCollection transactions) {
        this.transactionCollection = transactions;
    }

    public Header getHeader() {
        return this.header;
    }

    @Override
    public String toString() {
        return "Block {" +
                "header=" + header +
                ", transactionCollection=" + transactionCollection +
                '}';
    }
}
