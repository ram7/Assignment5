package Assignment5;


import java.util.ArrayList;

public class ItemPart{
	private int id;
	private String partName;
	private String location;
	private int quantity;
	private String vendor;
	private Gateway gateway;
	private ArrayList<ItemObserver> observers;

	public ItemPart(int id, String pName, int q, Gateway othergateway) {
		this(id, pName, q, "", othergateway);
		System.out.println("created itempart");
	}

	public ItemPart(int idNum, String pName, int q, String v, Gateway othergateway) {
	//	if (pName == null || pName.length() < 1)
	//		throw new IllegalArgumentException("Part Name cannot be blank");
		if (q < 1)
			throw new IllegalArgumentException("Quantity cannot be < 1");
		System.out.println("created itempart");
		gateway = othergateway;
		id = idNum;
		partName = pName;
		vendor = v;
		quantity = q;
		gateway.addPartDB(id, partName, vendor, quantity);
		gateway.updatePartDB(id, partName, vendor, quantity);


		observers = new ArrayList<ItemObserver>();
	}

	public int getPartId() {
		return id;
	}

	public void setPartId(int id) {
		this.id = id;
		updateObservers();
	}

	public String getPartName() {
		return partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
		updateObservers();
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
		updateObservers();
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		updateObservers();
	}
		

	public void registerObserver(ItemObserver o) {
		observers.add(o);
	}

	public void setFields(int pId, String pName, int q) {
		setPartName(pName);
//		setVendor(v);
		setQuantity(q);

		updateObservers();
	}

	public void updateDeleted() {
		for (ItemObserver o : observers) {
			try {
				o.modelDeleted2();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	private void updateObservers() {
		for (ItemObserver o : observers) {
			try {
				o.updateObserver2(this);
			} catch (Exception e) {
				// ignore
			}
		}
	}
}