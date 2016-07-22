/**********************************************************************************
** Programmer: Luke Fitzgerald
** Program Name: Treadmill
** Purpose: Create a program that simulates a treadmill
***********************************************************************************/

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.color.*;
import java.text.NumberFormat;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.*;
import java.util.Arrays;


public class Treadmill extends JFrame
{
	private JLabel speedLabel, inclineLabel, distanceLabel, quickInclineLabel, 
					quickSpeedLabel, paceLabel, timerLabel;
	private JTextField speedTF, inclineTF, distanceTF, paceTF, timeTF;
	private JButton onOroff, start, stopReset, inclineUp, inclineDown, speedUp, speedDown,
					quickIncline1, quickIncline2, quickIncline4, quickIncline6, 
					quickSpeed3, quickSpeed4, quickSpeed6, quickSpeed7;
	private Window2 w2;
	private Window3 w3;
	ButtonHandler bhandle;
	Clock clock1;
	TreadmillModel2 treadmill1;
	Timer stopwatch[];
	javax.swing.Timer timer1;
	RunTracker runTracker1;
	int onOffControl;
	javax.swing.Timer displayDistanceTimer;
	DistanceTimer displayDistance;
	Font f;
	
public Treadmill()
{
	super();
	Container window1 = getContentPane();
	window1.setLayout(null);
	//System.out.println("Hello world"):
	//FONTS
	f = new Font("Times New Roman", Font.BOLD, 24);

	//LABELS
	speedLabel = new JLabel("Speed");
	window1.add(speedLabel);
	speedLabel.reshape(25, 25, 50, 25);
	inclineLabel = new JLabel("Incline %");
	window1.add(inclineLabel);
	inclineLabel.reshape(125, 25, 50, 25);
	distanceLabel = new JLabel("Distance");
	window1.add(distanceLabel);
	distanceLabel.reshape(380, 25, 50, 25);
	quickSpeedLabel = new JLabel("Quick Speed Options");
	window1.add(quickSpeedLabel);
	quickSpeedLabel.reshape(215, 235, 300, 25);
	quickInclineLabel = new JLabel("Quick Incline Options");
	window1.add(quickInclineLabel);
	quickInclineLabel.reshape(215, 285, 300, 25);
	paceLabel = new JLabel("Pace (Min/Mile)");
	window1.add(paceLabel);
	paceLabel.reshape(475, 25, 100, 25);
	timerLabel = new JLabel("Time");
	window1.add(timerLabel);
	timerLabel.reshape(260, 25, 100, 25);
	
	//TEXTFIELDS
	speedTF = new JTextField(15);
	window1.add(speedTF);
	speedTF.reshape(25, 50, 50, 25);
	inclineTF = new JTextField(15);
	window1.add(inclineTF);
	inclineTF.reshape(125, 50, 50, 25);
	distanceTF = new JTextField();
	window1.add(distanceTF);
	distanceTF.reshape(380, 50, 50, 25);
	paceTF = new JTextField(15);
	window1.add(paceTF);
	paceTF.reshape(475, 50, 100, 25);
	timeTF = new JTextField(15);
	window1.add(timeTF);
	timeTF.reshape(225, 50, 100, 25);
	
	//BUTTONS
	onOroff = new JButton("On/Off");
	window1.add(onOroff);
	onOroff.reshape(200, 100, 150, 100);
	onOroff.setFont(f);
	onOroff.setBackground(Color.RED);
	start = new JButton("Start");
	window1.add(start);
	start.reshape(25, 100, 135, 25);
	stopReset = new JButton("Stop/Reset");
	window1.add(stopReset);
	stopReset.reshape(380, 100, 135, 25);
	inclineUp = new JButton("Increase Incline");
	window1.add(inclineUp);
	inclineUp.reshape(25, 150, 135, 25);
	inclineDown = new JButton("Decrease Incline");
	window1.add(inclineDown);
	inclineDown.reshape(25, 200, 135, 25);
	speedUp = new JButton("Speed Up");
	window1.add(speedUp);
	speedUp.reshape(380, 150, 135, 25);
	speedDown = new JButton("Slow Down");
	window1.add(speedDown);
	speedDown.reshape(380, 200, 135, 25);
	
	//QUICK SPEED BUTTONS
	quickSpeed3 = new JButton("3");
	window1.add(quickSpeed3);
	quickSpeed3.reshape(40, 260, 100, 25);
	quickSpeed4 = new JButton("4");
	window1.add(quickSpeed4);
	quickSpeed4.reshape(165, 260, 100, 25);
	quickSpeed6 = new JButton("6");
	window1.add(quickSpeed6);
	quickSpeed6.reshape(290, 260, 100, 25);
	quickSpeed7 = new JButton("7");
	window1.add(quickSpeed7);
	quickSpeed7.reshape(415, 260, 100, 25);
	
	//QUICK INCLINE BUTTONS
	quickIncline1 = new JButton("1");
	window1.add(quickIncline1);
	quickIncline1.reshape(40, 310, 100, 25);
	quickIncline2 = new JButton("2");
	window1.add(quickIncline2);
	quickIncline2.reshape(165, 310, 100, 25);
	quickIncline4 = new JButton("4");
	window1.add(quickIncline4);
	quickIncline4.reshape(290, 310, 100, 25);
	quickIncline6 = new JButton("6");
	window1.add(quickIncline6);
	quickIncline6.reshape(415, 310, 100, 25);
	
	//BUTTON HANDLER
	bhandle = new ButtonHandler();
	clock1 = new Clock();
	stopwatch = new Timer[3];
	for (int ct = 0; ct < 3; ct ++)
		stopwatch[ct] = new Timer();
	timer1 = new javax.swing.Timer(1000, clock1);
	onOffControl = 0;
	displayDistance = new DistanceTimer();
	displayDistanceTimer = new javax.swing.Timer(1000, displayDistance);
	
	//ADD ACTION LISTENER
	onOroff.addActionListener(bhandle);
	start.addActionListener(bhandle);
	start.setEnabled(false);
	stopReset.addActionListener(bhandle);
	stopReset.setEnabled(false);
	inclineUp.addActionListener(bhandle);
	inclineUp.setEnabled(false);
	inclineDown.addActionListener(bhandle);
	inclineDown.setEnabled(false);
	speedUp.addActionListener(bhandle);
	speedUp.setEnabled(false);
	speedDown.addActionListener(bhandle);
	speedDown.setEnabled(false);
	quickIncline1.addActionListener(bhandle);
	quickIncline1.setEnabled(false);
	quickIncline2.addActionListener(bhandle);
	quickIncline2.setEnabled(false);
	quickIncline4.addActionListener(bhandle);
	quickIncline4.setEnabled(false);
	quickIncline6.addActionListener(bhandle);
	quickIncline6.setEnabled(false);
	quickSpeed3.addActionListener(bhandle);
	quickSpeed3.setEnabled(false);
	quickSpeed4.addActionListener(bhandle);
	quickSpeed4.setEnabled(false);
	quickSpeed6.addActionListener(bhandle);
	quickSpeed6.setEnabled(false);
	quickSpeed7.addActionListener(bhandle);
	quickSpeed7.setEnabled(false);
	
	//SET TEXT FIELDS TO UNEDITABLE
	speedTF.setEditable(false);
	inclineTF.setEditable(false);
	distanceTF.setEditable(false);
	paceTF.setEditable(false);
	timeTF.setEditable(false);
	
	//SET WINDOW SIZE
	setSize(610, 450);
	show();
	
	//SHOW WINDOW 2 ON TOP
	w2 = new Window2();
}
	
public static void main(String args[])
	{
		Treadmill tread1 = new Treadmill();
		tread1.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
						{System.exit(0);}
				});
	}

