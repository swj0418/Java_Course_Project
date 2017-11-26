package test;
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;

import black.*;
import blue.*;
import red.*;

public class Test {
	public static void main(String[] ar) {
		
		ArrayList<ArrayList<Double>> PriceMatrix = new ArrayList<ArrayList<Double>>();
		PriceMatrix.add(new Stock("AAPL").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("SFNC").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("CPRT").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("GOOG").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("EDIG").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("IRCP").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("WAYN").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("RCKY").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("TRCB").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		PriceMatrix.add(new Stock("TSLA").request("ADJ_CLOSE", "2015-01-01", "2017-01-01"));
		
		ArrayList<ArrayList<Double>> ArrayToPrint = Stoculator.CorrelationMatrix(PriceMatrix, 1, "ARITHMETIC");
		Utils.DoubleMatrixPrinter(ArrayToPrint);
	}
}
