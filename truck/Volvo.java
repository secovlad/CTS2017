package seco.cts.main.truck;

import java.util.ArrayList;
import java.util.Random;

public class Volvo extends Truck {

	private ArrayList<String> modelList;

	public Volvo() {
		initList();
		makerName = "Volvo";
		Random rand = new Random();
		int chooseBit = rand.nextInt(2); // higher chance of better one ;)
		if (chooseBit == 1) {
			modelName = "FH16 2009";
		} else {
			modelName = "FH16 2014";
		}
	}

	/**
	 * Making note only here - you have the means necessary to throw exceptions
	 * if the model names are wrong in the constructor - just check if they are
	 * in the (conveniently made protected) modelMap.
	 * 
	 * @param modelToPick
	 */
	public Volvo(String modelToPick) {
		initList();
		makerName = "Volvo";
		modelName = modelToPick;
	}

	public void initList() {
		super.initList();
		modelList = new ArrayList<String>();
		modelList.add("FH16 2009");
		modelList.add("FH16 2014");
	}

	public ArrayList<String> getModelList() {
		return modelList;
	}
}
