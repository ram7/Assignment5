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

public class ItemsListView extends JFrame implements ItemInventoryObserver {
	private static final long serialVersionUID = 1L;
	private JList list;
	private DefaultListModel listModel;
	private Gateway gateway;
	private ItemInventoryController invC;
	
	public ItemsListView(ItemInventoryController invC, ItemInventory inv, Gateway otherGateway) {
		//buttons on top (north)
		//list view center
		this.invC = invC;
		this.gateway = otherGateway;
		this.setLayout(new BorderLayout());
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		JButton button = new JButton("Add Part");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ItemView v = new ItemView(ItemsListView.this.invC, null, gateway); // pass the gateway here. look in itemspart
				v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				v.setSize(400, 200);
				v.setLocation(400, 330);
				v.setVisible(true);
			}
		});
		buttonPanel.add(button);
		button = new JButton("Delete Part");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if(index >= 0 && index < ItemsListView.this.invC.getNumParts()) {
					ItemPart p = ItemsListView.this.invC.getPartByIndex(index);
					ItemsListView.this.invC.deletePart(p);
				}
			}
		});
		buttonPanel.add(button);
		
		this.add(buttonPanel, BorderLayout.SOUTH);
		
		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.setFixedCellWidth(100);
		//list.setSelectedIndex(0);//init list selected -> first item
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				//can also use
				//foxColor = LIST_LABELS[((JList<String>) e.getSource()).getSelectedIndex()];
				//foxColor = (String) ((JList<String>) e.getSource()).getSelectedValue();
				//updateStatusBar();
			}
		});
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() >= 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            ItemPart p = ItemsListView.this.invC.getPartByIndex(index);
					ItemView v = new ItemView(ItemsListView.this.invC, p, gateway);
					p.registerObserver(v);
					v.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					v.setSize(400, 200);
					v.setLocation(850, 0);
					v.setVisible(true);
		        }
		    }
		});
		this.add(new JScrollPane(list), BorderLayout.CENTER);

		//update the list with the inventory model
		updateObserver2(inv);
		
	}

	@Override
	public void updateObserver2(ItemInventory inv) {
		listModel.clear();
		for(ItemPart p : inv.getParts())
			listModel.addElement(p.getPartName());
		//int i = list.getSelectedIndex();
		//if(inv.getNumParts() > 0)
			//list.setSelectedIndex(0);
	}
}
