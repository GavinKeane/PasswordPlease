import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.*;
import java.util.Random;

public class Face {
	private List<String[]> faceSets = new ArrayList<String[]>();
	private Map<String, String> faces = new HashMap<String, String>();
	private Map<String, String> facesWithDifferentEyes = new HashMap<String, String>();
	private String previousFace = ":^)";

	public Face() {
		replenish();
	}
	
	
	private void replenish() {
		faces.put("(O_O)", "0,2");
		faces.put("(o-o)", "0,2");
		faces.put("(O_o)", "0,2");
		faces.put("(o_O)", "0,2");
		faces.put("(o_o)", "0,2");
		faces.put("(O-O)", "0,2");
		faces.put("(>_<)", "1,3");
		faces.put("(~_~;)", "1,3");
		faces.put("(-_-;)", "1,3");
		faces.put("(^_^;)", "1,3");
		faces.put("(^_^)v", "1,3");
		faces.put("(;._.)", "2,4");
		faces.put("(~_~*)", "1,3");
		faces.put("(-_-*)", "1,3");
		faces.put("('_')", "1,3");
		faces.put("(T_T)", "1,3");
		faces.put("(Q_Q)", "1,3");
		faces.put("(ToT)", "1,3");
		faces.put("(^_-)", "1,3");
		faces.put("(T_T)", "1,3");
		faces.put("(=^.^=)", "2,4");
		faces.put("(@_@)", "1,3");
		faces.put("(# ^.^ #)", "3,5");
		constructFaceSets();
	}

	private void constructFaceSets() {
		for (Entry<String, String> entry : this.faces.entrySet()) {
			String face = entry.getKey();
			String[] pairToAdd = new String[3];
			pairToAdd[0] = String.valueOf(face);
			String[] eyeIndices = entry.getValue().split(",");
			for (int i = 0; i < eyeIndices.length; i++) {
				face = face.substring(0, Integer.parseInt(eyeIndices[i])) + "o"
						+ face.substring(Integer.parseInt(eyeIndices[i]) + 1);
			}
			pairToAdd[1] = String.valueOf(face);
			pairToAdd[2] = String.valueOf(this.previousFace);
			this.previousFace = String.valueOf(pairToAdd[0]);
			faceSets.add(pairToAdd);
			facesWithDifferentEyes.put(face, "");
		}
	}

	public String[] removeRemoveRandomFaceFromCollection() {
		String[] facePair = new String[3];
		Random rand = new Random();
		int itemToBeRemoved = 0;
		try {
			itemToBeRemoved = rand.nextInt(faceSets.size());
		} catch (Exception e) {
			replenish();
			itemToBeRemoved = rand.nextInt(faceSets.size());
		}
		facePair = faceSets.remove(itemToBeRemoved);
		return facePair;
	}

	// For debugging
	private void printAll() {
		for (Entry<String, String> entry : this.faces.entrySet()) {
			System.out.println(entry.getKey());
		}
	}

	// For debugging also
	private void printAllWithWrongEyes() {
		for (Entry<String, String> entry : this.faces.entrySet()) {
			String face = entry.getKey();
			String[] eyeIndices = entry.getValue().split(",");
			for (int i = 0; i < eyeIndices.length; i++) {
				face = face.substring(0, Integer.parseInt(eyeIndices[i])) + "o"
						+ face.substring(Integer.parseInt(eyeIndices[i]) + 1);
			}
			System.out.println(face);
		}
	}
}
