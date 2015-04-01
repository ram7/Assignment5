package Assignment5;

import javax.swing.*;

public class ProjectMain {

	public static void main(String[] args) throws ClassNotFoundException {


		PartInventory inv2 = new PartInventory();

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

	}
}