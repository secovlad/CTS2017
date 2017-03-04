package seco.cts.main.truck;

import java.util.ArrayList;

public class MAN extends Truck {

	ArrayList<String> modelList;

	public MAN() {
		initList();
		makerName = "MAN";
		modelName = "TGX";
	}

	public MAN(String modelToPick) {
		initList();
		makerName = "MAN";
		modelName = modelToPick;
	}

	public void initList() {
		super.initList();
		modelList = new ArrayList<String>();
		modelList.add("TGX");
	}

	public ArrayList<String> getModelList() {
		return modelList;
	}
}
