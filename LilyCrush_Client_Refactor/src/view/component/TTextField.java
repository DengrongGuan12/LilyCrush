package view.component;

import java.awt.Color;

import javax.swing.JTextField;

public class TTextField extends JTextField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TTextField(){
		super();
		this.setForeground(Color.black);
		this.setCaretColor(Color.gray);
		this.setSelectionColor(Color.gray);
		this.setSelectedTextColor(Color.white);
		this.setBorder(null);
		this.setOpaque(false);
	}

}
