package Assignment5;

import java.util.ArrayList;

public class ItemInventory {
	private ArrayList<ItemPart> parts;
	private ArrayList<ItemInventoryObserver> observers;

	public ItemInventory() {
		parts = new ArrayList<ItemPart>();
		observers = new ArrayList<ItemInventoryObserver>();
	}

	public void deletePart(ItemPart p) {
		parts.remove(p);
		updateObservers();
		p.updateDeleted();// signal observing views that part has been deleted
	}

	public ArrayList<ItemPart> getParts() {
		return parts;
	}

	public int getNumParts() {
		return parts.size();
	}

	public boolean partNameExists(String pName, ItemPart part) {
		for (ItemPart p : parts) {
			if (pName.equalsIgnoreCase(p.getPartName())
					&& (p != part || part == null))
				return true;
		}
		return false;
	}

	public ItemPart addPart(ItemPart part, int id, String pName, int q,
			String v, Gateway othergateway) throws IllegalArgumentException {
		if (partNameExists(pName, part))
			throw new IllegalArgumentException("Part Name already exists!");

		ItemPart p = new ItemPart(id, pName, q, v, othergateway);
		parts.add(p);

		updateObservers();
		return p;
	}

	public void registerObserver(ItemInventoryObserver o) {
		observers.add(o);
	}

	public void updateObservers() {
		for (ItemInventoryObserver o : observers) {
			try {
				o.updateObserver2(this);
			} catch (Exception e) {
				// ignore for now
			}
		}
	}
}
