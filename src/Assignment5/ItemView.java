package Assignment5;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ItemView extends JFrame implements ItemObserver {
	private JTextField tfId;
	private JTextField tfPartName;
	private JTextField tfVendor;
	private JTextField tfQty;
	private Gateway gateway;
	private ItemPart part;
	private ItemInventoryController invC;

	public ItemView(ItemInventoryController i, ItemPart p, Gateway otherGateway) {
		part = p;
		invC = i;

		this.gateway = otherGateway;
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		panel.add(new JLabel("Product ID"));
		tfId = new JTextField();
		panel.add(tfId);

		panel.add(new JLabel("Part Name"));
		tfPartName = new JTextField();
		panel.add(tfPartName);

		panel.add(new JLabel("Quantity"));
		tfQty = new JTextField();
		panel.add(tfQty);

		// panel.add(new JLabel("Vendor"));
		tfVendor = new JTextField();
		// panel.add(tfVendor);

		this.add(panel, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		JButton button = new JButton("Save");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(tfQty.getText().trim());
				} catch (Exception err) {
					ItemView.this.showError("Invalid Quantity!");
					return;
				}
				ItemPart p = invC.addPart(ItemView.this, part,
						Integer.parseInt(tfId.getText().trim()),
						tfPartName.getText(),
						Integer.parseInt(tfQty.getText().trim()),
						tfVendor.getText(), gateway);
				if (p != null) {
					if (part == null) {
						part = p;
						part.registerObserver(ItemView.this);
						ItemView.this.setTitle("Editing " + part.getPartName());
					} else
						part = p;
				}
			}
		});
		panel.add(button);

		this.add(panel, BorderLayout.SOUTH);

		// set for edit mode
		if (p != null) {
			tfId.setText(Integer.toString(part.getPartId()));
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfQty.setText(Integer.toString(part.getQuantity()));
			this.setTitle("Editing " + p.getPartName());
		} else
			this.setTitle("Adding New Item");
	}

	public void showError(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error!",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void updateObserver2(ItemPart part) {
		if (part != null) {
			tfId.setText(Integer.toString(part.getPartId()));
			tfPartName.setText(part.getPartName());
			tfVendor.setText(part.getVendor());
			tfQty.setText(Integer.toString(part.getQuantity()));
			this.setTitle("Editing " + part.getPartName());
		}
	}

	@Override
	public void modelDeleted2() {
		this.setVisible(false);

	}

}
