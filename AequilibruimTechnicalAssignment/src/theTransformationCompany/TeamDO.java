package theTransformationCompany;

import java.util.ArrayList;
import java.util.List;

public class TeamDO extends Object{ 
	
	/*public TeamDO(List<TransformerDO> players, 
					String teamType, 
					String teamName, 
					Integer numberOfRivalEliminatedTeam){ 
		this.players = players;
		this.teamType = teamType; 
		this.teamName = teamName; 
		this.numberOfRivalEliminatedTeam = numberOfRivalEliminatedTeam; 
	} */
	
	private List<TransformerDO> players = new ArrayList<TransformerDO>(0); 
	private String teamType;
	private String teamName;  
	private Integer numberOfRivalEliminatedMember = 0; 
	
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public List<TransformerDO> getPlayers() {
		return players;
	}
	public void setPlayers(List<TransformerDO> players) {
		this.players = players;
	}
	
	public Integer getNumberOfRivalEliminatedMember() {
		return numberOfRivalEliminatedMember;
	}
	public void setNumberOfRivalEliminatedMember(Integer numberOfRivalEliminatedMember) {
		this.numberOfRivalEliminatedMember = numberOfRivalEliminatedMember;
	} 
}
