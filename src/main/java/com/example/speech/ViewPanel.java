package com.example.speech;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class ViewPanel extends JPanel{

	/**
	 * constructor
	 */
	public ViewPanel(){
	
		super( new BorderLayout() );
		
		add( new Controller(), BorderLayout.CENTER);	
	}
}
