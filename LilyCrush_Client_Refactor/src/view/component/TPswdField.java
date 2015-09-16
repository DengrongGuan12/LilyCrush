package view.component;

import java.awt.Color;

import javax.swing.JPasswordField;

public class TPswdField extends JPasswordField{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TPswdField(){
		super();
		this.setCaretColor(Color.gray);
		this.setSelectionColor(Color.gray);
		this.setBorder(null);
		this.setOpaque(false);
	}
	
}
