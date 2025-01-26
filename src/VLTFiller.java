import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import util.JsonDataClasses.*;
import util.PartType;

public class VLTFiller {

	private static final String ls = System.lineSeparator();
	private static final String noTitle = "%default%";
	//
	private static final String kitPrefix = "_INTEGRATED_KIT";
	private static final String hoodPrefix = "_HOOD";
	private static final String spoilerPrefix = "_SPOILER";
	private static final String lplatePrefix = "LICENSE_PLATE_";
	private static final String neonPrefix = "CARNEONGLOW_";
	private static final String driverPrefix = "_DRIVER";
	//
	private static final String headlightsPrefix = "_HEADLIGHTS";
	private static final String brakelightsPrefix = "_BRAKELIGHTS";
	private static final String sideMirrorsPrefix = "_SIDE_MIRRORS";
	private static final String roofPrefix = "_ROOF";
	private static final String roofScoopPrefix = "_ROOFSCOOP";
	private static final String trunkPrefix = "_TRUNK";
	private static final String extraPrefix = "_EXTRA";
	private static final String wingsPrefix = "_WINGS";
	private static final String interiorPrefix = "_INTERIOR";
	private static final String grillePrefix = "_GRILLE";
	//
	private static JenkinsHash jhash = new JenkinsHash();
	private SettingsObject settings = new SettingsObject();
	
