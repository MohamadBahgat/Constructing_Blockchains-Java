package controler;

import model.Block;
import model.Blockchain;
import model.Network;
import model.Person;

public class Main {

	public static void main(String[] args) {
		Network myNetwork = new Network();
		
		
		// blockchain with length 5 (5 blocks)
	    Blockchain blockchain3 = new Blockchain(2); // number of zeros in the mining process "proof of work"
	    blockchain3.addBlock(blockchain3.newBlock("1st Transaction ... Ali Sent 5$ to Mona"));
	    blockchain3.addBlock(blockchain3.newBlock("2nd Transaction ... Mona sent 57$ to Toaa"));
	    blockchain3.addBlock(blockchain3.newBlock("3rd Transaction ... Sami sent 88$ to Ali"));
	    blockchain3.addBlock(blockchain3.newBlock("4rd Transaction ... Rami sent 80$ to Ali"));
	    blockchain3.addBlock(blockchain3.newBlock("5rd Transaction ... Rami sent 88$ to Ali"));
	    System.out.println("BC1 length is: "+blockchain3.getLenght());
	    System.out.println("\n-------------------------\n");
	    // blockchain with length 4 (4 blocks)
	    Blockchain blockchain1 = new Blockchain(2); // number of zeros in the mining process "proof of work"
	    blockchain1.addBlock(blockchain1.newBlock("1st Transaction ... Ali Sent 500$ to Mona"));
	    blockchain1.addBlock(blockchain1.newBlock("2nd Transaction ... Mona sent 5700$ to Toaa"));
	    blockchain1.addBlock(blockchain1.newBlock("3rd Transaction ... Sami sent 8800$ to Ali"));	    
	    blockchain1.addBlock(blockchain1.newBlock("4nd Transaction ... lona sent 50$ to Toaa"));
	    System.out.println("BC2 length is: "+blockchain1.getLenght());
	    System.out.println("\n-------------------------\n");
	    // blockchain with length 3 (3 blocks)
	    Blockchain blockchain2 = new Blockchain(2); // number of zeros in the mining process "proof of work"
	    blockchain2.addBlock(blockchain2.newBlock("1st Transaction ... Ali Sent 50$ to Mona"));
	    blockchain2.addBlock(blockchain2.newBlock("2nd Transaction ... Mona sent 570$ to Toaa"));
	    blockchain2.addBlock(blockchain2.newBlock("3rd Transaction ... Sami sent 880$ to Ali"));
	    System.out.println("BC3 length is: "+blockchain2.getLenght());
	    System.out.println("\n-------------------------\n");
	    
	    //  --------- Make some people ------------
	    // pass to them blockchain3 the smallest one
	    Person p1 = new Person(1, "Ali",  "", blockchain1);
		Person p2 = new Person(2, "Mona", "", blockchain1);
		Person p3 = new Person(3, "Toaa", "", blockchain1);
		Person p4 = new Person(4, "Sami", "", blockchain1);
		System.out.println("\n-------------------------\n");
		System.out.println("\n-------------------------\n");
		// connect them to the network
		myNetwork.getArrList_People().add(p1);
		myNetwork.getArrList_People().add(p2);
		myNetwork.getArrList_People().add(p3);
		myNetwork.getArrList_People().add(p4);
		
		
		// lets add some blockchains to our people
		p1.addNew_BC(blockchain2);
		p2.addNew_BC(blockchain2); // shortest BC current_BC should not be changed to it
		p3.addNew_BC(blockchain2);
		System.out.println("\n-------------------------\n");
		p1.addNew_BC(blockchain3); // longest so current_BC must be changed to it
		p2.addNew_BC(blockchain3); // longest so current_BC must be changed to it
		p3.addNew_BC(blockchain3); // longest so current_BC must be changed to it
		System.out.println("\n-------------------------\n");
		System.out.println("\n-------------------------\n");
		
/*		
		 lets add now new blocks to our blockchains and see what will happen
		 We will add some blocks that matches "Blockchain2" which in index 1 
		 in our arrList_BC the new block must now go to this blockchain only 
		 as this block prev hash depends only on the last block of this BC.
		 once u add a new block correctly .. it will go to update current used
		 block chain to use the longest one of them if it is needed.
*/		
		

		int lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 is : " +lastIndex);
		String latestBlockHash_of_BC2 = blockchain2.latestBlock().getHash();
		Block b1 = new Block(4, System.currentTimeMillis(), latestBlockHash_of_BC2, "Ahmed sent 200 $ to Mark");
		p1.addNew_Block(b1); 
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 is after insertion the new block : " +lastIndex);
		System.out.println("\n-------------------------\n");
	
		
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 is : " +lastIndex);
		latestBlockHash_of_BC2 = blockchain2.latestBlock().getHash();
		Block b2 = new Block(5, System.currentTimeMillis(), latestBlockHash_of_BC2, "Ahmed sent 200 $ to Mark");
		p1.addNew_Block(b2);
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 after insertion the new block is: " +lastIndex);
		System.out.println("\n-------------------------\n");
		
		
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain3 is : " +lastIndex);
		latestBlockHash_of_BC2 = blockchain2.latestBlock().getHash();
		Block b3 = new Block(6, System.currentTimeMillis(), latestBlockHash_of_BC2, "Ahmed sent 200 $ to Mark");
		p1.addNew_Block(b3);
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 after insertion the new block is: " +lastIndex);
		System.out.println("\n-------------------------\n");

		
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 is : " +lastIndex);
		latestBlockHash_of_BC2 = blockchain2.latestBlock().getHash();
		Block b4 = new Block(7, System.currentTimeMillis(), latestBlockHash_of_BC2, "Ahmed sent 2890 $ to Mark");
		p1.addNew_Block(b4);
		lastIndex = blockchain2.latestBlock().getIndex();
		System.out.println("Last index in Blockchain2 after insertion the new block is: " +lastIndex);
		System.out.println("\n-------------------------\n");
		System.out.println("\n-------------------------\n");
		System.out.println("\n-------------------------\n");
		System.out.println("-------- Thank You for watching ^_^ --------");
		
//		p1.updateCurrentlyUsed_BC();
//		System.out.println("BC3 length is: "+blockchain3.getLenght());
//		System.out.println("BC1 length is: "+blockchain1.getLenght());
//		System.out.println("\n-------------------------\n");
////	    System.out.println("Is our Blockchain valid ? " + blockchain3.isBlockChainValid());
////	    System.out.println(blockchain3);
////	    
////	    System.out.println("------------------------------------\n");
////	    myNetwork.informRandomly(blockchain3.toString());
////	    System.out.println("Person 1 Cache file : { " + p1.getCache_Hitroy() + "}");
////	    
////	    System.out.println("------------------------------------\n");
////	    System.out.println("Person 3 Cache file : { " + p3.getCache_Hitroy() + "}");
	}
}
