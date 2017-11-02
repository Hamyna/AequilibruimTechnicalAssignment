package theTransformationCompany;

/**
 * base class for a every fighter 
 */ 
public class TransformerDO extends Object{
	
	
	public static final String STATUS_CODE_PENDING		= "P";
	
	private String	teamType;
	private String playerName; 
	
	private Integer strenght; 
	private Integer intelligence; 
	private Integer speed; 
	private Integer endurance; 
	private Integer rank; 
	private Integer courage; 
	private Integer firePower; 
	private Integer skill; 
	
	private boolean resultInd = false; //if true, this transformer won, if false this transformer lost the battle.
	private boolean isSkipped = false;  //if player does not have a match, it should be skiped. default it is false, when skipped set to true
	
	private int overAllRating;   
	
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public Integer getStrenght() {
		return strenght;
	}
	public void setStrenght(Integer strenght) {
		this.strenght = strenght;
	}
	public Integer getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}
	
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public Integer getEndurance() {
		return endurance;
	}
	public void setEndurance(Integer endurance) {
		this.endurance = endurance;
	}
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	public Integer getCourage() {
		return courage;
	}
	public void setCourage(Integer courage) {
		this.courage = courage;
	}
	
	public Integer getFirePower() {
		return firePower;
	}
	public void setFirePower(Integer firePower) {
		this.firePower = firePower;
	}
	
	public Integer getSkill() {
		return skill;
	}
	public void setSkill(Integer skill) {
		this.skill = skill;
	}
	
	//calculate attribute
	public int getOverAllRating() {
		this.overAllRating = this.calculateOverallRating();
		return overAllRating; 
	}  
	
	private int calculateOverallRating(){ 
		return ( this.strenght + this.intelligence + this.speed + this.endurance + this.firePower);  
	}
	 
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof TransformerDO)) {
			return false;
		}
		TransformerDO other = (TransformerDO) obj;
		if (playerName == null || playerName.isEmpty()) {
			if (other.playerName != null) {
				return false;
			}
		} else if (!playerName.equalsIgnoreCase(other.playerName)) {
				return false;
			}
			return true;
	}
	
	public String getTeamType() {
		return teamType;
	}
	public void setTeamType(String teamType) {
		this.teamType = teamType;
	}
	
	public boolean isResultInd() {
		return resultInd;
	}
	public void setResultInd(boolean resultInd) {
		this.resultInd = resultInd;
	}
	public boolean isSkipped() {
		return isSkipped;
	}
	public void setSkipped(boolean isSkipped) {
		this.isSkipped = isSkipped;
	}	 
	 
}
