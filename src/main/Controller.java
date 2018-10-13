package main;

import java.awt.BorderLayout;
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
	RecordThread recorder;
	JButton enter;
	
	
	/**
	 * Constructor
	 */
	public Controller(){
		process = new Process();
		currentFile = new String[1];
		recorder = new RecordThread();
		panel();
	}
	
	
	private void process() {
		boolean notDone = true;

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
		
		enter = new JButton("⏎");
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		add(enter,  BorderLayout.SOUTH);
		lineNum();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("enter")) {
			//activate recording - to speech stuff 
			//returns string 
			//call proccessing unit, passing current file 
			//returns an array of strings
			//set TextArea to array of Strings.
			recorder.record();
			/*String newStr = SpeechRecognizer.recognize();
			currentFile = Process.process(currentFile, newStr);
			
			display.setText(currentFile.toString()); //not sure what tostring will return
			lineNum();
			*/
		}
	}
	
	
	public void lineNum() {
		String num = "";

		for(int i = 0; i< currentFile.length; i++) {
			System.out.print("ho");
			num = num + " " + (i+1) +"\n";
		}
		
		lineNum.setText(num);

	}
}
