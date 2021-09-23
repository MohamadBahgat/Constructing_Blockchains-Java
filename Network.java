package model;

import java.util.ArrayList;
import java.util.Random;

public class Network {
	private ArrayList<Person> PeopleArrayList ;

	public Network() {
		PeopleArrayList = new ArrayList<>();
	}

	public ArrayList<Person> getArrList_People() {
		return PeopleArrayList;
	}
	
	public void informRandomly(String s) {
		if(!PeopleArrayList.isEmpty()) {			
			Random rand = new Random();
			ArrayList<Integer> seenInt = new ArrayList<Integer>();
			int  randPerson = rand.nextInt(PeopleArrayList.size()-1);
			int upperLimit = rand.nextInt(PeopleArrayList.size()-1);
			while(seenInt.size()<upperLimit){
				if(!seenInt.contains(randPerson)){
					PeopleArrayList.get(randPerson).updateCache(s);
					seenInt.add(randPerson);
				}
				else 
					randPerson = rand.nextInt(PeopleArrayList.size()-1);
			}
		}
	}
	
	public void informNormaly(String s) {
		if(!PeopleArrayList.isEmpty()) {
			int size = PeopleArrayList.size()-1;
			int i = 0;
			while(i<=size) {
				if(PeopleArrayList.get(i).isSeen()==false) {
					PeopleArrayList.get(i).updateCache(s);
					//ArrList_People.get(i).setSeen(true);
					//System.out.println("I am here + " + i);
					i++;
				}
			}
			
		}
		else {
			System.out.println("The Network is Empty ... add some people first");
		}
	}
	
	
	
	

}
