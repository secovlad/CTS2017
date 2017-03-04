package seco.cts.main.driver;

import java.util.Random;

/**
 * This is some shit. What was indeed as a class to simply represent a Job it
 * ended up being a behemoth also storing other Job information which you did
 * not foresee (although you should have).
 * 
 * At this point it might even be worth refactoring (Atlas.java) but fuck -
 * hehehehe xPP HEY you did end up refactoring
 * 
 * You've hammered enough pointlessly, now let's tie it all together and hope it
 * works.
 * 
 * @author Vlad Secosan
 *
 */
public class Job {

	private JobData jobData;

	private String startingCity;
	private String destinationCity = ""; // for comparison
	private int prizeMoney = 0; // every job has a flat 4.2k rate
	private String cargo = "";
	private double destinationDistance; // for easier access

	private Random rand = new Random(); // for general randomising needs

	public Job() {
		jobData = new JobData();
		int randomStart = rand.nextInt(jobData.getCityList().size());
		startingCity = jobData.getCityList().get(randomStart);
		determineDestination();
		determineCargo();
		determineMoney();
	}

	public Job(String startingCity) {
		jobData = new JobData();
		this.startingCity = startingCity;
		determineDestination();
		determineCargo();
		determineMoney();
	}

	public void determineDestination() {
		int startBit = jobData.getCityList().indexOf(startingCity);
		int destBit = rand.nextInt(JobData.NUMBER_OF_CITIES);
		if (startBit == destBit) {
			destBit += rand.nextInt(10);
			if (destBit > 23)
				destBit -= 10;
		}
		destinationCity = jobData.getCityList().get(destBit);
		destinationDistance = jobData.getDistanceArray()[startBit + 1][destBit + 1];
	}

	public void determineCargo() {
		while (cargo.equals("")) {
			for (String cargoTo : jobData.getCargoIndexMap().keySet()) {
				int randBitOne = rand.nextInt(2);
				int randBitTwo = rand.nextInt(2);
				if (randBitOne == randBitTwo) {
					cargo = cargoTo;
					break;
				}
			}
		}
	}

	public void determineMoney() {
		prizeMoney = (int) (4201 + (destinationDistance * jobData.getCargoIndexMap().get(cargo) / 2));
	}

	/**
	 * Prints a list of all the different routes truckers can take!
	 */
	public void printDistanceBoard() {
		/*
		 * Method 1 - doesn't print as an actual matrix, just prints the routes
		 */
		for (int i = 1; i <= JobData.NUMBER_OF_CITIES; i++) {
			for (int j = 1; j <= JobData.NUMBER_OF_CITIES; j++) {
				if (i != j)
					System.out.println(jobData.getArrayCityMap().get(i) + " - " + jobData.getArrayCityMap().get(j)
							+ " : " + jobData.getDistanceArray()[i][j] + " km");
			}
		}

		/*
		 * Method 2 - attempts to print as a matrix - it obviously won't fit on
		 * the screen and look like vomit
		 */
		/*
		 * for (int i=0;i<NUMBER_OF_CITIES;i++){
		 * System.out.printf(cityList.get(i)+ " "); } for (int
		 * i=1;i<=NUMBER_OF_CITIES;i++){ System.out.println(cityList.get(i-1));
		 * for(int j=1;j<=NUMBER_OF_CITIES;j++)
		 * System.out.print(distanceArray[i][j] + " "); System.out.println(); }
		 */
	}

	public String getStartingCity() {
		return startingCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public double getDestinationDistance() {
		return destinationDistance;
	}

	public int getPrizeMoney() {
		return prizeMoney;
	}

	public String getCargo() {
		return cargo;
	}

	@Override
	public String toString() {
		String str = "";
		str += "========JOB========\n";
		str += "Starting City: " + startingCity + "\n";
		str += "Destination City: " + destinationCity + "\n";
		str += "Cargo: " + cargo + "\n";
		str += "Distance: " + destinationDistance + " km\n";
		str += "Prize Money: " + prizeMoney + "€\n";
		return str;
	}
}
