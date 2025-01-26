import java.io.IOException;

public class main {

	private static String about = "Denioforms by Hypercycle, v1.5"
			+ "\nHelping tool to fill some VLT & GlobalC nodes for various NFS World cars. Approved by World Evolved!";
	
	public static void main(String[] args) throws IOException {
		System.out.println(about);
		if (args.length > 0 && args[0].contentEquals("-driverModelVLTPatch")) {
			VLTFillerDriverModels vltFillerDrivers = new VLTFillerDriverModels();
			vltFillerDrivers.makeVLTDriversPatch();
		}
		if (args.length > 0 && args[0].contentEquals("-wheelsPatch")) {
			WheelsFiller wheelsFiller = new WheelsFiller();
			wheelsFiller.makeWheelsPatch();
		} else {
			VLTFiller vltFiller = new VLTFiller();
			vltFiller.makeVLTPartBlocks();
		}
		
	}
}
