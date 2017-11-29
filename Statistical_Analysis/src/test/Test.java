package test;
import white.*;
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

public class Test implements Runnable{
	public static void main(String[] ar) {
		ConsoleOutputCapturer cap = new ConsoleOutputCapturer();
		cap.start();
		
		System.out.println("Line 1");
		System.out.println("Line 2");
		
		BufferedWriter bw = Utils.BufferedWriterCreator("./ConsoleCaptuerTest.txt");
		String str = cap.stop();
		
		try {
			bw.write(str);
			bw.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
