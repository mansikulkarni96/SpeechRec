package main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Controller extends JPanel implements ActionListener{

	private JTextArea display; 
	private JTextArea lineNum;
	//processing unit
	public Process process;
	String [] currentFile;
	JavaSoundRecorder recorder;
	JButton enter;
	
	
	/**
	 * Constructor
	 */
	public Controller(){
		panel();
		process = new Process();
		currentFile = new String[1];
		recorder = new JavaSoundRecorder();
	}
	
	
	/**
	 * Set up the visualization
	 */
	public void panel(){
		
		lineNum = new JTextArea(40, 2);
		add(lineNum);
		lineNum.setEditable(false);
		
		display = new JTextArea(40, 40);
		add(display);
		display.setLineWrap(true);
		display.setEditable(true);
		lineNum.setText(" 1\n 2\n 3\n");
		
		enter = new JButton("⏎");
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		add(enter);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("enter")) {
			recorder.record();
		}
	}
	
}