	public void makeVLTPartBlocks() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("PartsList.json"), StandardCharsets.UTF_8);
		List<PartsListObject> jsonObjList = new Gson().fromJson(reader, new TypeToken<List<PartsListObject>>() {}.getType());
	    reader.close();
	    loadSettingsJson();
	    
	    List<String> vltOutput = new ArrayList<>();
	    for (PartsListObject partObj : jsonObjList) {
	    	partObj.setPartType(partObj.getPartType().toUpperCase());
	    	getPartTypeFillRestrictions(partObj.getPartTypeEnum(), partObj);
	    	switch(partObj.getPartTypeEnum()) {
	    	case BODYKIT:
	    		vltOutput.add(fetchBodykitPart(partObj)); break;
	    	case HOOD:
	    		vltOutput.add(fetchHoodPart(partObj)); break;	    		
	    	case SPOILER:
	    		vltOutput.add(fetchSpoilerPart(partObj)); break;
	    	case WHEELS:
	    		vltOutput.add(fetchWheelsPart(partObj)); break;
	    	case NEON:
	    		vltOutput.add(fetchNeonPart(partObj)); break;
	    	case LICENSEPLATE:
	    		vltOutput.add(fetchLicensePlatePart(partObj)); break;
	    	case DRIVER:
	    		vltOutput.add(fetchDriverPart(partObj)); break;
	    	// Specific World Evolved part types
	    	case HEADLIGHTS:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, headlightsPrefix, "PART_HEADLIGHTS")); break;
	    	case BRAKELIGHTS:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, brakelightsPrefix, "PART_BRAKELIGHTS")); break;
	    	case SIDEMIRRORS:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, sideMirrorsPrefix, "PART_SIDEMIRRORS")); break;
	    	case ROOF:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, roofPrefix, "PART_ROOF")); break;
	    	case ROOFSCOOP:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, roofScoopPrefix, "PART_ROOFSCOOP")); break;
	    	case TRUNK:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, trunkPrefix, "PART_TRUNK")); break;
	    	case EXTRA:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, extraPrefix, "PART_EXTRA")); break;
	    	case WINGS:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, wingsPrefix, "PART_WINGS")); break;
	    	case INTERIOR:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, interiorPrefix, "PART_INTERIOR")); break;
	    	case GRILLE:
	    		vltOutput.add(fetchVisualPartGeneric(partObj, grillePrefix, "PART_GRILLE")); break;
	    	}
	    }
	    Files.write(Paths.get("VLTNodes.txt"), vltOutput, StandardCharsets.UTF_8);
	}
	
	public void loadSettingsJson() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("Settings.json"), StandardCharsets.UTF_8);
		settings = new Gson().fromJson(reader, new TypeToken<SettingsObject>() {}.getType());
	    reader.close();
	}
	
	private void getPartTypeFillRestrictions(PartType partType, PartsListObject partObj) {
		PartProperiesObject rulesObj = new PartProperiesObject();
		switch(partType) {
		case BODYKIT: case HOOD: case SPOILER: case DRIVER: 
			rulesObj.setAllowBaseCarHash(true);
			rulesObj.setAllowBrand(false);
			rulesObj.setAllowShortDesc(true);
			rulesObj.setAllowSpecialTitle(false); break;
    	case WHEELS:
    		rulesObj.setAllowBaseCarHash(false);
    		rulesObj.setAllowBrand(true);
    		rulesObj.setAllowShortDesc(false);
    		rulesObj.setAllowSpecialTitle(false); break;
    	case NEON: case LICENSEPLATE:
    		rulesObj.setAllowBaseCarHash(false);
    		rulesObj.setAllowBrand(false);
    		rulesObj.setAllowShortDesc(false);
    		rulesObj.setAllowSpecialTitle(false); break;
    	// Specific World Evolved part types
    	case HEADLIGHTS: case BRAKELIGHTS: case SIDEMIRRORS: case ROOF: case TRUNK: case EXTRA:
    	case WINGS: case INTERIOR: case ROOFSCOOP: case GRILLE:
    		rulesObj.setAllowBaseCarHash(true);
			rulesObj.setAllowBrand(false);
			rulesObj.setAllowShortDesc(true);
			rulesObj.setAllowSpecialTitle(true); break;
		}
		partObj.setRules(rulesObj);
	}
	
	//
	
	private String fetchBodykitPart(PartsListObject partObj) {
		String partFullName = 
				(partObj.getCarModel() + "_" + partObj.getPartName() + kitPrefix).toUpperCase();
		String partIcon = partObj.getPartName().startsWith("KITW") ? "VP_BST3_WOH" : "VP_BST2_WOH";
		if (partObj.isPoliceKit()) {partIcon = "VP_BK_COP";}

		return prepareVLTBlock(partObj, settings.getKitDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchHoodPart(PartsListObject partObj) {
		String isCarbon = partObj.isCarbonPart() ? "_CF" : "";
		String partFullName = 
				(partObj.getCarModel() + "_" + partObj.getPartName() + hoodPrefix + isCarbon).toUpperCase();
		String partIcon = isCarbon.isEmpty() ? "PART_HOOD" : "PART_HOOD_CF";	
		
		return prepareVLTBlock(partObj, settings.getHoodDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchSpoilerPart(PartsListObject partObj) {
		String isCarbon = partObj.isCarbonPart() ? "_CF" : "";
		String partFullName = 
				(partObj.getCarModel() + "_" + partObj.getPartName() + spoilerPrefix).toUpperCase();
		String partIcon = isCarbon.isEmpty() ? "PART_SPOILER" : "PART_SPOILER_CF";
			
		return prepareVLTBlock(partObj, settings.getSpoilerDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchWheelsPart(PartsListObject partObj) {
		StringBuilder vltBlocks = new StringBuilder();
		int i = 0;
		for (String wheelSize : partObj.getSizesArray()) {
			String partFullName = 
					(partObj.getBrand() + "_" + partObj.getPartName() + "_" + wheelSize + "_25").toUpperCase();
			String partIcon = getWheelsIcon(partObj, wheelSize);			
			
			vltBlocks.append(prepareVLTBlock(partObj, settings.getWheelsDefaultNode(), partIcon, partFullName));
			i++;
			if (i < partObj.getSizesArray().length) {
				vltBlocks.append(ls);
			}
		}		
		return vltBlocks.toString();
	}
	
	private String fetchLicensePlatePart(PartsListObject partObj) {
		String partFullName = 
				(lplatePrefix + partObj.getPartName()).toUpperCase();
		String partIcon = partObj.getIcon().contentEquals(noTitle) ? "PART_CCX_LP" : partObj.getIcon();
		
		return prepareVLTBlock(partObj, settings.getLPlateDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchNeonPart(PartsListObject partObj) {
		String partFullName = (neonPrefix + partObj.getPartName()).toUpperCase();
		String partIcon = partObj.getIcon().contentEquals(noTitle) ? "PART_R3_NE_BLUE" : partObj.getIcon();
		
		return prepareVLTBlock(partObj, settings.getNeonDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchDriverPart(PartsListObject partObj) {
		String partFullName = partObj.getPartName().isEmpty() 
				? (partObj.getCarModel() + driverPrefix).toUpperCase()
				: (partObj.getCarModel() + "_" + partObj.getPartName() + driverPrefix).toUpperCase();
		String partIcon = partObj.getIcon().contentEquals(noTitle) ? "BADGE13" : partObj.getIcon();
		
		return prepareVLTBlock(partObj, settings.getDriverDefaultNode(), partIcon, partFullName);
	}
	
	private String fetchVisualPartGeneric(PartsListObject partObj, String partPrefix, String partIcon) {
		String partFullName = partObj.getPartName().isEmpty() 
				? (partObj.getCarModel() + partPrefix).toUpperCase()
				: (partObj.getCarModel() + "_" + partObj.getPartName() + partPrefix).toUpperCase();
		
		return prepareVLTBlock(partObj, settings.getExtraSlotDefaultNode(), partIcon, partFullName);
	}
	
	//
	
	private String prepareVLTBlock(PartsListObject partObj, String typeDefaultNode,
			String partIcon, String partFullName) {
		StringBuilder vltBlock = new StringBuilder();
		long partHash = getUnsignedBinMemHash(partFullName);
		long baseCarHash = getBaseCarHash(partObj);
		String wheelsBrand = getWheelsBrandString(partObj);
		
		vltBlock.append(String.format(
				"copy_node virtualitem %s %s", typeDefaultNode, partFullName) + ls);
		vltBlock.append(String.format(
				"copy_node visualpart %s %s", typeDefaultNode, partFullName) + ls);
		if (partObj.getRules().isAllowBrand()) {
			vltBlock.append(String.format(
					"update_field virtualitem %s brand %s", partFullName, wheelsBrand) + ls);
		}
		vltBlock.append(String.format(
				"update_field virtualitem %s hash %s", partFullName, partHash) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s itemName %s", partFullName, partFullName) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s shortdescription %s", partFullName, getShortDescTitle(partObj)) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s icon %s", partFullName, partIcon) + ls);
		vltBlock.append(String.format(
				"update_field virtualitem %s title %s", partFullName, getPartTitle(partObj)) + ls);
		vltBlock.append(String.format(
				"update_field visualpart %s visualPartHash %s", partFullName, partHash) + ls);
		if (partObj.getRules().isAllowBaseCarHash()) {
			vltBlock.append(String.format(
					"update_field visualpart %s baseCarHashes[0] %s", partFullName, baseCarHash) + ls);
		}
		vltBlock.append(String.format(
				"rename_node virtualitem %s %s", partFullName, getVLTNodeHash(partFullName)) + ls);
		vltBlock.append(String.format(
				"rename_node visualpart %s %s", partFullName, getVLTNodeHash(partFullName)) + ls);
		
		return vltBlock.toString();
	}
	
	//
	
	private long getBaseCarHash(PartsListObject partObj) {
		return partObj.getRules().isAllowBaseCarHash() ? 
				getUnsignedBinMemHash(partObj.getCarModel()) : 0L;
	}
	
	private String getShortDescTitle(PartsListObject partObj) {
		if (!partObj.getRules().isAllowShortDesc()) {
			return settings.getDefaultCommonShortDesc();
		}
		return partObj.getShortDescTitle().contentEquals(noTitle) ? 
				String.format(settings.getDefaultShortDesc(), partObj.getCarModel()) : partObj.getShortDescTitle();
	}
	
	private String getPartTitle(PartsListObject partObj) {
		if (partObj.getRules().isAllowSpecialTitle() && partObj.getTitle().contentEquals(noTitle)) {
			String partNumber = !partObj.getPartName().isEmpty() ? 
					String.valueOf(Integer.parseInt(partObj.getPartName().substring(5))) : "";
			return getDefaultTitlePrefix(partObj.getPartTypeEnum()) + partNumber;
		}
		return partObj.getTitle().contentEquals(noTitle) ? partObj.getPartName() : partObj.getTitle();
	}
	
	private String getWheelsIcon(PartsListObject partObj, String wheelSize) {
		return partObj.getIcon().contentEquals(noTitle) ? 
				("PART_RI_" + partObj.getBrand() + "_" + Integer.parseInt(partObj.getPartName().substring(5)) + "_" + wheelSize) 
				: partObj.getIcon();
	}
	
	private String getWheelsBrandString(PartsListObject partObj) {
		return partObj.getRules().isAllowBrand() ? 
				("wheel_brand_" + partObj.getBrand()).toLowerCase() : "";
	}
	
	private String getDefaultTitlePrefix(PartType partType) {
		String title = "";
		switch(partType) {
		case HEADLIGHTS:
			title = settings.getDefaultLangTitlePrefix() + "HEADLIGHTS"; break;
		case BRAKELIGHTS:
			title = settings.getDefaultLangTitlePrefix() + "TAILLIGHTS"; break;
    	case SIDEMIRRORS:
    		title = settings.getDefaultLangTitlePrefix() + "MIRRORS"; break;
    	case ROOF:
    		title = settings.getDefaultLangTitlePrefix() + "ROOF"; break;
    	case ROOFSCOOP:
    		title = settings.getDefaultLangTitlePrefix() + "ROOFSCOOP"; break;
    	case TRUNK:
    		title = settings.getDefaultLangTitlePrefix() + "TRUNK"; break;
    	case EXTRA:
    		title = settings.getDefaultLangTitlePrefix() + "EXTRA"; break;
    	case WINGS:
    		title = settings.getDefaultLangTitlePrefix() + "WINGS"; break;
    	case INTERIOR:
    		title = settings.getDefaultLangTitlePrefix() + "INTERIOR"; break;
    	case GRILLE:
    		title = settings.getDefaultLangTitlePrefix() + "GRILLE"; break;
		default:
			break;
		}
		return title;
	}
	
	//
	
	public SettingsObject getSettings() {
		return settings;
	}
	
	// Utils
	
	public int calcBinMemoryHash(String carModel) {
		int hash = 0xFFFFFFFF;
		for (char c : carModel.toCharArray()) {
			hash = hash * 33 + (int) c;
		}
		return hash;
	}
	
	public String formatHexToString(int value) {
		return "0x" + String.format("%08X", value & 0xFFFFFFFF);
	}
	
	public long getUnsignedInt(int signed) {
	    return signed >= 0 ? signed : 2 * (long) Integer.MAX_VALUE + 2 + signed;
	}
	
	public long getUnsignedBinMemHash(String value) {
		return getUnsignedInt(calcBinMemoryHash(value));
	}
	
	public String getVLTNodeHash(String partFullName) {
		return formatHexToString( jhash.hash(
				formatHexToString(calcBinMemoryHash(partFullName)).toLowerCase() ) ).toLowerCase();
	}
	
}
