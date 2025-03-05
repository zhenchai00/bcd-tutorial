package lab10;

import java.util.ArrayList;
import java.util.List;

import lab2.hasher;


public class MerkleTree {
	private static MerkleTree instance;
	private List<String> tranxLst;
	private String root = "0";

	public String getRoot() {
		return root;
	}
	
	// set the transaction list to the MerkleTree object
	private MerkleTree(List<String> tranxLst) {
		super();
		this.tranxLst = tranxLst;
	}

	public static MerkleTree getInstance( List<String> tranxLst ) {
		if( instance == null ) {
			return new MerkleTree(tranxLst);
		}
		return instance;
	}

	public void build() {
		List<String> tempLst = new ArrayList<>();
		for (String tranx : this.tranxLst) {
			tempLst.add(tranx);
		}
		List<String> hashes = genTranxHashLst( tempLst );
		while(  hashes.size() != 1 ) {
			hashes = genTranxHashLst( hashes );
		}
		this.root = hashes.get(0);
	}
	
	/**
	 * @implNote
	 * Generate hashes of transactions 
	 * 
	 * @implSpec 
	 * - genTranxHashLst(List<String>) : List<String>
	 */
	private List<String> genTranxHashLst(List<String> tranxLst) {
		List<String> hashLst = new ArrayList<>();
		int i = 0;
		while( i < tranxLst.size() ) {
			
			String left = tranxLst.get(i);
			i++;
			
			String right = "";
			if( i != tranxLst.size() ) right = tranxLst.get(i);
			
			String hash = hasher.sha256(left.concat(right));
			hashLst.add(hash);
			i++;
		}
		return hashLst;
	}
}