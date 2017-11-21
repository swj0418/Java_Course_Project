package test;
import java.util.ArrayList;
import java.util.HashMap;

import black.DataRetriever;
import blue.IndexBuilder;
import red.*;

public class Test {
	public static void main(String[] ar) {
		Stock S = new Stock("TSLA");
		System.out.println(S.Adj_Close_M.keySet());
	}
}