private class Clock implements ActionListener 
{
    public void actionPerformed(ActionEvent d) 
    {	
    	timeTF.setText(stopwatch[0].formatTime());
    	timeTF.setHorizontalAlignment(JTextField.CENTER);
    }	
}

private class DistanceTimer implements ActionListener
{
	public void actionPerformed(ActionEvent a)
	{
		runTracker1.calculateDistance((int)stopwatch[0].getElapsedTime());
		distanceTF.setText(runTracker1.getDistance());
		distanceTF.setHorizontalAlignment(JTextField.CENTER);
	}
}

private class ButtonHandler implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == onOroff)
		{	
			if (onOffControl == 0)
			{
				start.setEnabled(true);
				treadmill1 = new TreadmillModel2();
				treadmill1.setTreadmillOn();
				onOroff.setBackground(Color.GREEN);
				
				//SET WEIGHT AND GENDER
				treadmill1.setWeight(w2.weightAmount);
				treadmill1.setGender(w2.genderNum);
				
				onOffControl = 1;
			}
			else if (onOffControl == 1)
			{
				start.setEnabled(false);
				stopReset.setEnabled(false);
				onOroff.setBackground(Color.RED);
				speedTF.setText(null);
				inclineTF.setText(null);
				timeTF.setText(null);
				distanceTF.setText(null);
				paceTF.setText(null);
				onOffControl = 0;
			}
		}
		else if(e.getSource() == start)
		{			
			//BEGINNING SPEED
			treadmill1.setSpeed(.5);
			treadmill1.setIncline(0);
			
			//SET TIME TO 0
			stopwatch[0].resetTime();
			
			//OUTPUT TO SCREEN
			paceTF.setText(treadmill1.getFormattedPace());
			paceTF.setHorizontalAlignment(JTextField.CENTER);
			speedTF.setText(Double.toString(treadmill1.getSpeed()));
			speedTF.setHorizontalAlignment(JTextField.CENTER);
			inclineTF.setText(Double.toString(treadmill1.getIncline()));
			inclineTF.setHorizontalAlignment(JTextField.CENTER);
			
			//RUNTRACKER
			runTracker1 = new RunTracker();
			runTracker1.setSpeed(.5);
			
			//ENABLE BUTTONS
			stopReset.setEnabled(true);
			inclineUp.setEnabled(true);
			inclineDown.setEnabled(true);
			speedUp.setEnabled(true);
			speedDown.setEnabled(true);
			quickIncline1.setEnabled(true);
			quickIncline2.setEnabled(true);
			quickIncline4.setEnabled(true);
			quickIncline6.setEnabled(true);
			quickSpeed3.setEnabled(true);
			quickSpeed4.setEnabled(true);
			quickSpeed6.setEnabled(true);
			quickSpeed7.setEnabled(true);
			start.setEnabled(false);
			onOroff.setEnabled(false);
			
			//TIMER START
			stopwatch[0].start();
			timer1.start();
			displayDistanceTimer.start();
		}
		else if(e.getSource() == stopReset)
		{
			inclineUp.setEnabled(false);
			inclineDown.setEnabled(false);
			speedUp.setEnabled(false);
			speedDown.setEnabled(false);
			quickIncline1.setEnabled(false);
			quickIncline2.setEnabled(false);
			quickIncline4.setEnabled(false);
			quickIncline6.setEnabled(false);
			quickSpeed3.setEnabled(false);
			quickSpeed4.setEnabled(false);
			quickSpeed6.setEnabled(false);
			quickSpeed7.setEnabled(false);
			start.setEnabled(true);
			onOroff.setEnabled(true);
			
			//TIMER STOP
			stopwatch[0].stop();
			timer1.stop();
			displayDistanceTimer.stop();
			treadmill1.setCaloriesBurned(Double.parseDouble(runTracker1.getDistance()));
			
			w3 = new Window3();
			w3.displayValues(runTracker1.getDistance(),
					stopwatch[0].formatTime(), treadmill1.getCalories());
			
			stopReset.setEnabled(false);
		}
		else if(e.getSource() == inclineUp)
		{
			//SET INCLINE MAX 9
			if (treadmill1.getIncline() == 9)
			{treadmill1.setIncline(9);}
			else 
			{	
				treadmill1.increaseIncline();
				inclineTF.setText(Double.toString(treadmill1.getIncline()));
			}
		}
		else if(e.getSource() == inclineDown)
		{
			//SET INCLINE MIN 0
			if (treadmill1.getIncline() == 0)
			{treadmill1.setIncline(0);}
			else 
			{
				treadmill1.decreaseIncline();
				inclineTF.setText(Double.toString(treadmill1.getIncline()));
			}
		}
		else if(e.getSource() == speedUp)
		{
			//SET SPEED MAX 9
			if (treadmill1.getSpeed() == 10)
			{
				treadmill1.setSpeed(9);
				runTracker1.setSpeed(9);
			}
			else if (treadmill1.getSpeed() < 9.9)
			{
				treadmill1.increaseSpeed();
				runTracker1.increaseSpeed();
				speedTF.setText(treadmill1.getFormattedSpeed());
			}
			
			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		else if(e.getSource() == speedDown)
		{
			//SET SPEED MIN 0
			if (treadmill1.getSpeed() == 0)
			{
				treadmill1.setSpeed(0);
				runTracker1.setSpeed(0);
			}
			else if (treadmill1.getSpeed() > 0.1)
			{
				treadmill1.decreaseSpeed();
				runTracker1.decreaseSpeed();
				speedTF.setText(treadmill1.getFormattedSpeed());
			}
			
			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		
		//QUICK SPEED BUTTONS
		else if(e.getSource() == quickSpeed3)
		{
			//SET SPEED
			treadmill1.setSpeed(3.0);
			runTracker1.setSpeed(3.0);
			speedTF.setText(treadmill1.getFormattedSpeed());

			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		else if(e.getSource() == quickSpeed4)
		{
			//SET SPEED
			treadmill1.setSpeed(4.0);
			runTracker1.setSpeed(4.0);
			speedTF.setText(treadmill1.getFormattedSpeed());

			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		else if(e.getSource() == quickSpeed6)
		{
			//SET SPEED
			treadmill1.setSpeed(6.0);
			runTracker1.setSpeed(6.0);
			speedTF.setText(treadmill1.getFormattedSpeed());

			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		else if(e.getSource() == quickSpeed7)
		{
			//SET SPEED	
			treadmill1.setSpeed(7.0);
			runTracker1.setSpeed(7.0);
			speedTF.setText(treadmill1.getFormattedSpeed());

			//SET PACE
			paceTF.setText(treadmill1.getFormattedPace());
		}
		
		//QUICK INCLINE OPTIONS
		else if(e.getSource() == quickIncline1)
		{
			treadmill1.setIncline(1.0);
			inclineTF.setText(Double.toString(treadmill1.getIncline()));
		}
		else if(e.getSource() == quickIncline2)
		{
			treadmill1.setIncline(2.0);
			inclineTF.setText(Double.toString(treadmill1.getIncline()));
		}
		else if(e.getSource() == quickIncline4)
		{
			treadmill1.setIncline(4.0);
			inclineTF.setText(Double.toString(treadmill1.getIncline()));
		}
		else if(e.getSource() == quickIncline6)
		{
			treadmill1.setIncline(6.0);
			inclineTF.setText(Double.toString(treadmill1.getIncline()));
		}
		
	}
	
}

}

class Window2 extends JFrame
{
	private JLabel weight;
	private JTextField enterWeight;
	private JButton enter;
	private JComboBox gender;
	private String genderArray[] = {"male", "female"};
	int weightAmount, genderNum;
	ButtonHandler bhandle;
	
	public Window2()
	{
		super();
		Container window2 = getContentPane();
		window2.setLayout(null);
		
		weight = new JLabel("Enter Your Weight");
		window2.add(weight);
		weight.setHorizontalAlignment(JLabel.CENTER);
		weight.reshape(25, 25, 125, 25);
		
		enterWeight = new JTextField(15);
		window2.add(enterWeight);
		enterWeight.setHorizontalAlignment(JTextField.LEFT);
		enterWeight.reshape(25, 50, 125, 25);
		enterWeight.setText("0");
		
		gender = new JComboBox(genderArray);
		window2.add(gender);
		gender.reshape(25, 75, 125, 25);
		
		enter = new JButton("Enter");
		window2.add(enter);
		enter.reshape(25, 125, 125, 25);
		
		bhandle = new ButtonHandler();
		
		enter.addActionListener(bhandle);
		
		setSize(200, 225);
		show();
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent u)
		{
			if (u.getSource() == enter)
			{
				if (Integer.parseInt(enterWeight.getText()) > 0)
					weightAmount = Integer.parseInt(enterWeight.getText());
				else
					weightAmount = 150;
				if ((String) gender.getSelectedItem() == "Male")
				{genderNum = 1;}
				else if ((String) gender.getSelectedItem() == "Female")
				{genderNum = 0;}
				
				hide();
			}
		}
	}
	
}

class Window3 extends JFrame
{
	private JLabel time, distance, caloriesBurned, printLabel;
	private JTextField timeTF, distanceTF, caloriesTF;
	private JRadioButton yes, no;
	private JButton enter;
	ButtonGroup yesNo;
	ButtonHandler bhandle;
	private String timeString, distanceString, calories;
	
	public Window3()
	{
		super();
		Container window3 = getContentPane();
		window3.setLayout(null);
		
		//LABELS
		distance = new JLabel("Distance: ");
		window3.add(distance);
		distance.reshape(25, 25, 100, 25);
		time = new JLabel("Time: ");
		window3.add(time);
		time.reshape(25, 50, 100, 25);
		caloriesBurned = new JLabel("Calories Burned: ");
		window3.add(caloriesBurned);
		caloriesBurned.reshape(25, 75, 100, 25);
		printLabel = new JLabel("Create Results Document?");
		window3.add(printLabel);
		printLabel.reshape(25, 125, 200, 25);
		
		//TEXT FIELDS
		timeTF = new JTextField(15);
		window3.add(timeTF);
		timeTF.reshape(125, 25, 100, 25);
		timeTF.setEditable(false);
		distanceTF = new JTextField(15);
		window3.add(distanceTF);
		distanceTF.reshape(125, 50, 100, 25);
		distanceTF.setEditable(false);
		caloriesTF = new JTextField(15);
		window3.add(caloriesTF);
		caloriesTF.reshape(125, 75, 100, 25);
		caloriesTF.setEditable(false);
		
		//RADIO BUTONS
		yesNo = new ButtonGroup();
		yes = new JRadioButton("Yes");
		no = new JRadioButton("No");
		yesNo.add(yes);
		yesNo.add(no);
		window3.add(yes);
		yes.reshape(25, 150, 100, 25);
		window3.add(no);
		no.reshape(25, 175, 100, 25);
		
		//BUTTON
		enter = new JButton("Enter");
		window3.add(enter);
		enter.reshape(25, 200, 100, 25);
		
		//BUTTON HANDLER
		bhandle = new ButtonHandler();
		enter.addActionListener(bhandle);
		
		setSize(400, 400);
		show();
	}
	
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent u)
		{
			if (u.getSource() == enter)
			{
				if (no.isSelected())
				{
					hide();
				}
				else if (yes.isSelected());
				{	
					File treadmillOutputFile;
					FileOutputStream fileout;
					DataOutputStream fileOutput = null;
					
					//CREAT OUTPUTFILE
					try
					{
						treadmillOutputFile = new File("TreadmillOutput.data");
						fileOutput = new DataOutputStream(new FileOutputStream(treadmillOutputFile));
					}
					catch (IOException l)
					{
						System.out.println("Could not create new output file");
					}
					//WRITE TO OUTPUT FILE
					try
					{
						fileOutput.writeUTF("Distance	Time	Calories\n");
						fileOutput.writeUTF(timeString + "		" + distanceString + "	" + calories);
					}
					catch (IOException k)
					{
						System.out.println("Cannot write to output file");
					}
					hide();
				}
			}
		}
	}
	public void displayValues(String time, String distance, int calories)
	{
		timeString = time;
		distanceString = distance;
		this.calories = Integer.toString(calories);
		timeTF.setText(time);
		distanceTF.setText(distance);
		caloriesTF.setText(Integer.toString(calories));
	}
}

