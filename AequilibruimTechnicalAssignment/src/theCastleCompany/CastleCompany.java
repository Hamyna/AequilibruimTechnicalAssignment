package theCastleCompany;

import java.util.ArrayList;
import java.util.List; 

public class CastleCompany {    
	
	public static void main(String[] args) { 
		List<Integer> stretchOfland  = new ArrayList<Integer>(0); 
		//add data {100, 1, 2, 3, 7, 2, 1, 9, 3, 10}
		stretchOfland.add(6); 
		stretchOfland.add(1);
		stretchOfland.add(4);
		
		CastleCompany castleCompany = new CastleCompany(); 
		Integer numberOfBuildableCastles = castleCompany.calculateBuildableCastles(stretchOfland);
		
		System.out.println(numberOfBuildableCastles + " can be built"); 
	}
	
	public Integer calculateBuildableCastles(List<Integer> list) {
 		Integer numberOfCastles = 0; 
		if(!list.isEmpty()) { 
 			numberOfCastles++; 
		}
 		if (list.size() > 2 ){ 
			for(int i=1; i<(list.size() - 1); i++) { 
				Integer number = list.get(i); 
				Integer rightNeigbour = list.get(i+1); 
				Integer leftNeigbour = list.get(i-1);
				boolean isPeak = this.isPeak(number, rightNeigbour, leftNeigbour);
				boolean isValley = this.isValley(number, rightNeigbour, leftNeigbour); 
				
				if(isValley) { 
 					numberOfCastles++; 
				}	
				if(isPeak) { 
 					numberOfCastles++; 
				}
			} 
		} 
		
 		return numberOfCastles;
	}
	 
	public boolean isPeak(Integer number, Integer rightNeigbour, Integer leftNeighour) {		
		return (number != rightNeigbour &&  number != leftNeighour 
				&& number>rightNeigbour && number>leftNeighour) ? true : false;
	}
	
	public boolean isValley(Integer number, Integer rightNeigbour, Integer leftNeighour) {		
		return (number != rightNeigbour &&  number != leftNeighour 
				&& number<rightNeigbour && number<leftNeighour) ? true : false;
	} 

}
