package seco.cts.main.driver;

import java.util.ArrayList;
import java.util.Random;

import seco.cts.main.simulation.Simulation;
import seco.cts.main.truck.*;

/**
 * The class representing a driver. They have a truck which they drive (which is
 * determined on their random or given truckPreference) and a couple of other
 * variables.
 * 
 * @author Vlad Secosan
 *
 */
public class Trucker {

	private String name;
	private int age;
	private float skillFactor;
	private int energyMeter = 100; // each trucker starts fully rested
	private double money = 0; // each trucker starts from the bottom
	private String truckPreference;
	private Truck drivenTruck;
	private Job currentJob;
	private double distanceLeftToDrive;
	private int restingHours = 0; // if it's not 0, it means the trucker is not
	// driving for that number of hours
	private ArrayList<String> truckList;

	private ArrayList<Double> amountDrivenList;
	private int highestAmountDriven = -5; // for factual purposes at the end.
	// maybe you could populate a list with all of them and print it at the end
	// just for the sake of it?

	/*
	 * Three levels of complexity for the constructor. Didn't bother making one
	 * which randomly decides truck preference but lets you manually sets skill
	 * because who even does that.
	 */

	/**
	 * The simplest constructor - you just pass the name and age of the trucker,
	 * their skill and truck gets randomly selected. Apologies to women, this
	 * simulator is sexist and assumes that only men drive trucks.
	 * 
	 * @param nameToGive
	 *            The name of the trucker.
	 * @param ageToGive
	 *            The age of the trucker
	 */
	public Trucker(String nameToGive, int ageToGive) {
		name = nameToGive;
		age = ageToGive;
		determineTruckPreference();
		determineTruck();
		determineSkillFactor();
		currentJob = new Job();
		distanceLeftToDrive = currentJob.getDestinationDistance();
		amountDrivenList = new ArrayList<Double>();
	}

	/**
	 * Second level of complexity - you pass the name, age and truckPreference
	 * (as a String from the five makers which are included in the simulation -
	 * if the name comes from somewhere else the program will throw an
	 * exception).
	 * 
	 * @param nameToGive
	 *            The name of the trucker.
	 * @param ageToGive
	 *            The age of the trucker.
	 * @param truckPreferenceToSet
	 *            His preference in truck makers.
	 */
	public Trucker(String nameToGive, int ageToGive, String truckPreferenceToSet) {
		name = nameToGive;
		age = ageToGive;
		truckPreference = truckPreferenceToSet;
		determineTruck();
		determineSkillFactor();
		currentJob = new Job();
		distanceLeftToDrive = currentJob.getDestinationDistance();
		amountDrivenList = new ArrayList<Double>();
	}

	/**
	 * Most customisable constructor - you pass the name, age, truckPreference
	 * and skillFactor (float between 1 and 2; program will throw exception if
	 * the number is out of these bounds).
	 * 
	 * @param nameToGive
	 *            The name of the trucker.
	 * @param ageToGive
	 *            The age of the trucker.
	 * @param truckPreferenceToSet
	 *            His preference in truck makers.
	 * @param skillFactorToSet
	 *            His skill.
	 */
	public Trucker(String nameToGive, int ageToGive, String truckPreferenceToSet, float skillFactorToSet) {
		name = nameToGive;
		age = ageToGive;
		truckPreference = truckPreferenceToSet;
		determineTruck();
		skillFactor = skillFactorToSet;
		currentJob = new Job();
		distanceLeftToDrive = currentJob.getDestinationDistance();
		amountDrivenList = new ArrayList<Double>();
	}

	/*- Constructor related methods -*/

	/**
	 * Randomly determines a number between 1.0 and 2.0 which decides the
	 * trucker's skill level.
	 */
	public void determineSkillFactor() {
		Random rand = new Random();
		float skillFactorToSet = (float) (rand.nextFloat() + 1.0);
		skillFactor = skillFactorToSet;
	}

	/**
	 * Simply selects a random truck maker from the list we have.
	 */
	public void determineTruckPreference() {
		Random rand = new Random();
		truckPreference = truckList.get(rand.nextInt(truckList.size()));
	}

