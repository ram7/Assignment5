package Assignment5;

public class ItemInventoryController {
	private ItemInventory inv;
	private Gateway g;
	
	public ItemInventoryController(ItemInventory i) {
		inv = i;
	}
	
	public ItemPart getPartByIndex(int index) {
		if(index < inv.getNumParts())
			return (ItemPart) inv.getParts().get(index);
		else 
			return null;
	}
	
	public void deletePart(ItemPart p) {
		//delete p from inv
		inv.deletePart(p);
	}

	public int getNumParts() {
		return inv.getNumParts();
	}
	
	public ItemPart addPart(ItemView view, ItemPart p, int id, String pName, int q, String v, Gateway othergateway) {
		//if p is null then create a new Part
		//but first validate pName does not already exist
		if(p == null) {
			try {
				System.out.println("in inv controller "+ id + " " + pName + " " + q );
				return inv.addPart(p, id, pName, q, v, othergateway);
			} catch(IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		} else {
			try {
				if(inv.partNameExists(pName, p)) {
					view.showError("Part Name already exists!");
					return null;
				}
				p.setFields(id, pName, q);
				inv.updateObservers();
				return p;
			} catch(IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		}
		return null;
	}
}
