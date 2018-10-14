package com.example.speech;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

public class SpeechRecApp {

	/**
	 * Create a JFrame that holds the Unit.
	 * 
	 **/
	public static void main( String[] args )
	{
		JFrame guiFrame;

		// create a new JFrame to hold a single TreePainting
		guiFrame = new JFrame( "Voice code editor");

		// set size
		guiFrame.setSize(700, 700);

		// create a ViewPanel and add it
		guiFrame.add( new ViewPanel());

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		// show frame
		guiFrame.setVisible( true );
	}
}
