package seco.cts.main.truck;

import java.util.ArrayList;
import java.util.Random;

public class Scania extends Truck {

	private ArrayList<String> modelList;

	/**
	 * Warning! (only posting here). Not easily extensible code (i.e would have
	 * to change this code completely if any manufacturer gets more than two
	 * models). But i most realistically won't get back to this project and it's
	 * nice to hacky half ass things sometimes so here it goes ;)
	 * 
	 * @param modelToPick
	 */
	public Scania() {
		initList();
		makerName = "Scania";
		Random rand = new Random();
		int chooseBit = rand.nextInt(2); // higher chance of better one ;)
		if (chooseBit == 1) {
			modelName = "R 2012";
		} else {
			modelName = "Streamline";
		}
	}

	public Scania(String modelToPick) {
		initList();
		makerName = "Scania";
		modelName = modelToPick;
	}

	public void initList() {
		super.initList();
		modelList = new ArrayList<String>();
		modelList.add("R 2012");
		modelList.add("Streamline");
	}

	public ArrayList<String> getModelList() {
		return modelList;
	}
}
