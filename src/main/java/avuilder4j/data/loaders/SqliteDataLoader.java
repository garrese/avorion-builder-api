package avuilder4j.data.loaders;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanConstant;
import avuilder4j.data.beans.BeanCrew;
import avuilder4j.data.beans.BeanCrewCommand;
import avuilder4j.data.beans.BeanEffect;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.data.beans.BeanMaterialParams;
import avuilder4j.data.beans.BeanShape;
import avuilder4j.data.beans.BeanType;
import avuilder4j.data.beans.BeanTypeModel;
import avuilder4j.data.beans.BeanTypeModelByMaterial;
import avuilder4j.data.beans.BeanTypeModelByMaterial.MapIndex;
import avuilder4j.data.beans.BeanTypeModelParams;
import avuilder4j.error.AvuilderException;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.SqlFunction;

public class SqliteDataLoader extends DataLoader {

	protected String dbFilePath = "db/avorion-db.sqlite3";

	protected Connection getConnection() throws SQLException {
		Connection connection = null;
//		connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getClassLoader().getResource(dbFilePath)); // TODO limpiar
//		System.out.println(getClass().getClassLoader().getResource(dbFilePath));
		connection = DriverManager.getConnection("jdbc:sqlite:" + "src/main/resources/db/avorion-db.sqlite3");
		return connection;
	}

	public String getDbFilePath() { return dbFilePath; }

	public void setDbFilePath(String dbFilePath) { this.dbFilePath = dbFilePath; }

