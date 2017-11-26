package white;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SymbolSetterModule extends JPanel {
	public String SYMBOL = "AAPL";
	
	SymbolSetterModule() {
		JLabel guide = new JLabel("Stock to Retrieve");
		guide.setBounds(0, 0, 100, 30);
		JTextField symbol = new JTextField();
		symbol.setEditable(true);
		symbol.setBounds(0, 30, 100, 30);
		JButton confirm = new JButton("Confirm");
		confirm.setBounds(0, 60, 100, 40);
		
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SYMBOL = symbol.getText();
			}
		});
		
		this.add(symbol);
		this.add(confirm);
		this.add(guide);
		
		
		this.setSize(150, 150);
		this.setLayout(null);
		this.setVisible(true);
	}
}
