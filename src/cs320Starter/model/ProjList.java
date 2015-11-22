package cs320Starter.model;

import java.util.ArrayList;
import java.util.Date;

public class ProjList
{
		
		int idSeed;
		String name;
		String title;
		String desc;
		float funTarget;
		Date sDate;
		int funDur;
		int daysToGo;
		int fundCollected;
		float percentCollected;
		boolean isSponsored = false;
		ArrayList<RewardList> rewards = new ArrayList<RewardList>();
		
		public ProjList()
		{
			
		}
		
		public ProjList(int id, String title)
		{
			this.idSeed = id;
			this.title = title;
		}
		
		public ProjList(int id, String name, String title, String desc,	float funTarget, Date sDate, int funDur)
		{
			this.idSeed = id;
			this.name = name;
			this.title =  title;
			this.desc = desc;
			this.funTarget = funTarget;
			this.sDate =  sDate;
			this.funDur = funDur;
		}
		
		public boolean isSponsored() {
			return isSponsored;
		}

		public void setSponsored(boolean isSponsored) {
			this.isSponsored = isSponsored;
		}
		
		public float getPercentCollected() {
			return percentCollected;
		}
		
		public int fundCollected() {
			return fundCollected;
		}

		public void FundControl(int fundTransfer) {
			this.fundCollected = fundTransfer;
		}

		public void setPercentCollected(float percentCollected) {
			this.percentCollected = percentCollected;
		}

		public int getDaysToGo() {
			return daysToGo;
		}

		public void setDaysToGo(int daysToGo) {
			this.daysToGo = daysToGo;
		}
		
		public int getFundCollected() {
			return fundCollected;
		}

		public void setFundCollected(int fundCollected) {
			this.fundCollected = fundCollected;
		}

		public int getIdSeed()
		{
			return idSeed;
		}
		
		public void setIdSeed(int id)
		{
			this.idSeed = id;
		}
	
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public float getFunTarget() {
			return funTarget;
		}
		public void setFunTarget(float funTarget) {
			this.funTarget = funTarget;
		}
		public Date getsDate() {
			return sDate;
		}
		public void setsDate(Date sDate) {
			this.sDate = sDate;
		}
		public ArrayList<RewardList> getRewards() {
			return rewards;
		}

		public void setRewards(ArrayList<RewardList> rewards) {
			this.rewards = rewards;
		}

		public int getFunDur() {
			return funDur;
		}
		public void setFunDur(int funDur) {
			this.funDur = funDur;
		}

}