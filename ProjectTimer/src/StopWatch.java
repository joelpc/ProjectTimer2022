import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.awt.event.*;

import javax.swing.*;


public class StopWatch implements ActionListener {

	JFrame frame = new JFrame();
	JLabel timeLabel = new JLabel();
	JButton startButton = new JButton("Start");
	JButton resetButton = new JButton("Reset");
	JButton stopButton = new JButton("Stop");
	int elapsedTime = 0;
	int seconds = 0;
	int minutes = 0;
	int hours = 0;
	boolean started = false;
	String seconds_string = String.format("%02d", seconds);
	String minutes_string = String.format("%02d", minutes);
	String hours_string = String.format("%02d", hours);
	
	Timer timer = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			elapsedTime = elapsedTime + 1000;// sets the elapsed time to update every second
			hours = (elapsedTime/3600000);// calculation for milliseconds in an hour
			minutes = (elapsedTime/60000) % 60;// calculation for milliseconds in a minute % 60 to round
			seconds = (elapsedTime/1000) % 60;// calculation for milliseconds in a second % 60 to round
			seconds_string = String.format("%02d", seconds);// updates seconds string
			minutes_string = String.format("%02d", minutes);
			hours_string = String.format("%02d", hours);
			
			timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);//adds the updated values to the text lable
		}
	});
	
	StopWatch() {	
	
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
		timeLabel.setBounds(40, 100, 200, 100);
		timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
		timeLabel.setBorder(BorderFactory.createBevelBorder(1));
		timeLabel.setBackground( new Color( 123, 50, 200) );
		timeLabel.setOpaque(true);
		timeLabel.setHorizontalAlignment(JTextField.CENTER);
	
		startButton.setBounds(40, 200, 100, 50);
		startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		startButton.setFocusable(false);
		startButton.addActionListener(this);
		
		resetButton.setBounds(140, 200, 100, 50);
		resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		
		frame.add(resetButton);
		frame.add(startButton);
		frame.add(timeLabel);
		frame.setTitle("Coding Stopwatch");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit out of application
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.black);	
		frame.setResizable(false);// prevent the frame from being resized
		frame.setSize(300, 400); // sets the x-dimension and the y-dimension of the frame
		frame.setVisible(true); // make the frame visible
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startButton) {// action listener for is pressed
			start();// method call to start the timer
			if(started == false) {// if 
				started = true;
				startButton.setText("Stop");
				start();
			}
			else {
				started = false;
				startButton.setText("Start");
				stop();
			}
		}
		if(e.getSource() == resetButton) {
			started =false;
			startButton.setText("Start");
			restart();
		}
		
		
	}
	void start () {
		timer.start();
	}
	void stop() {
		timer.stop();
	}
	void restart() {
		timer.stop();
		elapsedTime = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		seconds_string = String.format("%02d", seconds);// updates seconds string
		minutes_string = String.format("%02d", minutes);
		hours_string = String.format("%02d", hours);
		
		timeLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);//adds the updated values to the text label
	}
}