	/**
	 * This method determines the truck whose wheel our driver will get behind.
	 * First it selects the appropriate model list, randomly selects one of the
	 * models and assigns an instance of that model to the Driver's instance
	 * variable.
	 */
	public void determineTruck() {
		ArrayList<String> modelList = new ArrayList<String>();
		Random rand = new Random();

		switch (truckPreference) {
		case "MAN":
			modelList = (new MAN()).getModelList();
			break;
		case "MercedesBenz":
			modelList = (new MercedesBenz()).getModelList();
			break;
		case "Renault":
			modelList = (new Renault()).getModelList();
			break;
		case "Scania":
			modelList = (new Scania()).getModelList();
			break;
		case "Volvo":
			modelList = (new Volvo()).getModelList();
			break;
		}

		String modelToPick = modelList.get(rand.nextInt(modelList.size()));

		switch (truckPreference) {
		case "MAN":
			drivenTruck = new MAN(modelToPick);
			break;
		case "MercedesBenz":
			drivenTruck = new MercedesBenz(modelToPick);
			break;
		case "Renault":
			drivenTruck = new Renault(modelToPick);
			break;
		case "Scania":
			drivenTruck = new Scania(modelToPick);
			break;
		case "Volvo":
			drivenTruck = new Volvo(modelToPick);
			break;
		}
	}

	/**
	 * The main simulation method.
	 * 
	 * @throws Exception
	 */
	public void timePasses() throws Exception {

		if (restingHours > 0) {
			restingHours--; // means the driver has slept this hour
		} else {
			int energyToLose = 7;
			/*
			 * old random method Random rand = new Random(); energyToLose -=
			 * rand.nextInt(4);
			 */
			energyMeter -= energyToLose; // lose between 7 and 10 energy per
			// hour

			/**
			 * This is where the sleepBreak happens!
			 */
			if (energyMeter < 30) {
				sleepBreak();
				if (energyMeter > 100)
					energyMeter = 100;
			}

			/**
			 * This is where the driver has to stop and refuel
			 */
			if (drivenTruck.getRefuelState() == true && restingHours == 0) {
				refuelBreak();
			}
		}
	}

	/**
	 * When the trucker takes a break between 6 and 12 hours (number randomly
	 * determined based off his tired level) which rests his energyMeter by 7
	 * per hour. You also feed yourself and buy some supplies for the road.
	 * 
	 * @throws Exception
	 */
	public void sleepBreak() throws Exception {

		if (restingHours > 0) {
			throw new Exception("Error! Trucker entered sleepBreak while he was resting!");
		}

		Random rand = new Random();
		int hoursToSleep = 5 + rand.nextInt(7);
		rest(hoursToSleep);
		System.out.println("Before heading off to sleep, " + name + " has a meal and buys some supplies for the road.");

		int moneyForSupplies = 50 + rand.nextInt(50);
		if (moneyForSupplies < 60) {
			System.out.println("He ate fast food and skimped out on supplies. Spent " + moneyForSupplies + "€.");
		} else if (moneyForSupplies < 80) {
			System.out.println(
					"He got Domino's and stocked up on beers for 'downtime'. Spent " + moneyForSupplies + "€.");
		} else if (moneyForSupplies < 100) {
			System.out.println("Proper Waitrose shop and a nice meal. Spent " + moneyForSupplies + "€.");
		}

		money -= moneyForSupplies;
	}

	/**
	 * A one hour break in which you fully refill your tank! Calculates your
	 * cost.
	 */
	private void refuelBreak() {
		double priceOfLitre = 1.34;
		double amountToAdd = 730 - drivenTruck.getFuelValue(); // 730L - max
		// tank size for
		// each truck
		double priceToPay = amountToAdd * priceOfLitre;
		money -= priceToPay;
		System.out
		.println(name + " stopped to refill his tank! It cost him " + String.format("%.2f", priceToPay) + "€.");
		drivenTruck.setRefuelState(false);
		drivenTruck.setFuelValue(730); // we've refilled the tank
		rest(1);
	}

