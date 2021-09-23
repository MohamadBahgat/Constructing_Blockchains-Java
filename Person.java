package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Person {
	private int id ;
	private String name, cache_Histroy, lastTransaction, wallet ;
	private boolean Seen = false;
	private ArrayList<Blockchain> arrList_BC ;
	private int currentUsed_BC ;
	private Block recievedNewBlock ;
	
	// ---------------- Person Constructor ---------------
	public Person(int id, String name, String cache_Histroy,
			Blockchain blockchain ) {
		this.id = id;
		this.name = name;
		this.cache_Histroy = cache_Histroy;
		arrList_BC = new ArrayList<Blockchain>();
		//currentUsed_BC = blockchain;
		if(blockchain != null && blockchain.isBlockChainValid()) {
			arrList_BC.add(blockchain);
			updateCurrentlyUsed_BC();
		}else {
			JOptionPane.showMessageDialog(null,"You are trying to enter a NOT VALID BlockChain to " 
			+ name +" on his BlockChain list \nPlease add a VALID one");
		}
	}
	
	//  ------------ Person Methods ---------------
	@SuppressWarnings("static-access")
	public void updateCurrentlyUsed_BC() {
		int length = arrList_BC.size()-1;
		System.out.println("Size of our arrList is : "+(length+1));
		if(length >= 0) {
			currentUsed_BC = 0 ;//arrList_BC.get(0); // set to be the first element in the beginning
			int i = 0;
			while(i<= length-1) {
				if(arrList_BC.get(i+1).getLenght() >= arrList_BC.get(i).getLenght()) {
					currentUsed_BC =  i+1;  //arrList_BC.get(i+1);
					//System.out.println("----" + arrList_BC.get(i).getLenght());
					//currentUsed_BC.setBlocks(arrList_BC.get(i+1).getBlocks());
					//System.out.println("i+1 length: " +arrList_BC.get(i+1).getLenght() );
					//System.out.println("i length: " +arrList_BC.get(i).getLenght() );
				}
				i++;
			}
			System.out.println(this.name + "'s currently use this Blockchain in index :"+ currentUsed_BC);//arrList_BC.get(currentUsed_BC).toString()); //+ "\nBlockchain info : "+currentUsed_BC.toString());
			this.updateCache(arrList_BC.get(currentUsed_BC).toString());
			//System.out.println(currentUsed_BC.toString());
		}else {
		System.out.println("Can not be updated ... Your BlockChain List is empty !");
		}
	}
	public void addNew_BC(Blockchain bc) {
		if (bc.isBlockChainValid()) {
			arrList_BC.add(bc);
			System.out.println("Successfully added this new BlockChain to the arrList_BC");
			updateCurrentlyUsed_BC();
		}else {
			System.out.println("You are trying to add a NOT VALID BlockChain to your BlockChain list ... ");
			System.out.println("Please check your BlockChain validity first");
		}
	}
	public void addNew_Block(Block b) {
		if(b.getIndex() > 0) {
			if(!arrList_BC.isEmpty()) {
				boolean found = false;
				int index = 0;
				for(int i=0; i <= arrList_BC.size()-1; i++) {
					if(arrList_BC.get(i).latestBlock().getHash().equals(b.getPreviousHash())) {
						found = true;
						index = i;
						break;
					}
				}
				if (found == true) { 
					// we have found a blockchain this block belongs to it
					// then update the currentUsed_Blockchain as it may be a new longer length so we choose it
					arrList_BC.get(index).addBlock(b);
					updateCurrentlyUsed_BC();
					System.out.println("Successfully added this new Block to a Blockchain with index : " + index);
				}else { //  we have not found a blockchain this block belongs to it
					JOptionPane.showMessageDialog(null, "Unvaild Block ... We have not found a blockchain this Block belongs to it"
							, "No matching has been found ... Unvalid Block", JOptionPane.ERROR_MESSAGE);
				}
			}else {
				// arrList_BC is empty at this moment 
				// then create a new blockchain and then add it 
				// and then change its index to be number 1 
				//after the genesise block in the new BlockChain
				//Blockchain 
				JOptionPane.showMessageDialog(null, "You do not have any Blockchain yet to add this new Block to one of them. \nTry to add a new blockchain first in your Blockchain list" 
						, "Blockchain list is Empty", JOptionPane.ERROR_MESSAGE);

			}
		}else {
			// create new blockchain and add this block to it to be the second block after the gensise block
			 JOptionPane.showMessageDialog(null, "Unvaild block index ... OR You can not create the first block by your own", "Unvalid Block index", JOptionPane.ERROR_MESSAGE);
		}
	}
	public boolean isSeen() {
		return Seen;
	}
	public String makeTransaction(Person reciever, Person sender , double amount) {
		reciever.wallet = reciever.wallet + "\n" + "You have recieved " +amount + " $ from " + sender.name;
		lastTransaction = sender.name + " has sent " + amount + " $  to  " + reciever.name +"."; 
		return lastTransaction ;
	}
	public void updateCache(String s) {
//		StringBuilder builder = new StringBuilder();
//		builder.append(Cache_Hitroy).append("\n").append(s);
		cache_Histroy = cache_Histroy+"\n"+s;
	}
	
	// ---------------- setters & gtters  -------------------
		public String getTransaction() {
			return lastTransaction;
		}
		public void setTransaction(String transaction) {
			lastTransaction = transaction;
		}
		public int getID() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getCache_Hitroy() {
			return cache_Histroy;
		}
		public String getWallet() {
			return wallet;
		}
		public void setWallet(String wallet) {
			this.wallet = wallet;
		}
		public String getLastTransaction() {
			return lastTransaction;
		}
		public ArrayList<Blockchain> getBlockChainsArrayList() {
			return arrList_BC;
		}
		public int getCurrentBlockChain() {
			return currentUsed_BC;
		}
		public Block getRecievedBlock() {
			return recievedNewBlock;
		}
		public void setSeen(boolean seen) {
			Seen = seen;
		}
		// -------------- end of setters & getters --------------

}
