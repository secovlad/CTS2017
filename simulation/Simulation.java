package seco.cts.main.simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

import seco.cts.main.driver.Trucker;

/**
 * This whole thing is very very bug ridden but at least it kind of does
 * something!
 * 
 * As of Februrary this no longer applies, program's testing has usually been alright :D But you don't know ruthless testing
 * anyways so fuck knows how solid it actually is.
 * 
 * In all fairness the code probably sucks but after all you're only learning.
 * The important part now is that it works!
 * 
 * @author secov
 */
public class Simulation {

	private static final int TIME_UNIT = 1; // one hour; to calculate in km/h
	ArrayList<Trucker> truckerList = new ArrayList<Trucker>();
	public static int currentUnit = 0;
	Random rand = new Random(); // for various random needs

	String consoleOutput = ""; // this will populate itself with whatever is
	// being printed by the console;
	// it is for GUI purposes
	// use simulation.returnConsoleOutput() IMMEDIATELY AFTER running timePasses
	// to get the string.

	/**
	 * Blank constructor for the Simulation gets you just what you need! ;)
	 */
	public Simulation() {

		Trucker seco = new Trucker("Seco", 18, "Volvo");
		Trucker cosmin = new Trucker("Cosmin", 19, "Scania");
		Trucker vlad = new Trucker("Vlad", 19, "MAN");
		Trucker radu = new Trucker("Radu", 16, "MercedesBenz");
		Trucker plai = new Trucker("Plai", 23, "Renault");
		Trucker luigi = new Trucker("Luigi", 19, "MAN");

		truckerList.add(seco);
		truckerList.add(cosmin);
		truckerList.add(vlad);
		truckerList.add(radu);
		truckerList.add(plai);
		truckerList.add(luigi);

		// seco.getCurrentJob().printDistanceBoard();
	}

	public void go() throws Exception {
		timePasses(168); // one week - 168 hours
	}

	public void timePasses(int timeToPass) throws Exception {
		for (int i = 1; i <= timeToPass; i++) {
			timePasses();
		}
	}

	/**
	 * The main simulation method.
	 * 
	 * @throws Exception
	 */
	public void timePasses() throws Exception {

		String strToAdd = "";
		consoleOutput = "";

		currentUnit++; /* t i m e p a s s e d */

		strToAdd = "\n=====SIMULATION UNIT " + currentUnit + "=====";
		printOutput(strToAdd);

		for (Trucker trucker : truckerList) {

			trucker.timePasses(); // sleep meter

			boolean jobComplete = false; 
			// this exists to make a sure a trucker
			// won't go to sleep while he
			// completes a job (eh)

			double distanceTravelled = 0, distanceLeft = trucker.getDistanceLeftToDrive();

			if (trucker.getRestingHours() > 0) {
				// STATE ONE - SLEEPING / RESTING / REFUELING
				strToAdd = trucker.getName() + " is currently resting, will be heading back out in "
						+ trucker.getRestingHours() + " hours.";
				printOutput(strToAdd);
			} else {

				double randomFactor = rand.nextDouble() + 1.0;
				distanceTravelled = trucker.getTruck().getMediumSpeed() / TIME_UNIT
						* (trucker.getSkillFactor() + randomFactor - 1.0); // genius
				// formula

				if (trucker.getDistanceLeftToDrive() - distanceTravelled < 0) {
					// STATE FOUR - JOB DONE
					distanceTravelled = trucker.getDistanceLeftToDrive(); // so they don't drive more than they actually do
					
					trucker.getTruck().setMileage(trucker.getTruck().getMileage() + distanceTravelled);
					strToAdd = trucker.getName() + " has travelled " + String.format("%.0f", distanceTravelled)
					+ " km and has finished their job!:\n" + trucker.getCurrentJob()
					+ "\nMoney added to account.";
					printOutput(strToAdd);
					trucker.addMoney(trucker.getCurrentJob().getPrizeMoney());
					strToAdd = trucker.getName() + " will now rest in " + trucker.getCurrentJob().getDestinationCity()
							+ " for 3 hours before heading out again!";
					printOutput(strToAdd);
					jobComplete = true;
					trucker.jobCompleted();
					trucker.restWithoutEnergyGains(2);
				} else {
					distanceLeft = trucker.getDistanceLeftToDrive() - distanceTravelled;
				}

				if (trucker.getEnergyMeter() <= 30 && jobComplete == false) {
					// STATE TWO - GOING TO SLEEP
					trucker.sleepBreak();
					strToAdd = trucker.getName() + " will be going to sleep for " + trucker.getRestingHours()
					+ " hours.";
					printOutput(strToAdd);
				} else if (jobComplete == false) {
					trucker.getTruck().setMileage(trucker.getTruck().getMileage() + distanceTravelled);

					for (int i = 1; i <= distanceTravelled; i++)
						trucker.getTruck().timePasses(); // trucks' stuff
					// decrease by the
					// kilometer

					if (distanceLeft < 0) {

					} else {
						// STATE THREE - DRIVING
						strToAdd = trucker.getName() + " travelled " + (int) distanceTravelled + " km this hour.";
						printOutput(strToAdd);
					}
				}
			}

			if (distanceLeft > 0 && trucker.getRestingHours() == 0) {
				// ALSO STATE THREE - DRIVING
				trucker.setDistanceLeftToDrive(distanceLeft);
			}

			trucker.addAmountDriven(distanceTravelled); // add the amount driven
			// to the list

		}

		/*
		 * legacy feature from the console output days if (currentUnit % 10 ==
		 * 0){ printTruckers(); }
		 */

		if (currentUnit == 168) {
			System.out.println("An entire week has passed! Here are the final stats: ");
			consoleOutput += "An entire week has passed! Here are the final stats: \n";
			printTruckers();
			for (Trucker trucker : truckerList) {
				consoleOutput += trucker;
			}
			endGame();
		}
	}

	public void printTruckers() {
		for (Trucker trucker : truckerList) {
			System.out.println(trucker);
		}
	}

	public void endGame() {
		System.out.println("The final standing!");
		HashMap<String, Integer> moneyAtTheEnd = new HashMap<String, Integer>();
		ArrayList<Integer> clasament = new ArrayList<Integer>();
		for (Trucker trucker : truckerList) {
			moneyAtTheEnd.put(trucker.getName(), (int) trucker.getMoney());
		}
		Collections.sort(clasament);
	}

	public ArrayList<Trucker> getTruckerList() {
		return truckerList;
	}

	public String getConsoleOutput() {
		return consoleOutput;
	}

	/**
	 * Adds a string to both the console output to be returned to the UI and the
	 * actual console.
	 * 
	 * @param string
	 *            The string that we want added to both things.
	 */
	private void printOutput(String string) {
		// combating code duplication!! cheers pragmatic programmers.
		System.out.println(string);
		consoleOutput += string += "\n";
	}
}
