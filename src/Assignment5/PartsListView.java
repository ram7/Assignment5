package Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.sql.*;

public class PartsListView extends JFrame implements PartInventoryObserver {
	private static final long serialVersionUID = 1L;
	private JList list;
	private DefaultListModel listModel;
	private Gateway gateway;
	private PartInventoryController invC;

	
	public PartsListView(PartInventoryController invC, final PartInventory partinv, Gateway otherGateway) {
		//buttons on top (north)
		//list view center

		this.invC = invC;
		this.gateway = otherGateway;
		this.setLayout(new BorderLayout());

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		JButton button = new JButton("Template Parts");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int index = list.getSelectedIndex();
				if (index >= 0 && index < PartsListView.this.invC.getNumParts()) {
					ItemInventory inv = new ItemInventory();
					gateway.setPartView(partinv);
					gateway.getData();
					ItemInventoryController invC = new ItemInventoryController(
							inv);
					// inv.addPart(null, 2, "Part 1", 233, "6t");
					ItemsListView pView = new ItemsListView(invC, inv, gateway); // pass the gateway here. look in itemslistview
					pView.setTitle("Template Parts List");
					pView.setSize(450, 300);
					pView.setLocation(400, 0);
					pView.setVisible(true);
					inv.registerObserver(pView);
				}
			}
		});
		buttonPanel.add(button);
		button = new JButton("Add Template");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PartView v = new PartView(PartsListView.this.invC, null);
				v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				v.setSize(400, 200);
				v.setLocation(400, 330);
				v.setVisible(true);

			}
		});
		buttonPanel.add(button);
		button = new JButton("Delete Template");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if (index >= 0 && index < PartsListView.this.invC.getNumParts()) {
					Part p = PartsListView.this.invC.getPartByIndex(index);
					PartsListView.this.invC.deletePart(p);
				}
			}
		});
		buttonPanel.add(button);

		this.add(buttonPanel, BorderLayout.SOUTH);

		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setFixedCellWidth(100);
		// list.setSelectedIndex(0);//init list selected -> first item
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// can also use
				// foxColor = LIST_LABELS[((JList<String>)
				// e.getSource()).getSelectedIndex()];
				// foxColor = (String) ((JList<String>)
				// e.getSource()).getSelectedValue();
				// updateStatusBar();
			}
		});
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				JList list = (JList) evt.getSource();
				if (evt.getClickCount() >= 2) {

					int index = list.locationToIndex(evt.getPoint());
					Part p = PartsListView.this.invC.getPartByIndex(index);
					PartView v = new PartView(PartsListView.this.invC, p);
					p.registerObserver(v);
					v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					v.setSize(400, 200);
					v.setLocation(0, 330);
					v.setVisible(true);

					// int index = list.locationToIndex(evt.getPoint());
					// Part p = PartsListView.this.invC.getPartByIndex(index);
					// PartView v = new PartView(PartsListView.this.invC, p);
					// p.registerObserver(v);
					// v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					// v.setSize(400, 200);
					// v.setLocation(500, 100);
					// v.setVisible(true);
				}
			}
		});
		this.add(new JScrollPane(list), BorderLayout.CENTER);

		// update the list with the inventory model
		updateObserver(partinv);

	}

	@Override
	public void updateObserver(PartInventory inv) {
		listModel.clear();
		for (Part p : inv.getParts())
			listModel.addElement(p.getVendor());
		// int i = list.getSelectedIndex();
		// if(inv.getNumParts() > 0)
		// list.setSelectedIndex(0);
	}
}