	public <K, V> Map<K, V> connectAndSelect(String select, SqlFunction<ResultSet, AbstractMap.SimpleEntry<K, V>> mapper) throws AvuilderException {
		Connection c = null;
		Map<K, V> map = new HashMap<>();
		ResultSet r = null;
		try {

			c = getConnection();
			PreparedStatement st = c.prepareStatement(select);
			r = st.executeQuery();

			while (r.next()) {
				AbstractMap.SimpleEntry<K, V> entry = mapper.run(r);
				map.put(entry.getKey(), entry.getValue());
			}

		} catch (Exception e) {
			throw new AvuilderException(e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new AvuilderException(e);
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getWrapper(ResultSet r, String columnName) throws SQLException {
		return r.getObject(columnName) != null ? (T) r.getObject(columnName) : null;
	}

	public static Boolean getBoolean(ResultSet r, String columnName) throws SQLException {
		Integer i = getWrapper(r, columnName);
		return Nullable.m(() -> i == 1 ? true : false, null);
	}

	@Override
	public void loadConstants() throws AvuilderException {
		DataMaps.setConstantsMap(connectAndSelect("Select * from Constant", (r) -> {
			String n = r.getString("name");
			String g = r.getString("category");
			Double nv = getWrapper(r, "value");

			BeanConstant k = new BeanConstant(n, g, nv);
			return new SimpleEntry<String, BeanConstant>(k.getName(), k);
		}));
	}

	@Override
	public void loadCrew() throws AvuilderException {
		DataMaps.setCrewMap(connectAndSelect("Select * from Crew", (r) -> {
			Integer idx = getWrapper(r, "idx");
			String name = r.getString("name");
			Double salary = getWrapper(r, "salary");
//
			BeanCrew b = new BeanCrew(idx, name, salary);
			return new SimpleEntry<Integer, BeanCrew>(b.getIndex(), b);
		}));
	}

	@Override
	public void loadCrewCommands() throws AvuilderException {
		DataMaps.setCrewCommandMap(connectAndSelect("Select * from CrewCommand", (r) -> {
			Integer commander = getWrapper(r, "commander");
			Integer commanded = getWrapper(r, "commanded");
			Double commandRatio = getWrapper(r, "commandRatio");

			BeanCrewCommand.MapIndex idx = new BeanCrewCommand.MapIndex(commander, commanded);
			BeanCrewCommand b = new BeanCrewCommand(idx, commandRatio);

			return new SimpleEntry<BeanCrewCommand.MapIndex, BeanCrewCommand>(b.getIndex(), b);
		}));
	}

	@Override
	public void loadEffects() throws AvuilderException {
		DataMaps.setTypeModelsByMaterialsEffectsMap(connectAndSelect("Select * from Effect", (r) -> {
			Integer typeModelIndex = getWrapper(r, "typeModelIdx");
			Integer materialIndex = getWrapper(r, "materialIdx");
			Integer n = getWrapper(r, "n");
			Double value = getWrapper(r, "value");
			BeanEffect.MapIndex idx = new BeanEffect.MapIndex(typeModelIndex, materialIndex, n);
			BeanEffect tmbm = new BeanEffect(idx, value);
			return new SimpleEntry<BeanEffect.MapIndex, BeanEffect>(tmbm.getIndex(), tmbm);
		}));
	}

	@Override
	public void loadMaterials() throws AvuilderException {
		DataMaps.setMaterialsMap(connectAndSelect("Select * from Material", (r) -> {
			BeanMaterialParams p = new BeanMaterialParams(getWrapper(r, "idx"));
			p.setName(r.getString("name"));
			p.setDensity(getWrapper(r, "density"));
			p.setDurability(getWrapper(r, "durability"));
			p.setCreditCost(getWrapper(r, "creditCost"));
			p.setMaterialCost(getWrapper(r, "materialCost"));
			BeanMaterial m = new BeanMaterial(p);
			return new AbstractMap.SimpleEntry<Integer, BeanMaterial>(m.getIndex(), m);
		}));
	}

	@Override
	public void loadShapes() throws AvuilderException {
		DataMaps.setShapesMap(connectAndSelect("Select * from Shape", (r) -> {
			Integer i = getWrapper(r, "idx");
			String n = r.getString("name");
			Double v = getWrapper(r, "cuboidFilledIn");
			Integer s = getWrapper(r, "symmetricIdx");
			BeanShape shape = new BeanShape(i, n, v, s);
			return new SimpleEntry<Integer, BeanShape>(shape.getIndex(), shape);
		}));
	}

	@Override
	public void loadTypeModels() throws AvuilderException {
		DataMaps.setTypeModelsMap(connectAndSelect("Select * from TypeModel", (r) -> {
			BeanTypeModelParams p = new BeanTypeModelParams(getWrapper(r, "idx"));
			p.setName(r.getString("name"));
			p.setDensityMod(getWrapper(r, "densityMod"));
			p.setDurabilityMod(getWrapper(r, "durabilityMod"));
			p.setMaterialCostMod(getWrapper(r, "materialCostMod"));
			p.setMechanics(getWrapper(r, "mechanics"));
			p.setEngineers(r.getDouble("engineers"));
			p.setProcess(getBoolean(r, "process"));
			p.setHasVolume(getBoolean(r, "hasVolume"));
			p.setCollisionReduction(getWrapper(r, "collisionReduction"));
			p.setComment(r.getString("comment"));

			BeanTypeModel tm = new BeanTypeModel(p);
			return new SimpleEntry<Integer, BeanTypeModel>(tm.getIndex(), tm);
		}));
	}

	@Override
	public void loadTypeModelsByMaterials() throws AvuilderException {
		DataMaps.setTypeModelsByMaterialsMap(connectAndSelect("Select * from TypeModelByMaterial", (r) -> {
			Integer typeModelIndex = getWrapper(r, "typeModelIdx");
			Integer materialIndex = getWrapper(r, "materialIdx");
			Double creditCostMod = getWrapper(r, "creditCostMod");

			BeanTypeModelByMaterial.MapIndex idx = new MapIndex(typeModelIndex, materialIndex);
			BeanTypeModelByMaterial tmbm = new BeanTypeModelByMaterial(idx, creditCostMod);
			return new SimpleEntry<BeanTypeModelByMaterial.MapIndex, BeanTypeModelByMaterial>(tmbm.getIndex(), tmbm);
		}));
	}

	@Override
	public void loadTypes() throws AvuilderException {
		DataMaps.setTypesMap(connectAndSelect("Select * from Type", (r) -> {
			Integer idx = getWrapper(r, "idx");
			Integer typeModelIndex = getWrapper(r, "typeModelIdx");
			Integer shape = getWrapper(r, "shapeIdx");
			BeanType type = new BeanType(idx, typeModelIndex, shape);
			return new SimpleEntry<Integer, BeanType>(type.getIndex(), type);
		}));
	}

}
