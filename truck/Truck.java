package seco.cts.main.truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * The class that models a truck. It was initially going to be abstract but then
 * I realized it would be much easier to model random generation if I could
 * instantiate it. Hence this was born.
 * 
 * @author secov
 *
 */
public class Truck {

	private static final double MAXIMUM_FUEL = 730.0;// 730 is an avg. number

	// each truck needs these arrays to know its head around what it actually is
	// good coding practice? i don't know yet.
	protected ArrayList<String> makerList;
	protected HashMap<String, String> modelMap;
	// for determining medium speed !
	private HashMap<String, Integer> modelSpeedMap;

	protected int mediumSpeed; // gets picked according to the model it will be
								// from the modelSpeedMap
	protected String makerName; // gets set in each concrete class accordingly
	protected String modelName; // gets picked from the modelSpeedMap
	private boolean refuelState = false;
	private double fuel = MAXIMUM_FUEL; // you could make an entirely new map
										// and separate tank size between
										// trucks, but
	private double mileage = 0;

	/**
	 * Blank constructor gets you a random truck!
	 */
	public Truck() {
		initList();
		generateRandomMakerName();
		generateRandomModelName();
		setMediumSpeed();
	}

	/**
	 * Constructor which passes the makerName as its parameter. The model gets
	 * randomly chosen.
	 * 
	 * @param makerName
	 *            The name of the maker of the truck.
	 */
	public Truck(String makerName) {
		initList();
		generateRandomModelName();
		setMediumSpeed();
	}

	/**
	 * Constructor which takes both the maker and the model name of the truck. I
	 * should probably account for errors in cases in which the maker or model
	 * names are invalid...
	 * 
	 * @param makerName
	 *            The name of the maker of the truck.
	 * @param modelName
	 *            The name of the model of the truck.
	 */
	public Truck(String makerName, String modelName) {
		initList();
		setMediumSpeed();
	}

	public void timePasses() {
		// decrement fuel - refuel break check occurs in Simulation.java
		fuel -= 0.21;
		Random rand = new Random();
		int truckerPanic = rand.nextInt(150);
		if (fuel < 150 + truckerPanic) {
			refuelState = true;
		}
	}

	/**
	 * The crucial method which populates a list of trucks and models.
	 * Observation: This could be made into a TruckData, but it's not nearly as
	 * much as the Job. So I don't think it justifies a separate class.
	 */
	protected void initList() {
		makerList = new ArrayList<String>();
		modelMap = new HashMap<String, String>();
		modelSpeedMap = new HashMap<String, Integer>();

		makerList.add("MAN");
		makerList.add("MercedesBenz");
		makerList.add("Renault");
		makerList.add("Scania");
		makerList.add("Volvo");

		modelMap.put("MAN", "TGX");
		modelMap.put("MercedesBenz", "Actros");
		modelMap.put("MercedesBenz", "NewActros");
		modelMap.put("Renault", "Magnum");
		modelMap.put("Renault", "Premium");
		modelMap.put("Scania", "R 2012");
		modelMap.put("Scania", "Streamline");
		modelMap.put("Volvo", "FH16 2009");
		modelMap.put("Volvo", "FH16 2014");

		modelSpeedMap.put("TGX", 107);
		modelSpeedMap.put("Actros", 91);
		modelSpeedMap.put("NewActros", 96);
		modelSpeedMap.put("Premium", 80);
		modelSpeedMap.put("Magnum", 86);
		modelSpeedMap.put("R 2012", 113);
		modelSpeedMap.put("Streamline", 122);
		modelSpeedMap.put("FH16 2009", 118);
		modelSpeedMap.put("FH16 2014", 128);
	}

	/* Constructor methods */

	private void generateRandomMakerName() {
		Random rand = new Random();
		String randMakerName = makerList.get(rand.nextInt(makerList.size()));
		makerName = randMakerName;
	}

	/**
	 * To only be executed after generateRandomMakerName()! Throws exception if
	 * car does not have a maker name
	 */
	private void generateRandomModelName() {
		Random rand = new Random();
		ArrayList<String> modelList = new ArrayList<String>();
		for (String makerNameToMatch : modelMap.keySet()) {
			if (makerNameToMatch.equals(makerName))
				modelList.add(modelMap.get(makerName));
		}
		modelName = modelList.get(rand.nextInt(modelList.size()));
	}

	private void setMediumSpeed() {
		mediumSpeed = modelSpeedMap.get(modelName);
	}

	public HashMap<String, Integer> getModelSpeedMap() {
		return modelSpeedMap;
	}

	public String getMakerName() {
		return makerName;
	}

	public String getModelName() {
		return modelName;
	}

	public int getMediumSpeed() {
		return mediumSpeed;
	}

	public boolean getRefuelState() {
		return refuelState;
	}

	public void setRefuelState(boolean state) {
		refuelState = state;
	}

	public double getFuelValue() {
		return fuel;
	}

	public void setFuelValue(double fuelToSet) {
		fuel = fuelToSet;
	}

	public double getMileage() {
		return mileage;
	}

	public void setMileage(double d) {
		mileage = d;
	}

	@Override
	public String toString() {
		String str = "";
		str += "=====TRUCK=====\n";
		str += "Maker Name: " + makerName + "\n";
		str += "Model Name: " + modelName + "\n";
		str += "Mileage: " + String.format("%.2f", mileage) + " km\n";
		str += "Medium Speed: " + mediumSpeed + " km/h\n";
		str += "Fuel Left: " + String.format("%.2f", fuel) + " / " + MAXIMUM_FUEL + "\n";
		return str;
	}
}
