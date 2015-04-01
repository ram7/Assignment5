package Assignment5;

import java.util.ArrayList;

public class Part {
	private int id;
	private String partNumber;
	private String partName;
	private String vendor;


	private ArrayList<PartObserver> observers;
	private ItemPart other;

	
	public Part(int id, String pNum, String pName) {
		this(id, pNum, pName, "");
	}
	
	
	public Part(int idNum, String pNum, String pName, String v) {
	//	if (pNum == null || pNum.length() < 1)
	//		throw new IllegalArgumentException("Part # cannot be blank");
	//	if (pName == null || pName.length() < 1)
		//	throw new IllegalArgumentException("Part Name cannot be blank");
		
		id = idNum;
		partNumber = pNum;
		partName = pName;
		vendor = v;

		observers = new ArrayList<PartObserver>();
	}

	public int getPartId() {
		return id;
	}

	public void setPartId(int id) {
		this.id = id;
		updateObservers();
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
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



	public void registerObserver(PartObserver o) {
		observers.add(o);
	}
	


	public void setFields(int pId, String pNum, String pName, String v) {
		setPartNumber(pNum);
		setPartName(pName);
		setVendor(v);

		updateObservers();
	}
	

	public void updateDeleted() {
		for (PartObserver o : observers) {
			try {
				o.modelDeleted();
			} catch (Exception e) {
				// ignore
			}
		}
	}

	private void updateObservers() {
		for (PartObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch (Exception e) {
				// ignore
			}
		}
	}
	
}
