package seco.cts.main.truck;

import java.util.ArrayList;
import java.util.Random;

public class Renault extends Truck {

	private ArrayList<String> modelList;

	public Renault() {
		initList();
		makerName = "Renault";
		Random rand = new Random();
		int chooseBit = rand.nextInt(2); // higher chance of better one ;)
		if (chooseBit == 1) {
			modelName = "Premium";
		} else {
			modelName = "Magnum";
		}
	}

	public Renault(String modelToPick) {
		initList();
		makerName = "Renault";
		modelName = modelToPick;
	}

	public void initList() {
		super.initList();
		modelList = new ArrayList<String>();
		modelList.add("Magnum");
		modelList.add("Premium");
	}

	public ArrayList<String> getModelList() {
		return modelList;
	}
}
