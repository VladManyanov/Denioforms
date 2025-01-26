package util;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public final class JsonDataClasses {
	private JsonDataClasses() {}
	
	public static class PartsListObject {
		@SerializedName("CarModel")
		private String carModel; 
		@SerializedName("ShortDescTitle")
		private String shortDescTitle; 
		@SerializedName("PartName")
		private String partName; 
		@SerializedName("PartType")
		private String partType; 
		@SerializedName("Title")
		private String title;
		@SerializedName("Icon")
		private String icon;
		//
		@SerializedName("CarbonPart")
		private boolean carbonPart;
		@SerializedName("PoliceKit")
		private boolean policeKit;
		@SerializedName("Brand")
		private String brand;
		@SerializedName("Sizes")
		private String sizes;
		//
		private PartProperiesObject rules;

		public PartProperiesObject getRules() {
			return rules;
		}
		public void setRules(PartProperiesObject rules) {
			this.rules = rules;
		}
		
		public String getCarModel() {
			return carModel;
		}
		public void setCarModel(String carModel) {
			this.carModel = carModel;
		}
		
		public String getShortDescTitle() {
			return shortDescTitle;
		}
		public void setShortDescTitle(String shortDescTitle) {
			this.shortDescTitle = shortDescTitle;
		}
		
		public String getPartName() {
			return partName;
		}
		public void setPartName(String partName) {
			this.partName = partName;
		}
		
		public String getPartType() {
			return partType;
		}
		public void setPartType(String partType) {
			this.partType = partType;
		}
		public PartType getPartTypeEnum() {
			return PartType.valueOf(getPartType());
		}
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		} 
		
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		
		public boolean isCarbonPart() {
			return carbonPart;
		}
		public void setCarbonPart(boolean carbonPart) {
			this.carbonPart = carbonPart;
		}
		
		public boolean isPoliceKit() {
			return policeKit;
		}
		public void setPoliceKit(boolean policeKit) {
			this.policeKit = policeKit;
		}
		
		public String getBrand() {
			return brand;
		}
		public void setBrand(String brand) {
			this.brand = brand;
		}
		
		public String getSizes() {
			return sizes;
		}
		public String[] getSizesArray() {
			return sizes.split(",");
		}
		public void setSizes(String sizes) {
			this.sizes = sizes;
		}
	}
	
	public static class PartProperiesObject {
		private String partType; 
		private boolean allowBaseCarHash; 
		private boolean allowBrand;
		private boolean allowShortDesc;
		private boolean allowSpecialTitle;
		
		public String getPartType() {
			return partType;
		}
		public void setPartType(String partType) {
			this.partType = partType;
		}
		
		public boolean isAllowBaseCarHash() {
			return allowBaseCarHash;
		}
		public void setAllowBaseCarHash(boolean allowBaseCarHash) {
			this.allowBaseCarHash = allowBaseCarHash;
		}
		
		public boolean isAllowBrand() {
			return allowBrand;
		}
		public void setAllowBrand(boolean allowBrand) {
			this.allowBrand = allowBrand;
		}
		
		public boolean isAllowShortDesc() {
			return allowShortDesc;
		}
		public void setAllowShortDesc(boolean allowShortDesc) {
			this.allowShortDesc = allowShortDesc;
		} 
		
		public boolean isAllowSpecialTitle() {
			return allowSpecialTitle;
		}
		public void setAllowSpecialTitle(boolean allowSpecialTitle) {
			this.allowSpecialTitle = allowSpecialTitle;
		}
	}
	
	public static class SettingsObject {
		@SerializedName("DefaultShortDesc")
		private String defaultShortDesc; 
		@SerializedName("DefaultCommonShortDesc")
		private String defaultCommonShortDesc; 
		@SerializedName("DefaultLangTitlePrefix")
		private String defaultLangTitlePrefix; 
		@SerializedName("KitDefaultNode")
		private String kitDefaultNode; 
		@SerializedName("HoodDefaultNode")
		private String hoodDefaultNode; 
		@SerializedName("SpoilerDefaultNode")
		private String spoilerDefaultNode; 
		@SerializedName("WheelsDefaultNode")
		private String wheelsDefaultNode; 
		@SerializedName("LPlateDefaultNode")
		private String lplateDefaultNode; 
		@SerializedName("NeonDefaultNode")
		private String neonDefaultNode; 
		@SerializedName("DriverDefaultNode")
		private String driverDefaultNode; 
		@SerializedName("ExtraSlotDefaultNode")
		private String extraSlotDefaultNode;
		@SerializedName("WheelsDefaultPartId")
		private int wheelsDefaultPartId;
		@SerializedName("WheelsDefaultMaxLOD")
		private int wheelsDefaultMaxLOD;
		
		public String getDefaultShortDesc() {
			return defaultShortDesc;
		}
		public void setDefaultShortDesc(String defaultShortDesc) {
			this.defaultShortDesc = defaultShortDesc;
		}
		
		public String getDefaultCommonShortDesc() {
			return defaultCommonShortDesc;
		}
		public void setDefaultCommonShortDesc(String defaultCommonShortDesc) {
			this.defaultCommonShortDesc = defaultCommonShortDesc;
		}
		
		public String getDefaultLangTitlePrefix() {
			return defaultLangTitlePrefix;
		}
		public void setDefaultLangTitlePrefix(String defaultLangTitlePrefix) {
			this.defaultLangTitlePrefix = defaultLangTitlePrefix;
		}
		
		public String getKitDefaultNode() {
			return kitDefaultNode;
		}
		public void setKitDefaultNode(String kitDefaultNode) {
			this.kitDefaultNode = kitDefaultNode;
		}
		
		public String getHoodDefaultNode() {
			return hoodDefaultNode;
		}
		public void setHoodDefaultNode(String hoodDefaultNode) {
			this.hoodDefaultNode = hoodDefaultNode;
		}
		
		public String getSpoilerDefaultNode() {
			return spoilerDefaultNode;
		}
		public void setSpoilerDefaultNode(String spoilerDefaultNode) {
			this.spoilerDefaultNode = spoilerDefaultNode;
		}
		
		public String getWheelsDefaultNode() {
			return wheelsDefaultNode;
		}
		public void setWheelsDefaultNode(String wheelsDefaultNode) {
			this.wheelsDefaultNode = wheelsDefaultNode;
		}
		
		public String getLPlateDefaultNode() {
			return lplateDefaultNode;
		}
		public void setLPlateDefaultNode(String lplateDefaultNode) {
			this.lplateDefaultNode = lplateDefaultNode;
		}
		
		public String getNeonDefaultNode() {
			return neonDefaultNode;
		}
		public void setNeonDefaultNode(String neonDefaultNode) {
			this.neonDefaultNode = neonDefaultNode;
		}
		
		public String getDriverDefaultNode() {
			return driverDefaultNode;
		}
		public void setDriverDefaultNode(String driverDefaultNode) {
			this.driverDefaultNode = driverDefaultNode;
		}
		
		public String getExtraSlotDefaultNode() {
			return extraSlotDefaultNode;
		}
		public void setExtraSlotDefaultNode(String extraSlotDefaultNode) {
			this.extraSlotDefaultNode = extraSlotDefaultNode;
		}
		
		public int getWheelsDefaultPartId() {
			return wheelsDefaultPartId;
		}
		public void setWheelsDefaultPartId(int wheelsDefaultPartId) {
			this.wheelsDefaultPartId = wheelsDefaultPartId;
		}
		
		public int getWheelsDefaultMaxLOD() {
			return wheelsDefaultMaxLOD;
		}
		public void setWheelsDefaultMaxLOD(int wheelsDefaultMaxLOD) {
			this.wheelsDefaultMaxLOD = wheelsDefaultMaxLOD;
		} 
	}
	
	//
	
	public static class GlobalCCarPartsObject {
		@SerializedName("Name")
		private String name; 
		@SerializedName("Priority")
		private int priority; 
		@SerializedName("Parts")
		private List<GlobalCCarPartsEntry> parts;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public int getPriority() {
			return priority;
		}
		public void setPriority(int priority) {
			this.priority = priority;
		}
		
		public List<GlobalCCarPartsEntry> getParts() {
			return parts;
		}
		public void setParts(List<GlobalCCarPartsEntry> parts) {
			this.parts = parts;
		}
	}
	
	public static class GlobalCCarPartsEntry {
		@SerializedName("Name")
		private String name; 
		@SerializedName("Attributes")
		private List<Object> attributes;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public List<Object> getAttributes() {
			return attributes;
		}
		public void setAttributes(List<Object> attributes) {
			this.attributes = attributes;
		}
	}
	
	public static class GlobalCAttrsParamBool {
		@SerializedName("Name")
		private String name; 
		@SerializedName("Value")
		private boolean value; 
		@SerializedName("Strings")
		private List<String> strings;
		
		public GlobalCAttrsParamBool (String name, boolean value, List<String> strings) {
			this.name = name;
			this.value = value;
			this.strings = strings;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public boolean isValue() {
			return value;
		}
		public void setValue(boolean value) {
			this.value = value;
		}
		
		public List<String> getStrings() {
			return strings;
		}
		public void setStrings(List<String> strings) {
			this.strings = strings;
		}
	}
	
	public static class GlobalCAttrsParamStrings {
		@SerializedName("Name")
		private String name; 
		@SerializedName("Strings")
		private List<String> strings;
		
		public GlobalCAttrsParamStrings (String name, List<String> strings) {
			this.name = name;
			this.strings = strings;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public List<String> getStrings() {
			return strings;
		}
		public void setStrings(List<String> strings) {
			this.strings = strings;
		}
	}
	
	public static class GlobalCAttrsParamDec {
		@SerializedName("Name")
		private String name; 
		@SerializedName("Value")
		private long value; 
		@SerializedName("Strings")
		private List<String> strings;
		
		public GlobalCAttrsParamDec (String name, long value, List<String> strings) {
			this.name = name;
			this.value = value;
			this.strings = strings;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
		public long getValue() {
			return value;
		}
		public void setValue(long value) {
			this.value = value;
		}
		
		public List<String> getStrings() {
			return strings;
		}
		public void setStrings(List<String> strings) {
			this.strings = strings;
		}
	}
}
