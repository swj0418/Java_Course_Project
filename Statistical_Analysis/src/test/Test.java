package test;
import java.util.ArrayList;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;

import green.*;
import black.*;
import blue.*;
import red.*;

public class Test {
	public static void main(String[] ar) {
		ArrayList<ArrayList<Double>> PriceMatrix = new ArrayList<ArrayList<Double>>();
		PriceMatrix.add(new Stock("AAPL").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("GOOG").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("TSLA").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("AMZN").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("XRT").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("SRAX").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("MSFT").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("STX").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("XOM").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));
		PriceMatrix.add(new Stock("AVGO").request("ADJ_CLOSE", "2017-01-01", "2017-11-01"));

		ArrayList<ArrayList<Double>> ArrayToPrint = Stoculator.CorrelationMatrix(PriceMatrix, 30, "ARITHMETIC");
		Utils.DoubleMatrixPrinter(ArrayToPrint);
		
		Default d = new Default();
	}
}
