package avuilder4j.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import avuilder4j.design.base.BlockPlanInterfaceExporter;
import avuilder4j.error.Avuilder4jException;

public class DesignExporter {

	public static final int ROOT_PARENT = -1;

	protected String exportRoute = "ships/";

	public void exportDesign(List<? extends BlockPlanInterfaceExporter> blocks, String shipName) throws Avuilder4jException {

		if (shipName == null || shipName.equals("")) {
			throw new IllegalArgumentException("Ship's name can't be empty or null");
		}

		try {
			validateBlockPlanList(blocks);

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root
			Document doc = docBuilder.newDocument();
			Element shipDesignE = doc.createElement("ship_design");
			doc.appendChild(shipDesignE);

			Element planE = doc.createElement("plan");
			shipDesignE.appendChild(planE);

			// ship atts
			addAttribute(doc, planE, "accumulateHealth", "true");
			addAttribute(doc, planE, "convex", "false");

			// ( item > block )*
			for (BlockPlanInterfaceExporter block : blocks) {

				// item
				Element itemE = doc.createElement("item");
				planE.appendChild(itemE);

				int parentIndex = ROOT_PARENT;
				if (block.getParentIndex() != null) {
					parentIndex = block.getParentIndex();
				}
				addAttribute(doc, itemE, "parent", String.valueOf(parentIndex));

				addAttribute(doc, itemE, "index", String.valueOf(block.getIndex()));

				// block
				Element blockE = doc.createElement("block");
				itemE.appendChild(blockE);

				String lx = String.valueOf(block.getXL());
				String ly = String.valueOf(block.getYL());
				String lz = String.valueOf(block.getZL());
				String ux = String.valueOf(block.getXU());
				String uy = String.valueOf(block.getYU());
				String uz = String.valueOf(block.getZU());
				addAttribute(doc, blockE, "lx", lx);
				addAttribute(doc, blockE, "ly", ly);
				addAttribute(doc, blockE, "lz", lz);
				addAttribute(doc, blockE, "ux", ux);
				addAttribute(doc, blockE, "uy", uy);
				addAttribute(doc, blockE, "uz", uz);

				addAttribute(doc, blockE, "index", String.valueOf(block.getTypeIndex()));

				addAttribute(doc, blockE, "material", String.valueOf(block.getMaterialIndex()));

				addAttribute(doc, blockE, "look", String.valueOf(block.getLook()));

				addAttribute(doc, blockE, "up", String.valueOf(block.getUp()));

				addAttribute(doc, blockE, "color", block.getColor().toLowerCase());

			}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(exportRoute + shipName + ".xml"));
			transformer.transform(source, result);

		} catch (Exception e) {
			throw new Avuilder4jException("Error exporting ship design.", e);
		}
	}

	private static void addAttribute(Document doc, Element element, String attributeName, String attributeValue) {
		Attr materialIndexAtt = doc.createAttribute(attributeName);
		materialIndexAtt.setValue(attributeValue);
		element.setAttributeNode(materialIndexAtt);
	}

	public static void validateBlockPlanList(List<? extends BlockPlanInterfaceExporter> blocks) throws Avuilder4jException {

		ArrayList<BlockPlanInterfaceExporter> roots = new ArrayList<BlockPlanInterfaceExporter>();
		for (BlockPlanInterfaceExporter block : blocks) {
			block.validateBlockPlan();
			if (block.getParentIndex() == null || block.getParentIndex().equals(-1)) {
				roots.add(block);
				if (roots.size() > 1) {
					throw new Avuilder4jException("Can not be more than one root block. roots= " + roots);
				}
			}
		}
		if (roots.size() == 0) {
			throw new Avuilder4jException("Must be one root block.");
		}
	}

	public String getExportRoute() { return exportRoute; }

	public void setExportRoute(String exportRoute) { this.exportRoute = exportRoute; }

}
