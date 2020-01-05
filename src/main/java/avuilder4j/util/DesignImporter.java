package avuilder4j.util;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import avuilder4j.design.base.BlockInterfaceImporter;
import avuilder4j.error.Avuilder4jException;

public class DesignImporter {

	protected String importRoute = "ships/";

	public <B extends BlockInterfaceImporter<B>> List<B> importDesign(String shipName, Supplier<B> supplier) throws Avuilder4jException {

		HashMap<Integer, B> blocksMap = new LinkedHashMap<>();
		HashMap<Integer, Integer> parentsMap = new HashMap<>();

		try {
			File fXmlFile = new File(importRoute + shipName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			Element design = (Element) doc.getElementsByTagName("ship_design").item(0);
			Element plan = (Element) design.getElementsByTagName("plan").item(0);

			NodeList items = plan.getElementsByTagName("item");
			for (int i = 0; i < items.getLength(); i++) {

				Element item = (Element) items.item(i);
				Integer index = getIntegerAtt(item, "index");
				Integer parentIndex = getIntegerAtt(item, "parent");

				Element xmlBlock = (Element) item.getElementsByTagName("block").item(0);
				B importer = supplier.get();

				importer.setIndex(index);
				importer.setXL(getDoubleAttRounded(xmlBlock, "lx"));
				importer.setXU(getDoubleAttRounded(xmlBlock, "ux"));
				importer.setYL(getDoubleAttRounded(xmlBlock, "ly"));
				importer.setYU(getDoubleAttRounded(xmlBlock, "uy"));
				importer.setZL(getDoubleAttRounded(xmlBlock, "lz"));
				importer.setZU(getDoubleAttRounded(xmlBlock, "uz"));
				importer.setTypeIndex(getIntegerAtt(xmlBlock, "index"));
				importer.setMaterialIndex(getIntegerAtt(xmlBlock, "material"));
				importer.setLook(getIntegerAtt(xmlBlock, "look"));
				importer.setUp(getIntegerAtt(xmlBlock, "up"));
				importer.setColor(xmlBlock.getAttribute("color"));

				parentsMap.put(index, parentIndex);
				blocksMap.put(index, importer);
			}

			for (Map.Entry<Integer, Integer> parentRegistry : parentsMap.entrySet()) {

				B block = blocksMap.get(parentRegistry.getKey());
				B parent = blocksMap.get(parentRegistry.getValue());
				block.setParent(parent);
			}

		} catch (Exception e) {
			throw new Avuilder4jException(e);
		}

		return blocksMap.values().stream().collect(Collectors.toList());
	}

	public Integer getIntegerAtt(Element e, String att) {
		String value = e.getAttribute(att);
		return Integer.valueOf(value);
	}

	public Double getDoubleAttRounded(Element e, String att) {
		String value = e.getAttribute(att);
		return Math.round(Double.valueOf(value) * 1000) / 1000d;
	}

	public String getImportRoute() { return importRoute; }

	public void setImportRoute(String importRoute) { this.importRoute = importRoute; }

}
