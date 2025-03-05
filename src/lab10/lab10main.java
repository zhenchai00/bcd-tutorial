package lab10;

import java.util.ArrayList;
import java.util.List;

public class lab10main {
    public static void main(String[] args) {
        // test generate merkle tree
        String line1 = "bob|alice|debit|100";
        String line2 = "mick|alice|debit|200";
        String line3 = "peter|alice|debit|300";
        String line4 = "ali|bob|credit|400";

        List<String> list = new ArrayList<>();
        list.add(line1);
        list.add(line2);
        list.add(line3);
        list.add(line4);

        MerkleTree mt = MerkleTree.getInstance(list);
        mt.build();

        String root = mt.getRoot();
        System.out.println("Merkle Root =  " + root);


        // lab4 exercise integrate with MerkleRoot
        Block genesis = new Block("0");
        System.out.println(genesis);

        TransactionCollection tranxList = new TransactionCollection();
        tranxList.add(line1);
        tranxList.add(line2);
        tranxList.add(line3);
        tranxList.add(line4);

        Block b1 = new Block(genesis.getHeader().getCurrentHash());
        b1.setTransactions(tranxList);
        System.out.println(b1);

        TransactionCollection tranxList2 = new TransactionCollection();
        tranxList2.add(line1);
        tranxList2.add(line2);

        Block b2 = new Block(b1.getHeader().getCurrentHash());
        b2.setTransactions(tranxList2);
        System.out.println(b2);
    }
}
