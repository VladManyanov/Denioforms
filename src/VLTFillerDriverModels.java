import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class VLTFillerDriverModels {

	private static final String ls = System.lineSeparator();
	//
	private static final String driverDefaultPrefix = "_DRIVER";
	private static final String driverFemalePrefix = "_FEMALE_DRIVER";
	private static final String driverIconPrefix = "PART_DRIVER_";
	private static final String driverTitlePrefix = "GM_CATALOG_WEV2_DRIVER_";
	private static final String driverVisualPartSubType = "vpart_driver";
	//
	private static final String driverMale = "MALE";
	private static final String driverFemale = "FEMALE";
	private static final String nodeBrand = "visual_brand_nfs";
	//
	private VLTFiller vltFiller = new VLTFiller();
	
	public void makeVLTDriversPatch() throws IOException {
		List<String> carNameList = Files.readAllLines(new File("OriginalCarsList.txt").toPath(), StandardCharsets.UTF_8);
		List<String> vltOutput = new ArrayList<>();
	    for (String carName : carNameList) {
	    	vltOutput.add("## " + carName);
	    	vltOutput.add(prepareVLTDriverPatchBlock(
	    			carName, driverIconPrefix, driverTitlePrefix, driverDefaultPrefix, driverMale));
	    	vltOutput.add(prepareVLTDriverPatchBlock(
	    			carName, driverIconPrefix, driverTitlePrefix, driverFemalePrefix, driverFemale));
	    }
	    Files.write(Paths.get("VLTDriversPatch.txt"), vltOutput, StandardCharsets.UTF_8);
	}
	
	private String prepareVLTDriverPatchBlock(String carName, String partIconPrefix, String titlePrefix, String nodeName, String modelName) {
		StringBuilder vltBlock = new StringBuilder();
		String fullNodeName = carName + nodeName;
		String fullIcon = partIconPrefix + modelName;
		String fullTitle = titlePrefix + modelName;
		String vltNodeHash = vltFiller.getVLTNodeHash(fullNodeName);
		
		vltBlock.append(String.format(
				"update_field virtualitem %s icon %s", vltNodeHash, fullIcon) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s subType %s", vltNodeHash, driverVisualPartSubType) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s title %s", vltNodeHash, fullTitle) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s brand %s", vltNodeHash, nodeBrand) + ls);
		
		return vltBlock.toString();
	}

}
