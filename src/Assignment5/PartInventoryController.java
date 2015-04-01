package Assignment5;

public class PartInventoryController {
	private PartInventory inv;
	
	public PartInventoryController(PartInventory i) {
		inv = i;
	}
	
	public Part getPartByIndex(int index) {
		if(index < inv.getNumParts())
			return (Part) inv.getParts().get(index);
		else 
			return null;
	}
	
	public void deletePart(Part p) {
		//delete p from inv
		inv.deletePart(p);
	}

	public int getNumParts() {
		return inv.getNumParts();
	}
	
	public Part addPart(PartView view, Part p, int id, String pNum, String pName, String v) {
		//if p is null then create a new Part
		//but first validate pName does not already exist
		if(p == null) {
			try {
				return inv.addPart(p, id, pNum, pName, v);
			} catch(IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		} else {
			try {
				
				p.setFields(id, pNum, pName, v);
				inv.updateObservers();
				return p;
			} catch(IllegalArgumentException ex) {
				view.showError(ex.getMessage());
			}
		}
		return null;
	}
}
