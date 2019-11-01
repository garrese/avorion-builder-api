package avuilder4j.design;

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

import avuilder4j.entities.game.Block;
import avuilder4j.error.Avuilder4jException;

public class DesignExporter {

	protected String exportRoute = "";

	public void export(List<Block> blocks, String shipName) throws Avuilder4jException {
		try {

			if (shipName == null || shipName.equals("")) {
				throw new IllegalArgumentException("Ship's name can't be empty or null");
			}

			ArrayList<Block> roots = new ArrayList<Block>();
			for (Block block : blocks) {
				block.validateBlock();
				if (block.getParent() == null) {
					if (roots.size() > 0) {
						throw new Avuilder4jException("Can not be more than one root block. roots= " + roots);
					}
					roots.add(block);
				}
			}
			if (roots.size() == 0) {
				throw new Avuilder4jException("Must be one root block.");
			}

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
			for (Block block : blocks) {

				// item
				Element itemE = doc.createElement("item");
				planE.appendChild(itemE);

				String parentIndex = "-1";
				if (block.getParent() != null) {
					parentIndex = String.valueOf(block.getParent().getIndex());
				}
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

				String look = String.valueOf(block.getTypeLook().getLook());
				addAttribute(doc, blockE, "look", look);

				String up = String.valueOf(block.getTypeLook().getUp());
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
			throw new Avuilder4jException("Error exporting ship.", e);
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
