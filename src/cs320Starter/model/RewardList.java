package cs320Starter.model;

public class RewardList
{
	int amount;
	
	String reward;
	
	public RewardList()
	{
		
	}
	
	public RewardList( int amount, String reward)
	{
		this.amount = amount;
		this.reward = reward;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}
	
	
}
