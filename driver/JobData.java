package seco.cts.main.driver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Living proof that a class can get too bloated, and then you make another
 * class and move all that crap in there! This basically acts as a different
 * access point for all the identical data which each job accesses when it is
 * created.
 * 
 * @author secov
 *
 */
public class JobData {

	public static final int NUMBER_OF_CITIES = 24;

	private int[][] distanceArray = new int[25][25];
	private ArrayList<String> cityList;
	private HashMap<Integer, String> arrayCityMap;
	private HashMap<String, Integer> cityDistanceMap;
	private HashMap<String, Double> cargoIndexMap;

	public JobData() {
		writingTheMatrix();
		populateArrayCityMap();
		populateCargoMap();
	}

	public int[][] getDistanceArray() {
		return distanceArray;
	}

	public HashMap<Integer, String> getArrayCityMap() {
		return arrayCityMap;
	}

	public HashMap<String, Integer> getCityDistanceMap() {
		return cityDistanceMap;
	}

	public ArrayList<String> getCityList() {
		return cityList;
	}

	public HashMap<String, Double> getCargoIndexMap() {
		return cargoIndexMap;
	}

	public void populateCargoMap() {
		cargoIndexMap = new HashMap<String, Double>();
		cargoIndexMap.put("Vegetables", 1.0);
		cargoIndexMap.put("Fruit", 1.1);
		cargoIndexMap.put("Corn", 1.2);
		cargoIndexMap.put("Cows", 1.3);
		cargoIndexMap.put("Furniture", 1.4);
		cargoIndexMap.put("Coal", 1.5);
		cargoIndexMap.put("Iron Products", 1.7);
		cargoIndexMap.put("Automobiles", 1.9);
		cargoIndexMap.put("Gas", 2.0);
	}

	public void populateArrayCityMap() {
		cityList = new ArrayList<String>(25);
		arrayCityMap = new HashMap<Integer, String>();
		cityDistanceMap = new HashMap<String, Integer>();

		cityList.add("Barcelona");
		cityList.add("Belgrade");
		cityList.add("Berlin");
		cityList.add("Brussels");
		cityList.add("Bucharest");
		cityList.add("Budapest");
		cityList.add("Copenhagen");
		cityList.add("Dublin");
		cityList.add("Hamburg");
		cityList.add("Istanbul");
		cityList.add("Kiev");
		cityList.add("London");
		cityList.add("Madrid");
		cityList.add("Milan");
		cityList.add("Moscow");
		cityList.add("Munich");
		cityList.add("Paris");
		cityList.add("Prague");
		cityList.add("Rome");
		cityList.add("Saint Petersburg");
		cityList.add("Sofia");
		cityList.add("Stockholm");
		cityList.add("Vienna");
		cityList.add("Warsaw");

		arrayCityMap.put(1, "Barcelona");
		arrayCityMap.put(2, "Belgrade");
		arrayCityMap.put(3, "Berlin");
		arrayCityMap.put(4, "Brussels");
		arrayCityMap.put(5, "Bucharest");
		arrayCityMap.put(6, "Budapest");
		arrayCityMap.put(7, "Copenhagen");
		arrayCityMap.put(8, "Dublin");
		arrayCityMap.put(9, "Hamburg");
		arrayCityMap.put(10, "Istanbul");
		arrayCityMap.put(11, "Kiev");
		arrayCityMap.put(12, "London");
		arrayCityMap.put(13, "Madrid");
		arrayCityMap.put(14, "Milan");
		arrayCityMap.put(15, "Moscow");
		arrayCityMap.put(16, "Munich");
		arrayCityMap.put(17, "Paris");
		arrayCityMap.put(18, "Prague");
		arrayCityMap.put(19, "Rome");
		arrayCityMap.put(20, "Saint Petersburg");
		arrayCityMap.put(21, "Sofia");
		arrayCityMap.put(22, "Stockholm");
		arrayCityMap.put(23, "Vienna");
		arrayCityMap.put(24, "Warsaw");

		cityDistanceMap.put("Barcelona", 1);
		cityDistanceMap.put("Belgrade", 2);
		cityDistanceMap.put("Berlin", 3);
		cityDistanceMap.put("Brussels", 4);
		cityDistanceMap.put("Bucharest", 5);
		cityDistanceMap.put("Budapest", 6);
		cityDistanceMap.put("Copenhagen", 7);
		cityDistanceMap.put("Dublin", 8);
		cityDistanceMap.put("Hamburg", 9);
		cityDistanceMap.put("Istanbul", 10);
		cityDistanceMap.put("Kiev", 11);
		cityDistanceMap.put("London", 12);
		cityDistanceMap.put("Madrid", 13);
		cityDistanceMap.put("Milan", 14);
		cityDistanceMap.put("Moscow", 15);
		cityDistanceMap.put("Munich", 16);
		cityDistanceMap.put("Paris", 17);
		cityDistanceMap.put("Prague", 18);
		cityDistanceMap.put("Rome", 19);
		cityDistanceMap.put("Saint Petersburg", 20);
		cityDistanceMap.put("Sofia", 21);
		cityDistanceMap.put("Stockholm", 22);
		cityDistanceMap.put("Vienna", 23);
		cityDistanceMap.put("Warsaw", 24);
	}

