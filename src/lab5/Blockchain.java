package lab5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import com.google.gson.GsonBuilder;

public class Blockchain {
    private static LinkedList<Block> db;
    private static Blockchain instance;
    public String chainFile;

    public static Blockchain getInstance(String chainFile) {
        if (instance == null) {
            instance = new Blockchain(chainFile);
        }
        return instance;
    }

    private Blockchain(String chainFile) {
        this.chainFile = chainFile;
        if (! new File(chainFile).exists()) {
            System.err.println(" >> Creating Blockchain binary!");
            try {
                new File(chainFile).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        db = new LinkedList<Block>();
    }

    public void genesis() {
        Block genesis = new Block("0");
        db.add(genesis);
        persist();
    }

    public void nextBlock(Block newBlock) {
        db = get();
        db.add(newBlock);
        persist();
    }

    public LinkedList<Block> get() {
        try (
            FileInputStream fileInput = new FileInputStream(this.chainFile);
            ObjectInputStream input = new ObjectInputStream(fileInput);
        ) {
            return (LinkedList<Block>) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void persist() {
        try (
            FileOutputStream fileOutput = new FileOutputStream(this.chainFile);
            ObjectOutputStream output = new ObjectOutputStream(fileOutput);
        ) {
            output.writeObject(db);
            System.out.println(" >> Master file is updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void distribute() {
        String chain = new GsonBuilder().setPrettyPrinting().create().toJson(db);
        System.out.println(chain);
    }
}
