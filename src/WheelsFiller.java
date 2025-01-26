import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import util.JsonDataClasses.*;

public class WheelsFiller {
	
	private static final List<String> emptyList = new ArrayList<>();
	//
	private VLTFiller vltFiller = new VLTFiller();
	
	public void makeWheelsPatch() throws IOException {
		List<String> oldWheelsList = Files.readAllLines(new File("WheelModelsList.txt").toPath(), StandardCharsets.UTF_8);
		GlobalCCarPartsObject globalCFile = new GlobalCCarPartsObject();
		
		globalCFile.setName("WHEELSX"); // Insert your file ID here
		globalCFile.setPriority(1);
		List<GlobalCCarPartsEntry> wheelsParts = new ArrayList<>();
		
		vltFiller.loadSettingsJson();
		
	    for (String oldWheelModel : oldWheelsList) {
	    	String[] modelNameParams = oldWheelModel.split("_");
	    	
	    	GlobalCCarPartsEntry wheelObj = new GlobalCCarPartsEntry();
	    	wheelObj.setName(oldWheelModel);
	    	List<Object> attrs = new ArrayList<>(); // Very primitive way to avoid dealing with multiple object types of "Value" field, sorry
	    	String[] partWheelNames = getPartWheelName(modelNameParams);
	    	
	    	attrs.add(new GlobalCAttrsParamBool("STOCK", false, emptyList));
	    	attrs.add(new GlobalCAttrsParamStrings("LOD_CHARACTERS_OFFSET", new ArrayList<>(Arrays.asList("ABCD "))));
	    	attrs.add(new GlobalCAttrsParamDec("LANGUAGEHASH", 
	    			vltFiller.getUnsignedBinMemHash(partWheelNames[1]), emptyList));
	    	attrs.add(new GlobalCAttrsParamDec("LOD_NAME_PREFIX_SELECTOR", 2, emptyList));
	    	attrs.add(new GlobalCAttrsParamStrings("NAME_OFFSET", new ArrayList<>(Arrays.asList(partWheelNames[0]))));
	    	attrs.add(new GlobalCAttrsParamDec("PARTID_UPGRADE_GROUP", vltFiller.getSettings().getWheelsDefaultPartId(), emptyList));
	    	attrs.add(new GlobalCAttrsParamDec("LOD_NAME_PREFIX_NAMEHASH", 
	    			vltFiller.getUnsignedBinMemHash(modelNameParams[0]), emptyList));
	    	attrs.add(new GlobalCAttrsParamDec("OUTER_RADIUS", 25, emptyList));
	    	attrs.add(new GlobalCAttrsParamDec("MAX_LOD", vltFiller.getSettings().getWheelsDefaultMaxLOD(), emptyList));
	    	attrs.add(new GlobalCAttrsParamDec("INNER_RADIUS", 17, emptyList));
	    	attrs.add(new GlobalCAttrsParamStrings("LOD_BASE_NAME", getLODBaseName(modelNameParams)));
	    	
	    	wheelObj.setAttributes(attrs);
	    	wheelsParts.add(wheelObj);
	    }
	    globalCFile.setParts(wheelsParts);
	    Gson gson = new GsonBuilder().setPrettyPrinting().create();
	    String jsonOut = gson.toJson(globalCFile);
	    Files.write(Paths.get("WheelsPatch.json"), jsonOut.getBytes(StandardCharsets.UTF_8));
	}
	
	//
	
	private List<String> getLODBaseName(String[] modelNameParams) {
		return new ArrayList<>(Arrays.asList(modelNameParams[1], modelNameParams[2] + "_" + modelNameParams[3]));
	}
	
	private String[] getPartWheelName(String[] modelNameParams) {
		String wheelModelIdStr = modelNameParams[1].substring(5);
		String wheelModelId = Integer.parseInt(wheelModelIdStr) < 10 ? wheelModelIdStr.substring(1) : wheelModelIdStr;
		String[] partWheelNames = new String[2];
		partWheelNames[0] = "PART_WHEEL_" + modelNameParams[0] + "_" + wheelModelId + " " + modelNameParams[2] + " " + modelNameParams[3];
		partWheelNames[1] = "PART_WHEEL_" + modelNameParams[0] + "_" + wheelModelId + "_" + modelNameParams[2] + "_" + modelNameParams[3];
		return partWheelNames;
	}

}
