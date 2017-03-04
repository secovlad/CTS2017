package seco.cts.main.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

import javax.swing.*;

import seco.cts.main.simulation.Simulation;
import seco.cts.main.driver.*;

/**
 * The first UI you ever made that ever works, congratulations. 
 * 
 * @author Vlad Secosan
 *
 */
public class CTSViewer {

	private JFrame frame;
	private Simulation simulation;
	private JTextArea textArea;
	private JTextArea numberOfTurns;

	private HashMap<String, Container> truckerContainerMap;
	
	private Font mainFont = new Font("Arial", Font.PLAIN, 20);

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused") // we actually only need its constructor
		CTSViewer test = new CTSViewer();
	}

	public CTSViewer() throws Exception {
		initSimulation();
		truckerContainerMap = new HashMap<String, Container>();
		makeFrame();
	}

	private void initSimulation() throws Exception {
		simulation = new Simulation();
		simulation.printTruckers();
	}

	private void makeFrame() {
		frame = new JFrame("Crappy Truck Sim 2017");
		makeMenuBar(frame);

		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new BorderLayout());

		JPanel truckerCollection = new JPanel();
		truckerCollection.setLayout(new GridLayout(1, simulation.getTruckerList().size()));

		for (Trucker trucker : simulation.getTruckerList()) {
			JPanel truckerPanel = addNewTruckerPanel(trucker);
			truckerCollection.add(truckerPanel);
			truckerContainerMap.put(trucker.getName(), truckerPanel);
		}

		contentPane.add(truckerCollection, BorderLayout.NORTH);

		JButton timePassButton = new JButton("Time Passes");
		timePassButton.setFont(mainFont);
		timePassButton.addActionListener(new TimePassButtonListener());

		JPanel timePassButtonPanel = new JPanel();
		timePassButtonPanel.setLayout(new BorderLayout());
		numberOfTurns = new JTextArea();
		numberOfTurns.setEditable(true);
		numberOfTurns.setFont(mainFont);
		timePassButtonPanel.add(timePassButton, BorderLayout.NORTH);
		timePassButtonPanel.add(numberOfTurns, BorderLayout.SOUTH);
		contentPane.add(timePassButtonPanel, BorderLayout.EAST);

		// investigate why scrollpane is not working
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setFont(mainFont);
		// textArea.setPreferredSize(new Dimension(600, 200));
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFont(mainFont);
		contentPane.add(scrollPane, BorderLayout.CENTER);

		frame.setSize(2100, 1200);
		frame.setVisible(true);
	}

	private void makeMenuBar(JFrame frame) {
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// create the File menu
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		JMenuItem newItem = new JMenuItem("New");
		newItem.addActionListener(new NewActionListener());
		fileMenu.add(newItem);

		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new QuitActionListener());
		fileMenu.add(quitItem);
	}

	/**
	 * Edit - you just add them all to the same pane it seems.
	 */
	private JPanel addNewTruckerPanel(Trucker trucker) {

		JPanel truckerPanel = new JPanel();

		JLabel header = new JLabel("=====TRUCKER=====");
		
		JLabel row1 = new JLabel(trucker.getName() + " | Age: " + trucker.getAge());
		JLabel row2 = new JLabel("Energy: " + trucker.getEnergyMeter() + " / 100");
		JLabel row3 = new JLabel("Skill factor: " + String.format("%.2f", trucker.getSkillFactor()));
		JLabel row4 = new JLabel(
				"Current truck: " + trucker.getTruck().getMakerName() + " " + trucker.getTruck().getModelName());
		JLabel row5 = new JLabel("Account balance: " + String.format("%.2f", trucker.getMoney())+ "€");
		JLabel row6 = new JLabel("Distance left to drive: " + String.format("%.2f", trucker.getDistanceLeftToDrive())+"km");
		JLabel jobHeader = new JLabel("======JOB=======");
		JLabel row7 = new JLabel("Starting City: " + trucker.getCurrentJob().getStartingCity());
		JLabel row8 = new JLabel("Destination City: " + trucker.getCurrentJob().getDestinationCity());
		JLabel row9 = new JLabel("Cargo: " + trucker.getCurrentJob().getCargo());
		JLabel row10 = new JLabel("Distance: " + trucker.getCurrentJob().getDestinationDistance()+ "km");
		JLabel row11 = new JLabel("Prize Money: " + trucker.getCurrentJob().getPrizeMoney()+ "€");
		JLabel truckHeader = new JLabel("======TRUCK======");
		JLabel row12 = new JLabel("Model Name: " + trucker.getTruck().getMakerName());
		JLabel row13 = new JLabel("Maker Name: " + trucker.getTruck().getModelName());
		JLabel row14 = new JLabel("Mileage: " + String.format("%.2f", trucker.getTruck().getMileage())+"km");
		JLabel row15 = new JLabel("Medium Speed: " + trucker.getTruck().getMediumSpeed()+"km/h");
		JLabel row16 = new JLabel("Fuel Level: " + String.format("%.2f", trucker.getTruck().getFuelValue())+" litres");
		
		header.setFont(mainFont);
		row1.setFont(mainFont);
		row2.setFont(mainFont);
		row3.setFont(mainFont);
		row4.setFont(mainFont);
		row5.setFont(mainFont);
		row6.setFont(mainFont);
		jobHeader.setFont(mainFont);
		row7.setFont(mainFont);
		row8.setFont(mainFont);
		row9.setFont(mainFont);
		row10.setFont(mainFont);
		row11.setFont(mainFont);
		truckHeader.setFont(mainFont);
		row12.setFont(mainFont);
		row13.setFont(mainFont);
		row14.setFont(mainFont);
		row15.setFont(mainFont);
		row16.setFont(mainFont);
		
		truckerPanel.setLayout(new GridLayout(19, 1));

		truckerPanel.add(header);
		truckerPanel.add(row1);
		truckerPanel.add(row2);
		truckerPanel.add(row3);
		truckerPanel.add(row4);
		truckerPanel.add(row5);
		truckerPanel.add(row6);
		truckerPanel.add(jobHeader);
		truckerPanel.add(row7);
		truckerPanel.add(row8);
		truckerPanel.add(row9);
		truckerPanel.add(row10);
		truckerPanel.add(row11);
		truckerPanel.add(truckHeader);
		truckerPanel.add(row12);
		truckerPanel.add(row13);
		truckerPanel.add(row14);
		truckerPanel.add(row15);
		truckerPanel.add(row16);

		return truckerPanel;
	}

	/**
	 * Really bad code duplication but it works!
	 * 
	 * @param trucker
	 */
	private void updateTruckerPanel(Trucker trucker) {

		Container truckerPanel = truckerContainerMap.get(trucker.getName());

		for (Component component : truckerPanel.getComponents()) {
			truckerPanel.remove(component);
		}

		String restedStr = "";
		if (trucker.getRestingHours() > 0) {
			restedStr += "[RESTING: " + trucker.getRestingHours() + "h left]";
		}

		JLabel header;
		if (restedStr.equals("")) {
			header = new JLabel("=====TRUCKER=====");
		} else {
			header = new JLabel("=====TRUCKER " + restedStr);
		}

		JLabel row1 = new JLabel(trucker.getName() + " | Age: " + trucker.getAge());
		JLabel row2 = new JLabel("Energy: " + trucker.getEnergyMeter() + " / 100");
		JLabel row3 = new JLabel("Skill factor: " + String.format("%.2f", trucker.getSkillFactor()));
		JLabel row4 = new JLabel(
				"Current truck: " + trucker.getTruck().getMakerName() + " " + trucker.getTruck().getModelName());
		JLabel row5 = new JLabel("Account balance: " + String.format("%.2f", trucker.getMoney())+ "€");
		JLabel row6 = new JLabel("Distance left to drive: " + String.format("%.2f", trucker.getDistanceLeftToDrive())+"km");
		JLabel jobHeader = new JLabel("======JOB=======");
		JLabel row7 = new JLabel("Starting City: " + trucker.getCurrentJob().getStartingCity());
		JLabel row8 = new JLabel("Destination City: " + trucker.getCurrentJob().getDestinationCity());
		JLabel row9 = new JLabel("Cargo: " + trucker.getCurrentJob().getCargo());
		JLabel row10 = new JLabel("Distance: " + trucker.getCurrentJob().getDestinationDistance()+ "km");
		JLabel row11 = new JLabel("Prize Money: " + trucker.getCurrentJob().getPrizeMoney()+ "€");
		JLabel truckHeader = new JLabel("======TRUCK======");
		JLabel row12 = new JLabel("Model Name: " + trucker.getTruck().getMakerName());
		JLabel row13 = new JLabel("Maker Name: " + trucker.getTruck().getModelName());
		JLabel row14 = new JLabel("Mileage: " + String.format("%.2f", trucker.getTruck().getMileage())+"km");
		JLabel row15 = new JLabel("Medium Speed: " + trucker.getTruck().getMediumSpeed()+"km/h");
		JLabel row16 = new JLabel("Fuel Level: " + String.format("%.2f", trucker.getTruck().getFuelValue())+" litres");
		
		header.setFont(mainFont);
		row1.setFont(mainFont);
		row2.setFont(mainFont);
		row3.setFont(mainFont);
		row4.setFont(mainFont);
		row5.setFont(mainFont);
		row6.setFont(mainFont);
		jobHeader.setFont(mainFont);
		row7.setFont(mainFont);
		row8.setFont(mainFont);
		row9.setFont(mainFont);
		row10.setFont(mainFont);
		row11.setFont(mainFont);
		truckHeader.setFont(mainFont);
		row12.setFont(mainFont);
		row13.setFont(mainFont);
		row14.setFont(mainFont);
		row15.setFont(mainFont);
		row16.setFont(mainFont);

		truckerPanel.add(header);
		truckerPanel.add(row1);
		truckerPanel.add(row2);
		truckerPanel.add(row3);
		truckerPanel.add(row4);
		truckerPanel.add(row5);
		truckerPanel.add(row6);
		truckerPanel.add(jobHeader);
		truckerPanel.add(row7);
		truckerPanel.add(row8);
		truckerPanel.add(row9);
		truckerPanel.add(row10);
		truckerPanel.add(row11);
		truckerPanel.add(truckHeader);
		truckerPanel.add(row12);
		truckerPanel.add(row13);
		truckerPanel.add(row14);
		truckerPanel.add(row15);
		truckerPanel.add(row16);
	}

	class NewActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			newSim();
		}
	}

	class QuitActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			quit();
		}
	}

	class TimePassButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			try {
				if (numberOfTurns.getText().equals("")) simulation.timePasses();
				else simulation.timePasses(Integer.parseInt(numberOfTurns.getText()));
				textArea.append(simulation.getConsoleOutput());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (Trucker trucker : simulation.getTruckerList()) {
				updateTruckerPanel(trucker);
			}
			frame.setVisible(true);
		}
	}

	/* Methods for the menu bar */
	private void newSim() {
		simulation = new Simulation();
		for (Trucker trucker : simulation.getTruckerList()) {
			updateTruckerPanel(trucker);
		}
		frame.pack();
		frame.setVisible(true);
		// clear everything ()
	}

	private void quit() {

	}

}
