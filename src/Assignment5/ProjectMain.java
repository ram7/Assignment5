package Assignment5;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class ProjectMain {

	public static void main(String[] args) throws ClassNotFoundException {
		
		final PartInventory inv2 = new PartInventory();
		final Gateway gateway = new Gateway();
		final PartInventoryController invC2 = new PartInventoryController(inv2);
		final PartsListView pView2 = new PartsListView(invC2, inv2, gateway);

		JFrame frame = new JFrame("Login Screen");
		JMenuBar menuBar;
		JMenu menu;
		JMenuItem menuItem;		
		menuBar = new JMenuBar();
		menu = new JMenu("Login as");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("Tom Jones");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pView2.setTitle("Templates (Logged in as Tom Jones)");
				pView2.setSize(400, 300);
				pView2.setVisible(true);
				inv2.registerObserver(pView2);		
			}
		});
		menu.add(menuItem);

		menuItem = new JMenuItem("Sue Smith");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pView2.setTitle("Templates (Logged in as Sue Smith)");
				pView2.setSize(400, 300);
				pView2.setVisible(true);
				inv2.registerObserver(pView2);		
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Ragnar Nelson");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				pView2.setTitle("Templates (Logged in as Ragnar Nelson)");
				pView2.setSize(400, 300);
				pView2.setVisible(true);
				inv2.registerObserver(pView2);		
			}
		});
		menu.add(menuItem);

		//5. Show it.
		frame.add(menuBar, BorderLayout.NORTH);
		frame.setSize(300, 200);
		frame.setVisible(true);

		
/*		PartInventory inv2 = new PartInventory();

		 //Gateway gateway = new Gateway(inv2);
		 //gateway.getData();

		Gateway gateway = new Gateway();
		PartInventoryController invC2 = new PartInventoryController(inv2);

		inv2.addPart(null, 0, "", "", "Sample Template");
		
		// views
		PartsListView pView2 = new PartsListView(invC2, inv2, gateway);
		pView2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pView2.setTitle("Templates");
		pView2.setSize(400, 300);
		pView2.setVisible(true);
		inv2.registerObserver(pView2);
*/
	}
	
}