	public void writingTheMatrix() {
		// note - this really is some proper long - haul trucking
		// also really tedious work

		/* Barcelona */
		distanceArray[2][1] = 1528;
		distanceArray[3][1] = 1497;
		distanceArray[4][1] = 1062;
		distanceArray[5][1] = 1968;
		distanceArray[6][1] = 1498;
		distanceArray[7][1] = 1757;
		distanceArray[8][1] = 1469;
		distanceArray[9][1] = 1471;
		distanceArray[10][1] = 2230;
		distanceArray[11][1] = 2391;
		distanceArray[12][1] = 1137;
		distanceArray[13][1] = 504;
		distanceArray[14][1] = 725;
		distanceArray[15][1] = 3006;
		distanceArray[16][1] = 1054;
		distanceArray[17][1] = 831;
		distanceArray[18][1] = 1353;
		distanceArray[19][1] = 856;
		distanceArray[20][1] = 2813;
		distanceArray[21][1] = 1745;
		distanceArray[22][1] = 2276;
		distanceArray[23][1] = 1347;
		distanceArray[24][1] = 1862;

		/* Belgrade */
		distanceArray[3][2] = 999;
		distanceArray[4][2] = 1372;
		distanceArray[5][2] = 447;
		distanceArray[6][2] = 316;
		distanceArray[7][2] = 1327;
		distanceArray[8][2] = 2145;
		distanceArray[9][2] = 1229;
		distanceArray[10][2] = 809;
		distanceArray[11][2] = 976;
		distanceArray[12][2] = 1688;
		distanceArray[13][2] = 2026;
		distanceArray[14][2] = 885;
		distanceArray[15][2] = 1710;
		distanceArray[16][2] = 773;
		distanceArray[17][2] = 1445;
		distanceArray[18][2] = 738;
		distanceArray[19][2] = 721;
		distanceArray[20][2] = 1797;
		distanceArray[21][2] = 329;
		distanceArray[22][2] = 1620;
		distanceArray[23][2] = 489;
		distanceArray[24][2] = 826;

		/* Berlin */
		distanceArray[4][3] = 651;
		distanceArray[5][3] = 1293;
		distanceArray[6][3] = 689;
		distanceArray[7][3] = 354;
		distanceArray[8][3] = 1315;
		distanceArray[9][3] = 254;
		distanceArray[10][3] = 1735;
		distanceArray[11][3] = 1024;
		distanceArray[12][3] = 929;
		distanceArray[13][3] = 1867;
		distanceArray[14][3] = 840;
		distanceArray[15][3] = 1607;
		distanceArray[16][3] = 501;
		distanceArray[17][3] = 876;
		distanceArray[18][3] = 280;
		distanceArray[19][3] = 1181;
		distanceArray[20][3] = 1319;
		distanceArray[21][3] = 1318;
		distanceArray[22][3] = 810;
		distanceArray[23][3] = 523;
		distanceArray[24][3] = 516;

		/* Brussels */
		distanceArray[5][4] = 1769;
		distanceArray[6][4] = 1131;
		distanceArray[7][4] = 766;
		distanceArray[8][4] = 773;
		distanceArray[9][4] = 489;
		distanceArray[10][4] = 2178;
		distanceArray[11][4] = 1836;
		distanceArray[12][4] = 318;
		distanceArray[13][4] = 1314;
		distanceArray[14][4] = 696;
		distanceArray[15][4] = 2253;
		distanceArray[16][4] = 601;
		distanceArray[17][4] = 261;
		distanceArray[18][4] = 721;
		distanceArray[19][4] = 1171;
		distanceArray[20][4] = 1903;
		distanceArray[21][4] = 1697;
		distanceArray[22][4] = 1280;
		distanceArray[23][4] = 914;
		distanceArray[24][4] = 1159;

		/* Bucharest */
		distanceArray[6][5] = 639;
		distanceArray[7][5] = 1571;
		distanceArray[8][5] = 2534;
		distanceArray[9][5] = 1544;
		distanceArray[10][5] = 445;
		distanceArray[11][5] = 744;
		distanceArray[12][5] = 2088;
		distanceArray[13][5] = 2469;
		distanceArray[14][5] = 1331;
		distanceArray[15][5] = 1497;
		distanceArray[16][5] = 1186;
		distanceArray[17][5] = 1869;
		distanceArray[18][5] = 1076;
		distanceArray[19][5] = 1137;
		distanceArray[20][5] = 1740;
		distanceArray[21][5] = 296;
		distanceArray[22][5] = 1742;
		distanceArray[23][5] = 855;
		distanceArray[24][5] = 946;

		/* Budapest */
		distanceArray[7][6] = 1011;
		distanceArray[8][6] = 1894;
		distanceArray[9][6] = 927;
		distanceArray[10][6] = 1064;
		distanceArray[11][6] = 894;
		distanceArray[12][6] = 1450;
		distanceArray[13][6] = 1975;
		distanceArray[14][6] = 788;
		distanceArray[15][6] = 1565;
		distanceArray[16][6] = 563;
		distanceArray[17][6] = 1247;
		distanceArray[18][6] = 443;
		distanceArray[19][6] = 811;
		distanceArray[20][6] = 1556;
		distanceArray[21][6] = 629;
		distanceArray[22][6] = 1316;
		distanceArray[23][6] = 216;
		distanceArray[24][6] = 545;

		/* Copenhagen */
		distanceArray[8][7] = 1238;
		distanceArray[9][7] = 287;
		distanceArray[10][7] = 2017;
		distanceArray[11][7] = 1326;
		distanceArray[12][7] = 955;
		distanceArray[13][7] = 2071;
		distanceArray[14][7] = 1157;
		distanceArray[15][7] = 1558;
		distanceArray[16][7] = 838;
		distanceArray[17][7] = 1025;
		distanceArray[18][7] = 633;
		distanceArray[19][7] = 1529;
		distanceArray[20][7] = 1143;
		distanceArray[21][7] = 1635;
		distanceArray[22][7] = 521;
		distanceArray[23][7] = 868;
		distanceArray[24][7] = 667;

		/* Dublin */
		distanceArray[9][8] = 1073;
		distanceArray[10][8] = 2950;
		distanceArray[11][8] = 2513;
		distanceArray[12][8] = 462;
		distanceArray[13][8] = 1449;
		distanceArray[14][8] = 1413;
		distanceArray[15][8] = 2792;
		distanceArray[16][8] = 1374;
		distanceArray[17][8] = 776;
		distanceArray[18][8] = 1465;
		distanceArray[19][8] = 1882;
		distanceArray[20][8] = 2314;
		distanceArray[21][8] = 2471;
		distanceArray[22][8] = 1626;
		distanceArray[23][8] = 1680;
		distanceArray[24][8] = 1823;

		/* Hamburg */
		distanceArray[10][9] = 1983;
		distanceArray[11][9] = 1440;
		distanceArray[12][9] = 720;
		distanceArray[13][9] = 1785;
		distanceArray[14][9] = 900;
		distanceArray[15][9] = 1779;
		distanceArray[16][9] = 610;
		distanceArray[17][9] = 744;
		distanceArray[18][9] = 492;
		distanceArray[19][9] = 1307;
		distanceArray[20][9] = 1414;
		distanceArray[21][9] = 1554;
		distanceArray[22][9] = 809;
		distanceArray[23][9] = 742;
		distanceArray[24][9] = 750;

		/* Istanbul */
		distanceArray[11][10] = 1052;
		distanceArray[12][10] = 2496;
		distanceArray[13][10] = 2734;
		distanceArray[14][10] = 1669;
		distanceArray[15][10] = 1753;
		distanceArray[16][10] = 1582;
		distanceArray[17][10] = 2253;
		distanceArray[18][10] = 1507;
		distanceArray[19][10] = 1373;
		distanceArray[20][10] = 2099;
		distanceArray[21][10] = 502;
		distanceArray[22][10] = 2171;
		distanceArray[23][10] = 1273;
		distanceArray[24][10] = 1386;

		/* Kiev */
		distanceArray[12][11] = 2131;
		distanceArray[13][11] = 2859;
		distanceArray[14][11] = 1672;
		distanceArray[15][11] = 756;
		distanceArray[16][11] = 1391;
		distanceArray[17][11] = 2022;
		distanceArray[18][11] = 1138;
		distanceArray[19][11] = 1673;
		distanceArray[20][11] = 1051;
		distanceArray[21][11] = 1020;
		distanceArray[22][11] = 1265;
		distanceArray[23][11] = 1052;
		distanceArray[24][11] = 690;

		/* London */
		distanceArray[13][12] = 1263;
		distanceArray[14][12] = 957;
		distanceArray[15][12] = 2498;
		distanceArray[16][12] = 916;
		distanceArray[17][12] = 340;
		distanceArray[18][12] = 1034;
		distanceArray[19][12] = 1431;
		distanceArray[20][12] = 2093;
		distanceArray[21][12] = 2012;
		distanceArray[22][12] = 1431;
		distanceArray[23][12] = 1233;
		distanceArray[24][12] = 1445;

		/* Madrid */
		distanceArray[14][13] = 1187;
		distanceArray[15][13] = 3473;
		distanceArray[16][13] = 1484;
		distanceArray[17][13] = 1053;
		distanceArray[18][13] = 1773;
		distanceArray[19][13] = 1360;
		distanceArray[20][13] = 3183;
		distanceArray[21][13] = 2250;
		distanceArray[22][13] = 2591;
		distanceArray[23][13] = 1807;
		distanceArray[24][13] = 2288;

		/* Milan */
		distanceArray[15][14] = 2283;
		distanceArray[16][14] = 348;
		distanceArray[17][14] = 641;
		distanceArray[18][14] = 646;
		distanceArray[19][14] = 476;
		distanceArray[20][14] = 2122;
		distanceArray[21][14] = 1166;
		distanceArray[22][14] = 1650;
		distanceArray[23][14] = 623;
		distanceArray[24][14] = 1143;

		/* Moscow */
		distanceArray[16][15] = 1957;
		distanceArray[17][15] = 2484;
		distanceArray[18][15] = 1664;
		distanceArray[19][15] = 2374;
		distanceArray[20][15] = 632;
		distanceArray[21][15] = 1777;
		distanceArray[22][15] = 1227;
		distanceArray[23][15] = 1669;
		distanceArray[24][15] = 1149;

		/* Munich */
		distanceArray[17][16] = 685;
		distanceArray[18][16] = 300;
		distanceArray[19][16] = 698;
		distanceArray[20][16] = 1773;
		distanceArray[21][16] = 1096;
		distanceArray[22][16] = 1311;
		distanceArray[23][16] = 354;
		distanceArray[24][16] = 809;

		/* Paris */
		distanceArray[18][17] = 885;
		distanceArray[19][17] = 1105;
		distanceArray[20][17] = 2157;
		distanceArray[21][17] = 1758;
		distanceArray[22][17] = 1541;
		distanceArray[23][17] = 1033;
		distanceArray[24][17] = 1365;

		/* Prague */
		distanceArray[19][18] = 922;
		distanceArray[20][18] = 1476;
		distanceArray[21][18] = 1064;
		distanceArray[22][18] = 1052;
		distanceArray[23][18] = 250;
		distanceArray[24][18] = 514;

		/* Rome */
		distanceArray[20][19] = 2339;
		distanceArray[21][19] = 894;
		distanceArray[22][19] = 1974;
		distanceArray[23][19] = 763;
		distanceArray[24][19] = 1316;

		/* Saint Petersburg */
		distanceArray[21][20] = 1969;
		distanceArray[22][20] = 688;
		distanceArray[23][20] = 1577;
		distanceArray[24][20] = 1023;

		/* Sofia */
		distanceArray[22][21] = 1884;
		distanceArray[23][21] = 817;
		distanceArray[24][21] = 1076;

		/* Stockholm */
		distanceArray[23][22] = 1241;
		distanceArray[24][22] = 808;

		/* Vienna */
		distanceArray[24][23] = 557;

		/* Warsaw */
		// already had everything inputted

		// time to fill in the other side!
		for (int i = 1; i <= NUMBER_OF_CITIES; i++) {
			for (int j = 1; j <= NUMBER_OF_CITIES; j++) {
				if (i == j)
					distanceArray[i][j] = 0;
				else if (i != j && j > i) {
					distanceArray[i][j] = distanceArray[j][i];
				}
			}
		}
	}
}
