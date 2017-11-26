package white;
import java.awt.Frame;

import javax.swing.JFrame;

public class GeneralInfoFrame extends JFrame {
	SymbolSetterModule Symbolsetter = new SymbolSetterModule();
	public GeneralInfoModule GIM = new GeneralInfoModule(Symbolsetter.SYMBOL);
	
	GeneralInfoFrame() {
		new Frame("Test General Data");
		Symbolsetter.setBounds(450, 50, 100, 100);
		this.add(Symbolsetter);
		this.add(GIM);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	public static void main(String[] ar) {
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	            	new GeneralInfoFrame();
	            }
	        });
	}

}
