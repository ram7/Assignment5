package Assignment5;

import java.util.ArrayList;

public class PartInventory {
	private ArrayList<Part> parts;
	private ArrayList<PartInventoryObserver> observers;
	
	public PartInventory() {
		parts = new ArrayList<Part>();
		observers = new ArrayList<PartInventoryObserver>();
	}
	
	public void deletePart(Part p) {
		parts.remove(p);
		updateObservers();
		p.updateDeleted();//signal observing views that part has been deleted
	}
	
	public ArrayList<Part> getParts() {
		return parts;
	}
	
	public int getNumParts() {
		return parts.size();
	}
	
	public boolean partNumberExists(String pNum, Part part) {
		for(Part p : parts) {
			if(pNum.equalsIgnoreCase(p.getPartNumber()))
				return true;
		}
		return false;
	}
	

	
	public Part addPart(Part part, int id, String pNum, String pName, String v) throws IllegalArgumentException {
		if(partNumberExists(pNum, part))
			throw new IllegalArgumentException("Part Number Already Exists breh!");
	
		Part p = new Part(id, pNum, pName, v);
		parts.add(p);
		updateObservers();
		return p;
	}
	
	public void registerObserver(PartInventoryObserver o) {
		observers.add(o);
	}
	
	public void updateObservers() {
		for(PartInventoryObserver o : observers) {
			try {
				o.updateObserver(this);
			} catch(Exception e) {
				//ignore for now
			}
		}
	}
}

