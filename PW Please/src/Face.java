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
		faces.put("( ͡° ͜ʖ ͡°)","3,9");
		faces.put("٩(⁎❛ᴗ❛⁎)۶","3,5");
		faces.put("(ノಠ益ಠ)ノ","2,4");
		faces.put("(ಠ⌣ಠ)","1,3");
		faces.put("(✿◠‿◠)","2,4");
		faces.put("(▰˘◡˘▰)","2,4");
		faces.put("( ͒ ۝ ͒ )","3,7");
		faces.put("̿̿ ̿̿ ̿̿ ̿'̿'\\̵͇̿̿\\з= ( ▀ ͜͞ʖ▀) =ε/̵͇̿̿/’̿’̿ ̿ ̿̿ ̿̿ ̿̿","24,29");
		faces.put("ᕦ(⪧︹⪦)ᕤ","2,4");
		faces.put("¯\\_(⊙︿⊙)_/¯","4,6");
		faces.put("(ಥ﹏ಥ)","1,3");
		faces.put("(́◉◞౪◟◉‵)","2,6");
		faces.put("【•】_【•】","1,5");
		faces.put("(◡‿◡✿)","1,3");
		faces.put("(✖╭╮✖)","1,4");
		faces.put("(︶ω︶)","1,3");
		faces.put("(づ｡◕‿‿◕｡)づ","3,6");
		faces.put("(◣_◢)","1,3");
		faces.put("(✪㉨✪)","1,3");
		faces.put("(✌ﾟ∀ﾟ)☞","2,4");
		constructFaceSets();
	}
	
	private void constructFaceSets() {
		for (Entry<String, String> entry : this.faces.entrySet()) {
			String face = entry.getKey();
			String[] pairToAdd = new String[3];
			pairToAdd[0] = String.valueOf(face);
			String[] eyeIndices = entry.getValue().split(",");
			for (int i = 0; i < eyeIndices.length; i++) {
				face = face.substring(0, Integer.parseInt(eyeIndices[i])) + "o" + face.substring(Integer.parseInt(eyeIndices[i]) + 1);
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
		int itemToBeRemoved = rand.nextInt(faceSets.size());
		facePair = faceSets.remove(itemToBeRemoved);
		return facePair;
	}
	
	//For debugging
	private void printAll() {
	    for (Entry<String, String> entry : this.faces.entrySet()) {
	        System.out.println(entry.getKey());
	    }
	}
	
	//For debugging also
	private void printAllWithWrongEyes() {
		for (Entry<String, String> entry : this.faces.entrySet()) {
			String face = entry.getKey();
			String[] eyeIndices = entry.getValue().split(",");
			for (int i = 0; i < eyeIndices.length; i++) {
				face = face.substring(0, Integer.parseInt(eyeIndices[i])) + "o" + face.substring(Integer.parseInt(eyeIndices[i]) + 1);
			}
			System.out.println(face);
		}
	}
}
