package seco.cts.main.simulation;

import seco.cts.main.driver.Trucker;

public class TestingGrounds {

	/**
	 * This has to be the best test code I've ever written. Or worst?
	 * (uncustomizability) Dar pana la urma tot mi-am bagat pula
	 * 
	 * @param args
	 *            n-am
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Simulation simulation = new Simulation();
		System.out.println("This is where we test new pieces of code! And stuff.");
		simulation.printTruckers();
		simulation.timePasses(1000);
		simulation.printTruckers();

		for (Trucker trucker : simulation.getTruckerList()) {
			trucker.checkHighestAmountDriven();
			System.out.println(
					trucker.getName() + "'s highest amount driven was: " + trucker.getHighestAmountDriven() + " km!");
		}

		System.out.println("We will print the entire list of distances that each trucker has made.");
		for (Trucker trucker : simulation.getTruckerList()) {
			System.out.println("====" + trucker.getName() + "====");
			int i = 1;
			for (double distance : trucker.getAmountDrivenList()) {
				System.out.println(i + " : " + String.format("%.2f", distance) + " km");
				i++;
				// and with this you notice a bug - they only rest for one hour
				// after completing a job.
			}

		}
	}
}
