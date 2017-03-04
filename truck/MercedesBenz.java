package seco.cts.main.truck;

import java.util.ArrayList;
import java.util.Random;

public class MercedesBenz extends Truck {

	private ArrayList<String> modelList;

	public MercedesBenz() {
		initList();
		makerName = "MercedesBenz";
		Random rand = new Random();
		int chooseBit = rand.nextInt(2); // higher chance of better one ;)
		if (chooseBit == 1) {
			modelName = "Actros";
		} else {
			modelName = "NewActros";
		}
	}

	public MercedesBenz(String modelToPick) {
		initList();
		makerName = "MercedesBenz";
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
