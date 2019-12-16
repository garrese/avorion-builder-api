package avuilder4j.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import avuilder4j.design.BlockPlan;
import avuilder4j.design.StructurePlan;
import avuilder4j.design.sub.dimensional.Orientation;
import avuilder4j.error.Avuilder4jException;

public class DesignImporter {

	protected String exportRoute = "ships/";

	public StructurePlan export(String shipName) throws Avuilder4jException {

		StructurePlan s = new StructurePlan();
		try {
			File fXmlFile = new File(exportRoute + shipName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			Element design = (Element) doc.getElementsByTagName("ship_design").item(0);
			Element plan = (Element) design.getElementsByTagName("plan").item(0);

			NodeList items = plan.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element) items.item(i);
				Element block = (Element) item.getElementsByTagName("block").item(0);

				BlockPlan b = new BlockPlan();
				b.setIndexInStructure(getIntegerAtt(item, "index"));
				b.getAxisX().setLowerEnd(getDoubleAttRounded(block, "lx"));
				b.getAxisX().setUpperEnd(getDoubleAttRounded(block, "ux"));
				b.getAxisY().setLowerEnd(getDoubleAttRounded(block, "ly"));
				b.getAxisY().setUpperEnd(getDoubleAttRounded(block, "uy"));
				b.getAxisZ().setLowerEnd(getDoubleAttRounded(block, "lz"));
				b.getAxisZ().setUpperEnd(getDoubleAttRounded(block, "uz"));
				b.setTypeIndex(getIntegerAtt(block, "index"));
				b.setMaterialIndex(getIntegerAtt(block, "material"));
				b.setOrientation(new Orientation(getIntegerAtt(block, "look"), getIntegerAtt(block, "up")));
				b.setColor(block.getAttribute("color"));
				s.add(b);
			}

			for (int i = 0; i < items.getLength(); i++) {
				Element item = (Element) items.item(i);

				Integer index = getIntegerAtt(item, "index");
				Integer parent = getIntegerAtt(item, "parent");
				s.findByIndex(index).setParent(s.findByIndex(parent));
			}
		} catch (Exception e) {
			throw new Avuilder4jException(e);
		}

		return s;
	}

	public Integer getIntegerAtt(Element e, String att) {
		String value = e.getAttribute(att);
		return Integer.valueOf(value);
	}

	public Double getDoubleAttRounded(Element e, String att) {
		String value = e.getAttribute(att);
		return Math.round(Double.valueOf(value) * 1000) / 1000d;
	}

}
