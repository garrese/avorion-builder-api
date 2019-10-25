package avuilder4j.managers;

import java.io.File;
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

import avuilder4j.entities.game.Block;
import avuilder4j.error.AvuilderCoreException;

public class Exporter {

	protected String exportRoute = "";

	public void export(List<Block> blocks, String shipName) throws AvuilderCoreException {
		try {

			if (shipName == null || shipName.equals("")) {
				throw new IllegalArgumentException("Ship's name can't be empty or null");
			}
			for (Block block : blocks) {
				block.validateBlock();
			}

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root
			Document doc = docBuilder.newDocument();
			Element shipE = doc.createElement("ship");
			doc.appendChild(shipE);

			// ship atts
			addAttribute(doc, shipE, "accumulateHealth", "true");
			addAttribute(doc, shipE, "convex", "false");

			// ( item > block )*
			for (Block block : blocks) {

				// item
				Element itemE = doc.createElement("item");
				shipE.appendChild(itemE);

				String parentIndex = String.valueOf(block.getParent().getIndex());
				addAttribute(doc, itemE, "parent", parentIndex);

				String blockIndex = String.valueOf(block.getIndex());
				addAttribute(doc, itemE, "index", blockIndex);

				// block
				Element blockE = doc.createElement("block");
				itemE.appendChild(blockE);

				String lx = String.valueOf(block.getAxisX().getLowerEnd());
				String ly = String.valueOf(block.getAxisY().getLowerEnd());
				String lz = String.valueOf(block.getAxisZ().getLowerEnd());
				String ux = String.valueOf(block.getAxisX().getUpperEnd());
				String uy = String.valueOf(block.getAxisY().getUpperEnd());
				String uz = String.valueOf(block.getAxisZ().getUpperEnd());
				addAttribute(doc, blockE, "lx", lx);
				addAttribute(doc, blockE, "ly", ly);
				addAttribute(doc, blockE, "lz", lz);
				addAttribute(doc, blockE, "ux", ux);
				addAttribute(doc, blockE, "uy", uy);
				addAttribute(doc, blockE, "uz", uz);

				String typeIndex = String.valueOf(block.getType());
				addAttribute(doc, blockE, "index", typeIndex);

				String materialIndex = String.valueOf(block.getMaterial());
				addAttribute(doc, blockE, "material", materialIndex);

				String look = String.valueOf(block.getOrientation().getLook());
				addAttribute(doc, blockE, "look", look);

				String up = String.valueOf(block.getOrientation().getUp());
				addAttribute(doc, blockE, "up", up);

				String color = block.getColor();
				addAttribute(doc, blockE, "color", color.toLowerCase());

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
			throw new AvuilderCoreException("Error exporting ship");
		}
	}

	private static void addAttribute(Document doc, Element element, String attributeName, String attributeValue) {
		Attr materialIndexAtt = doc.createAttribute(attributeName);
		materialIndexAtt.setValue(attributeValue);
		element.setAttributeNode(materialIndexAtt);
	}

	public String getExportRoute() {
		return exportRoute;
	}

	public void setExportRoute(String exportRoute) {
		this.exportRoute = exportRoute;
	}

}
