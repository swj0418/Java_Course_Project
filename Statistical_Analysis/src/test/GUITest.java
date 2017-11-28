package test;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUITest extends JFrame{
	UpperPanel up;
	
	GUITest() {
		up = new UpperPanel();
		setSize(600, 600);
		setLayout(null);
		add(up);
		setVisible(true);
		buttoncenter();
	}
	
	public static void main(String[] ar) {
		GUITest GT = new GUITest();
	}
	
	private void buttoncenter() {
		up.lp1.button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = up.lp1.field.getText();
				up.lp2.text.setText(str);
				System.out.println(up.lp2.text);
			}
		});
	}
}


class UpperPanel extends JPanel {
	LowerPanel lp1;
	LowerPanel2 lp2;
	
	UpperPanel() {
		setLayout(null);
		setSize(600,600);
		lp1 = new LowerPanel();
		lp2 = new LowerPanel2();
		
		lp1.setBounds(0, 0, 500, 50);
		lp2.setBounds(0, 100, 300, 50);
		
		add(lp1);
		add(lp2);
		
		buttoncenter();
	}
	
	private void buttoncenter() {
		
	}
}

class LowerPanel extends JPanel {
	JLabel text = new JLabel("AAA");
	JTextField field = new JTextField();
	JButton button = new JButton("Change");
	GridLayout layout = new GridLayout();
	
	LowerPanel() {
		setLayout(layout);
		add(text); add(field); add(button);
	}
}

class LowerPanel2 extends JPanel {
	JLabel text = new JLabel("BBB");
	GridLayout layout = new GridLayout();
	
	LowerPanel2() {
		layout.setColumns(1);
		layout.setRows(1);
		setLayout(layout);
		add(text);
	}
}