class TreadmillModel1
{
	protected boolean treadmillOn;
	protected boolean start;
	protected boolean reset;
	protected double speed;
	
	public TreadmillModel1()
	{
		speed = 0.0;
		treadmillOn = false;
		start = false;
		reset = false;
	}
	public void increaseSpeed()
	{speed += .1;}
	public void decreaseSpeed()
	{speed -= .1;}
	public void setTreadmillOn()
	{treadmillOn = true;}
	public void setTreadmillOff()
	{treadmillOn = false;}
	public void setStart()
	{start = true;}
	public void setResetOn()
	{reset = true;}
	public void setResetOff()
	{reset = false;}	
	public void setSpeed(double MPH)
	{speed = MPH;}
	public double getSpeed()
	{return speed;}
	public String getFormattedSpeed()
	{
		double speed1 = speed;
		NumberFormat format1 = NumberFormat.getNumberInstance();
		format1.setMaximumFractionDigits(1);
		format1.setMinimumFractionDigits(1);
		String formattedSpeed = format1.format(speed1);
		return formattedSpeed;
	}
	
}

class TreadmillModel2 extends TreadmillModel1
{
	private double incline;
	private double pace;
	private int weight;
	private int calories;
	private int gender;
	
	public TreadmillModel2()
	{
		super();
		incline = 0.0;
		pace = 0.0;
		calories = 0;
	}
	
