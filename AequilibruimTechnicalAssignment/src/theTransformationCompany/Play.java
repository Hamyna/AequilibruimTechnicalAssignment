package theTransformationCompany;

import java.util.ArrayList;
import java.util.List;

public class Play  { 
	
	public TeamDO autoBotsTeamResult; 
	public TeamDO deceptionsTeamResult;
	
	BattleServiceImpl battleService = new BattleServiceImpl(); 
	
	public void start(TeamDO autoBots, TeamDO deceptions) {  
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
	
	public static void main(String[] args) {
		//input data 
 		
		Play transformerGame = new Play();
		
		//set autoBots 
		TeamDO autoBots = new TeamDO(); 
		//set team type
		autoBots.setTeamType( TEAM_TYPE_CODE.A.toString() );
		autoBots.setTeamName("AutoBots"); 
		List<TransformerDO> autoBotsPlayers = new ArrayList<TransformerDO>(0); 
		
		TransformerDO a1 = new TransformerDO(); 
		a1.setPlayerName("Bluestreak"); 
		a1.setTeamType(TEAM_TYPE_CODE.A.toString());
		a1.setStrenght(6);
		a1.setIntelligence(6);
		a1.setSpeed(7);
		a1.setEndurance(9); 
		a1.setRank(5); 
		a1.setCourage(2);
		a1.setFirePower(9);
		a1.setSkill(7);
		autoBotsPlayers.add(a1); 
		
		TransformerDO a2 = new TransformerDO(); 
 		a2.setPlayerName("Hubcap"); 
 		a2.setTeamType(TEAM_TYPE_CODE.A.toString());
 		a2.setStrenght(4);
 		a2.setIntelligence(4);
		a2.setSpeed(4);
		a2.setEndurance(4); 
		a2.setRank(4); 
		a2.setCourage(4);
		a2.setFirePower(4);
		a2.setSkill(4);
		autoBotsPlayers.add(a2);
		
		autoBots.setPlayers(autoBotsPlayers);
		
		TeamDO deceptions = new TeamDO(); 
		deceptions.setTeamType( TEAM_TYPE_CODE.D.toString() );
		deceptions.setTeamName("Deceptions"); 
		
		List<TransformerDO> deceptionPlayrs = new ArrayList<TransformerDO>(0); 
		TransformerDO d1 = new TransformerDO(); 
		d1.setPlayerName("SoundWave"); 
		d1.setTeamType(TEAM_TYPE_CODE.D.toString());
		d1.setStrenght(8);
		d1.setIntelligence(9);
		d1.setSpeed(2);
		d1.setEndurance(6); 
		d1.setRank(7); 
		d1.setCourage(5);
		d1.setFirePower(6);
		d1.setSkill(10);
		deceptionPlayrs.add(d1); 
		
		deceptions.setPlayers(deceptionPlayrs);
		
		transformerGame.start(autoBots, deceptions);
		
 	} 
}
