package theTransformationCompany;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BattleServiceImpl {
	
	public static final String specialPlayerOptimus = "Optimus Prime";
	public static final  String specialPlayerPredaking = "Predaking";
	
	public void sortTeamMembersByRank(List<TransformerDO> _aTeam){ 
		_aTeam.sort(Comparator.comparing(TransformerDO::getRank).reversed()); 
	}
	
	public List<BattleDO> setBattleStage(List<TransformerDO> autorobotsTeam, List<TransformerDO> deceptionTeam){  
		int noOfBattle = 0;
	
		if(autorobotsTeam.size() <= deceptionTeam.size()) { 
			noOfBattle = autorobotsTeam.size(); 
			deceptionTeam = this.skipNotMachedPlayers(deceptionTeam, autorobotsTeam.size());  
		}
		else{ 
			noOfBattle = deceptionTeam.size();
			autorobotsTeam = this.skipNotMachedPlayers(autorobotsTeam, deceptionTeam.size());  
		}
		
		List<BattleDO> battles  = new ArrayList<BattleDO>(noOfBattle); 
		
		for(int i=0; i<noOfBattle; i++) { 
			BattleDO aBattle = new BattleDO();
			aBattle.setaAutoBoot(autorobotsTeam.get(i));
			aBattle.setaDeception(deceptionTeam.get(i));  
			battles.add(aBattle); 
		}
		
		return battles; 
	}
	
	public BattleDO fightByCourageAndStrenght(BattleDO aBattle) { 
		
		if(aBattle.getaAutoBoot().getStrenght() > aBattle.getaDeception().getStrenght()
				&& aBattle.getaAutoBoot().getCourage() > aBattle.getaDeception().getCourage() )  { 
			
			if(this.geaterThanByNumber( aBattle.getaAutoBoot().getStrenght(), aBattle.getaDeception().getStrenght(), 3)
					&& this.geaterThanByNumber( aBattle.getaAutoBoot().getCourage(), aBattle.getaDeception().getCourage(), 4)) { 
				aBattle.getaAutoBoot().setResultInd(true); 
				//aBattle.getaDeception().setResultInd(false); 
			
			}
		}
		if(aBattle.getaDeception().getStrenght() > aBattle.getaAutoBoot().getStrenght()
				&& aBattle.getaDeception().getCourage() > aBattle.getaAutoBoot().getCourage() )  { 
			
			if(this.geaterThanByNumber( aBattle.getaDeception().getStrenght(), aBattle.getaAutoBoot().getStrenght(), 3)
					&& this.geaterThanByNumber( aBattle.getaDeception().getCourage(), aBattle.getaAutoBoot().getCourage(), 4)) { 
			
				aBattle.getaDeception().setResultInd(true); 
				//aBattle.getaAutoBoot().setResultInd(false); 
			}
		}
		
		return aBattle; 
	}
	
	public BattleDO fightBySkills(BattleDO aBattle) { 
		
		if( aBattle.getaAutoBoot().getSkill() > aBattle.getaDeception().getSkill() ) {
			
			if( this.geaterThanByNumber(aBattle.getaAutoBoot().getSkill() , aBattle.getaDeception().getSkill() , 3) ) { 
				
				aBattle.getaAutoBoot().setResultInd(true); 
				//aBattle.getaDeception().setResultInd(false);
			}
		}
		
		if( aBattle.getaDeception().getSkill() > aBattle.getaAutoBoot().getSkill() ) {
			
			if( this.geaterThanByNumber(aBattle.getaDeception().getSkill() , aBattle.getaAutoBoot().getSkill() , 3) ) { 
				
				aBattle.getaDeception().setResultInd(true); 
				//aBattle.getaAutoBoot().setResultInd(false);
			}
			
		}
		
		return aBattle;
	}
	
	public BattleDO fightByOverAllRating(BattleDO aBattle) { 
	
		//only fight by overall rating if no winner 
		if(!aBattle.getaAutoBoot().isResultInd() && !aBattle.getaDeception().isResultInd() ) {
			if( aBattle.getaAutoBoot().getOverAllRating() > aBattle.getaDeception().getOverAllRating() ) {
			
				aBattle.getaAutoBoot().setResultInd(true); 
				//aBattle.getaDeception().setResultInd(false); 
			
			} 
			if( aBattle.getaDeception().getOverAllRating() > aBattle.getaAutoBoot().getOverAllRating() ) { 
				
				aBattle.getaDeception().setResultInd(true); 
				//aBattle.getaAutoBoot().setResultInd(false);
 			
			}
		}
		
		
		return aBattle; 
	}
	
	public boolean noWinnerFound(BattleDO aBattleDO) { 
		if( !aBattleDO.getaAutoBoot().isResultInd() && !aBattleDO.getaDeception().isResultInd() ) { 
			return true; 
		}
		return false; 
	}
	
	private boolean geaterThanByNumber(Integer greaterValue, Integer lesserValue, int minDifference) {
		if( (greaterValue - lesserValue)  >= minDifference ) { 
			return true; 
		}else return false; 
	}
	
	public BattleDO checkSpeacialRules(BattleDO aBattleDO) {
		 if(isSpecialPleayer(aBattleDO.getaAutoBoot()) && isSpecialPleayer(aBattleDO.getaDeception())) { 
			 return null; //when null is return, process will terminate
 		 }
		 else{ 
			if(isSpecialPleayer(aBattleDO.getaAutoBoot())) { 
				 aBattleDO.getaAutoBoot().setResultInd(true); 
				 aBattleDO.getaDeception().setResultInd(false);  
				 return aBattleDO; 
				 
			 }
			 else if(isSpecialPleayer(aBattleDO.getaDeception())) { 
				 aBattleDO.getaAutoBoot().setResultInd(false); 
				 aBattleDO.getaDeception().setResultInd(true);  
				 return aBattleDO; 
			 }else { 
				 return aBattleDO; 
			 }
		 } 
		 
		 
	}
	
	private boolean isSpecialPleayer(TransformerDO aTransformerDO) { 
		return (aTransformerDO.getPlayerName().equalsIgnoreCase(specialPlayerOptimus)
				|| aTransformerDO.getPlayerName().equalsIgnoreCase(specialPlayerPredaking)) ? true : false; 
				
	}
	
	public boolean calculateWinnersAndLosers(TeamDO autoBotsTeam, TeamDO deceptionsTeam) {  
		
		Integer deceptionsLosers =  Integer.valueOf( (int) deceptionsTeam.getPlayers().stream().filter( t -> (!t.isResultInd() && !t.isSkipped())).count());
		autoBotsTeam.setNumberOfRivalEliminatedMember(deceptionsLosers); 
		
		Integer autoBotsLosers =  Integer.valueOf( (int) autoBotsTeam.getPlayers().stream().filter( t -> (!t.isResultInd() && !t.isSkipped())).count());
		deceptionsTeam.setNumberOfRivalEliminatedMember(autoBotsLosers); 
		
		if(autoBotsTeam.getNumberOfRivalEliminatedMember() > deceptionsTeam.getNumberOfRivalEliminatedMember()) { 
			System.out.println("Winning Team is - " + autoBotsTeam.getTeamName().toString());
			printTeamSurvivors(autoBotsTeam); 
			
			System.out.println("Surviving from the losing Team (" + deceptionsTeam.getTeamName() + ")" + " are below");
			printTeamSurvivors(deceptionsTeam); 
			
		}
		
		else if(autoBotsTeam.getNumberOfRivalEliminatedMember() < deceptionsTeam.getNumberOfRivalEliminatedMember()) {
			System.out.println("Winning Team " + deceptionsTeam.getTeamName() ); 
			printTeamSurvivors(deceptionsTeam); 
			
			System.out.println("Surviving from the losing Team (" + autoBotsTeam.getTeamName() + ")" + " are below");
			printTeamSurvivors(autoBotsTeam); 
			
		}
		
		else { 
			return true;  //battle is a tie, quit 
		}
		
		return false; 
	}  

	private void printTeamSurvivors(TeamDO aTeam){ 
		
		for(TransformerDO aLoser : aTeam.getPlayers()) {
			//survivor = ( ( isResultInd == true && isSkipped = false) OR  ( isResultInd == false && isSkipped = true ) )
			if( (aLoser.isResultInd()  && !aLoser.isSkipped()) || (!aLoser.isResultInd() && aLoser.isSkipped())) { 
				System.out.println( " " + aLoser.getPlayerName() );
			}
			
		}
	}
	
    private List<TransformerDO> skipNotMachedPlayers(List<TransformerDO> team, int battleSize){ 
    	int notMatched = ( team.size() -  battleSize );
		for(int i=0; i<notMatched; i++){ 
			team.get( (battleSize - i) ).setSkipped(true); 
		}
		
		return team; 
    }
    
 
    

}