	public void increaseIncline()
	{incline += .5;}
	public void decreaseIncline()
	{incline -= .5;}
	public void setIncline(double percentIncline)
	{incline = percentIncline;}
	public double getIncline()
	{return incline;}
	public void setWeight(int weight)
	{this.weight = weight;}
	public int getCalories()
	{return calories;}
	public String getFormattedPace()
	{
		double pace = 60/speed;
		NumberFormat format1 = NumberFormat.getNumberInstance();
		format1.setMaximumFractionDigits(2);
		format1.setMinimumFractionDigits(2);
		String pace2 = format1.format(pace);
		return pace2;
	}
	public void setCaloriesBurned(double distance)
	{
		if (gender == 1)
			calories = (int) (.79 * weight * distance);
		else if (gender == 0)
			calories = (int) (.79 * weight * distance * .97);
	}
	public void setGender(int gender)
	{
		if (gender == 0)
			this.gender = 0;
		else if (gender == 1)
			this.gender = 1;
	}
}

class Timer
{
	private boolean isRunning;
	private long startTime;
	private long elapsedTime;
	private long stopTime;
	private long sec, min;
	private String formattedTime;
	
	//CONSTRUCTOR
	public Timer()
	{
		isRunning = false;
		startTime = 0;
		elapsedTime = 0;
		stopTime = 0;
		sec = 0;
		min = 0;
		formattedTime = "";
	}
	
