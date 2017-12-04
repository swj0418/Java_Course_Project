package white;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import blue.DataCheckModule;
import gray.Global;

public class GUIMain {
	public static void main(String[] ar) {
		DataCheckModule DC = new DataCheckModule();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				if(Global.Validation == true) {
					Global.motherframe = new MotherFrame();
				} else {
					JFrame f = new JFrame();
					JOptionPane.showMessageDialog(f, "Specify the time window for calculating portfolio.");
				}
				
			}
		});
	}
}