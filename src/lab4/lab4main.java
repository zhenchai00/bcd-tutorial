package lab4;

public class lab4main {
    public static void main(String[] args) {
        // first block called genesis block
        Block genesis = new Block("0");
        System.out.println(genesis);

        String tranx1 = "alice|bob|debit|rm|10";
        String tranx2 = "helen|bob|debit|rm|20";

        TransactionCollection tranxList = new TransactionCollection();
        tranxList.add(tranx1);
        tranxList.add(tranx2);

        // block 1
        Block b1 = new Block(genesis.getHeader().getCurrentHash());
        b1.setTransactions(tranxList);
        System.out.println(b1);


        String tranx3 = "alice|bob|credit|rm|5";
        String tranx4 = "helen|bob|credit|rm|10";
        String tranx5 = "alice|bob|credit|rm|5";

        TransactionCollection tranxList2 = new TransactionCollection();
        tranxList2.add(tranx3);
        tranxList2.add(tranx4);
        tranxList2.add(tranx5);

        // block 2 
        Block b2 = new Block(b1.getHeader().getCurrentHash());
        b2.setTransactions(tranxList2);
        System.out.println(b2);

    }
}
