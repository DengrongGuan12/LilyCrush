package view.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * 
 * 计分的Label
 *
 */
public class PointLabel extends JLabel{
	
	private double point=0;
	
	private boolean preIfMax = false;
	
	public PointLabel(){
		setForeground(Color.WHITE);
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 25));
		setOpaque(false);
		setPoint(point);
		setVisible(true);
	}
	
	//将分数重置为零
	public void reset(){
		point=0;
		preIfMax = false;
		setPoint(0);
	}
	
	//如果是最高分的话设置为true，会有数字变大的效果
	public synchronized void update(int incre,boolean ifMax){
		if(preIfMax == false && ifMax == true){
			setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 45));
		}else if (preIfMax == true) {
			setFont(new Font("Bauhaus 93", Font.BOLD | Font.ITALIC, 35));
		}
		
		preIfMax = ifMax;
		setPoint(incre);
	}
	
	private void setPoint(double incre){
		point += incre;
		if(Math.round(point)-point ==0){
			setText( String.valueOf((long)point));
		}
		else{
			setText(String.valueOf(point));
		}
	}

}
