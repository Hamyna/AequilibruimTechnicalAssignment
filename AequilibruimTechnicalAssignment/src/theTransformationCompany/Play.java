package theTransformationCompany;

import java.util.List;
import java.util.Scanner;

public class Play  { 
	
	public TeamDO autoBotsTeamResult; 
	public TeamDO deceptionsTeamResult;
	Scanner inputReader = new Scanner(System.in); 

	BattleServiceImpl battleService = new BattleServiceImpl(); 
	
	public void start(TeamDO autoBots, TeamDO deceptions) {  
		
    	inputReader.close(); //close input stream 
		//sort teams 
		battleService.sortTeamMembersByRank(autoBots.getPlayers()); 
		battleService.sortTeamMembersByRank(deceptions.getPlayers()); 
		
		List<BattleDO> battles = battleService.setBattleStage(autoBots.getPlayers(), deceptions.getPlayers()); 
		System.out.println(battles.size() + " Battle(s).");   
		
		//start fighting 
		this.fight(battles); 
		//end fight 
		boolean tieInd = battleService.calculateWinnersAndLosers(autoBots, deceptions); 
		
		if(tieInd){ 
			try {
				finalize();
			} catch (Throwable e) {
					e.printStackTrace();
			}  
		}
		
	} 
	
	protected void finalize() throws Throwable {
	     try {
	        //clear all objects  - destructor? 
	     } finally {
	         super.finalize();
	     }
	 }
	
    private void fight(List<BattleDO> battles){ 
    	autoBotsTeamResult = new TeamDO(); 
    	deceptionsTeamResult = new TeamDO(); 
    	
    	for(BattleDO aBattle : battles) { 
			//check if special rule applies 
			aBattle = battleService.checkSpeacialRules(aBattle); 
			if(null == aBattle) {
				try {
					finalize();
				} catch (Throwable e) {
 					e.printStackTrace();
				} 
				break;  //end
			}
			
			if(battleService.noWinnerFound(aBattle)) aBattle = battleService.fightByCourageAndStrenght(aBattle); 
			if(battleService.noWinnerFound(aBattle)) aBattle = battleService.fightBySkills(aBattle);
			if(battleService.noWinnerFound(aBattle)) aBattle = battleService.fightByOverAllRating(aBattle);  
			 
			autoBotsTeamResult.getPlayers().add(aBattle.getaAutoBoot()); 
			deceptionsTeamResult.getPlayers().add(aBattle.getaDeception()); 
 		} 
    }
	
    private String followInstructions(){ 
    	return "cant continue without following instructions"; 
    }
    
    private TeamDO getDataFromUser(String teamType){ 
    	TeamDO teamDO = new TeamDO(); 
    	
    	teamDO.setTeamType(teamType.toUpperCase());
    	String teamName = (teamType.equalsIgnoreCase("A"))? "Autobot" : "Deception"; 
    	teamDO.setTeamName(teamName);

    	System.out.println("Follow instrustion below to input data");
    	System.out.println("Enter player for team " + teamName); 
    
	    	System.out.println("How many players do you have for Team "+teamName);
	    	int teamSize = inputReader.nextInt();
	    	System.out.println("Now you would input the property for all players of "+teamName);

	    	if(teamSize > 0){ 
	    		for(int i=1; i<(teamSize+1); i++){ 
	    			TransformerDO aPlayer = new TransformerDO(); 
	    			aPlayer.setTeamType(teamType); 
	    			
	    			System.out.println("Input a name for player " + i + " in team "+teamName);
	    			
	    			String playerName = inputReader.next(); 
	    			aPlayer.setPlayerName(playerName);
	    			
	    			aPlayer.setStrenght(this.inputProperty("strenght", playerName));
	    			aPlayer.setIntelligence(this.inputProperty("intelligence", playerName));
	    			aPlayer.setSpeed(this.inputProperty("speed", playerName) );
	    			aPlayer.setEndurance(this.inputProperty("endurance", playerName) );
	    			aPlayer.setRank(this.inputProperty("rank", playerName) );
	    			aPlayer.setCourage(this.inputProperty("courage", playerName) );
	    			aPlayer.setFirePower(this.inputProperty("firePower", playerName) );
	    			aPlayer.setSkill(this.inputProperty("skill", playerName) );
	    			
	    			teamDO.getPlayers().add(aPlayer);
	    		}
	    	}else{ 
	    		followInstructions();
	    	} 
	    	
    	return teamDO; 
    }
    
    private int inputProperty(String propertyName, String playerName){ 
     	System.out.println("Input "+propertyName+" for player "+playerName);
    	
    	return inputReader.nextInt(); 
    	
    }
    
	public static void main(String[] args) {
 		Play transformerGame = new Play(); 

		TeamDO autoBots =  transformerGame.getDataFromUser("A"); 
		TeamDO deceptions = transformerGame.getDataFromUser("D"); 
		
		transformerGame.start(autoBots, deceptions);
		
 	} 
}
