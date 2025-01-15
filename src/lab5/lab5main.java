package lab5;

import java.io.File;

public class lab5main {
    private static String masterFolder = "src/lab5/master";
    private static String fileName = masterFolder + "/blockchain.bin";
    public static void main(String[] args) {
        Blockchain blockchain = Blockchain.getInstance(fileName);
        if (! new File(masterFolder).exists()) {
            System.err.println(" >> Creating Blockchain binary!");
            new File(masterFolder).mkdir();
            blockchain.genesis();
        } else {
            String line1 = "alice|bob|debit|100";
            String line2 = "helen|bob|debit|200";

            TransactionCollection transactionCollection = new TransactionCollection();
            transactionCollection.add(line1);
            transactionCollection.add(line2);

            String previousHash = blockchain.get().getLast().getHeader().getCurrentHash();
            Block b1 = new Block(previousHash);
            b1.setTransactions(transactionCollection);
            blockchain.nextBlock(b1);
            blockchain.distribute();
        }
    }
}
