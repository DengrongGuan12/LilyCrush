package businesslogic;

import enums.Prop;
import businesslogic.managers.PropManager;
import businesslogicService.FinancialBLService;

public class FinancialBL implements FinancialBLService{
	@Override
	public void setCoins(int coins) {
		PropManager propManager=PropManager.getInstance();
		propManager.setCoins(coins);
		
	}
	public void exitPurchasePanel() {
		PropManager propManager=PropManager.getInstance();
		propManager.recoverCoins();
		
	}
	public void noChooseProp(Prop p) {
		PropManager propManager=PropManager.getInstance();
		propManager.noChooseProp(p);
	}
	public boolean chooseProp(Prop p) {
		PropManager propManager=PropManager.getInstance();
		return propManager.chooseProp(p);
		// this.buyPropsPanel.refreshCoins(this.propManager.getCoins());
		
	}
	public int getMyCoins() {
		PropManager propManager=PropManager.getInstance();
		return propManager.getCoins();

	}

}