	public void start()
	{
		if (isRunning == false)
		{
			startTime = System.currentTimeMillis();
			isRunning = true;
		}
	}
	public void stop()
	{
		stopTime = System.currentTimeMillis();
		isRunning = false;
	}
	public long getElapsedTime()
	{
		if (isRunning)
		{
			elapsedTime = ((System.currentTimeMillis() - startTime) / 1000);
		}
		else
		{
			elapsedTime = ((stopTime - startTime) / 1000);
		}
		return elapsedTime;
	}
	public boolean getIsRunning()
	{return isRunning;}
	
	public String formatTime()
	{
		sec ++;
		if (sec == 60)
		{
			min ++;
			sec = 0;
		}
		
		if (sec >= 10)
			formattedTime = Long.toString(min) + ":" + Long.toString(sec);
		else if (sec < 10)
			formattedTime = Long.toString(min) + ":" + "0" + Long.toString(sec);
		return formattedTime;
	}
	public void resetTime()
	{
		elapsedTime = 0;
		startTime = 0;
		stopTime = 0;
		min = 0;
		sec = 0;
	}
}

class RunTracker
{
	private double speed, pace, distance;

	//CONSTRUCTOR
	public RunTracker()
	{
		speed = 0.0;
		pace = 0.0;
		distance = 0.0;
	}
	public double getPace()
	{return pace;}
	public double getSpeed()
	{return speed;}
	public void setSpeed(double speed)
	{
		this.speed = speed;
		setPace();
	}
	public void increaseSpeed()
	{
		speed += .1;
		setPace();
	}
	public void decreaseSpeed()
	{
		speed -= .1;
		setPace();
	}

	public void setPace()
	{
		double pace = 60/speed;
		NumberFormat format1 = NumberFormat.getNumberInstance();
		format1.setMaximumFractionDigits(2);
		format1.setMinimumFractionDigits(2);
		String pace2 = format1.format(pace);
		double pace3 = Double.parseDouble(pace2);
		this.pace = pace3;
	}
	public void calculateDistance(int endTime)
	{
		distance += 1 / (pace * 60);
	}
	public String getDistance()
	{
		NumberFormat formatDistance = NumberFormat.getNumberInstance();
		formatDistance.setMaximumFractionDigits(2);
		formatDistance.setMinimumFractionDigits(2);
		String finalDistance = formatDistance.format(distance);
		return finalDistance;
	}
	public void resetDistance()
	{
		distance = 0;
	}
}