	/**
	 * Method for the post job rest.
	 * 
	 * @param numberOfHours
	 */
	public void rest(int numberOfHours) {
		restWithoutEnergyGains(numberOfHours);
		energyMeter += 7 * numberOfHours;
	}

	/**
	 * A needed alternative for the post-job rest.
	 * 
	 * @param numberOfHours
	 */
	public void restWithoutEnergyGains(int numberOfHours) {
		restingHours = numberOfHours;
	}

	/**
	 * Populate the truck list with the available brands. A pretty bad and hard
	 * to extend implementation (every time we add a new brand, we have to
	 * accordingly edit this method) but it'll do for now.
	 */
	public void populateTruckList() {
		truckList = new ArrayList<String>();
		truckList.add("MAN");
		truckList.add("MercedesBenz");
		truckList.add("Renault");
		truckList.add("Scania");
		truckList.add("Volvo");
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	/**
	 * Gives the trucker a new job!
	 */
	public void jobCompleted() {
		String currentCity = currentJob.getDestinationCity();
		Job nextJob = new Job(currentCity);
		currentJob = nextJob;
		distanceLeftToDrive = currentJob.getDestinationDistance();
	}

	public void addNewJob() {
		currentJob = new Job();
		distanceLeftToDrive = currentJob.getDestinationDistance();
	}

	/**
	 * Returns the truck of the trucker.
	 * 
	 * @return drivenTruck The truck the truckers trucks with.
	 */
	public Truck getTruck() {
		return drivenTruck;
	}

	public float getSkillFactor() {
		return skillFactor;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getDistanceLeftToDrive() {
		return distanceLeftToDrive;
	}

	public void setDistanceLeftToDrive(double distanceLeftToDrive) {
		this.distanceLeftToDrive = distanceLeftToDrive;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double moneyToSet) {
		money = moneyToSet;
	}

	public void addMoney(double moneyToAdd) {
		money += moneyToAdd;
	}

	public int getRestingHours() {
		return restingHours;
	}

	public int getEnergyMeter() {
		return energyMeter;
	}

	/**
	 * Called at the end of the simulation; goes through the ArrayList of driven
	 * distanfes and returns the highest one.
	 */
	public void checkHighestAmountDriven() {
		for (double amountDriven : amountDrivenList) {
			if (amountDriven > highestAmountDriven) {
				highestAmountDriven = (int) amountDriven;
			}
		}
	}

	public int getHighestAmountDriven() {
		return highestAmountDriven;
	}

	/**
	 * You need to figure out something else for what you want. Check your notes
	 * in the Programming 1 notebook.
	 * 
	 * @return str The supposed debug info.
	 */
	public String getDebugInfo() {
		String str = "";
		if (Simulation.currentUnit == 1) {
			str += "Posting info for trucker " + name + "\n";
		}
		str += "Simulation Unit: " + Simulation.currentUnit + "\n";
		return str;
	}

	/**
	 * A method which gets called in Simulation's time passes to get a
	 * comprehensive list of all amounts driven.
	 * 
	 * @param amountDriven
	 *            The amount the trucker drove that hour.
	 */
	public void addAmountDriven(double amountDriven) {
		amountDrivenList.add(amountDriven);
	}

	public ArrayList<Double> getAmountDrivenList() {
		return amountDrivenList;
	}

	@Override
	public String toString() {
		String str = "====TRUCKER====\n";
		str += "Trucker Name: " + name + "\n";
		str += "Trucker Age: " + age + "\n";
		str += "Energy Level: " + energyMeter + " / 100\n";
		str += "Skill Factor: " + String.format("%.2f", skillFactor) + "\n";
		str += "Current Truck: " + drivenTruck.getMakerName() + " " + drivenTruck.getModelName() + "\n";
		str += "Account Balance: " + String.format("%.2f", money) + "€\n";
		str += "Distance Left to Drive: " + String.format("%.2f", distanceLeftToDrive) + "\n";
		str += currentJob;
		str += drivenTruck;
		return str;
	}
}
