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
import avuilder4j.error.Avuilder4jException;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.java.SqlFunction;

public class SqliteDataLoader extends DataLoader {

	protected String dbFilePath = "db/avorion-db.sqlite3";

	protected Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:sqlite:" + getClass().getClassLoader().getResource(dbFilePath));
		return connection;
	}

	public String getDbFilePath() { return dbFilePath; }

	public void setDbFilePath(String dbFilePath) { this.dbFilePath = dbFilePath; }

	public <K, V> Map<K, V> connectAndSelect(String select,
			SqlFunction<ResultSet, AbstractMap.SimpleEntry<K, V>> mapper) throws Avuilder4jException {
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
			throw new Avuilder4jException(e);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new Avuilder4jException(e);
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
		return NullSafe.run(() -> i == 1 ? true : false, null);
	}

	@Override
	public void loadConstants() throws Avuilder4jException {
		DataMaps.setConstantsMap(connectAndSelect("Select * from Constant", (r) -> {
			String n = r.getString("name");
			String g = r.getString("category");
			Double nv = getWrapper(r, "value");

			BeanConstant k = new BeanConstant(n, g, nv);
			return new SimpleEntry<String, BeanConstant>(k.getName(), k);
		}));
	}

	@Override
	public void loadCrew() throws Avuilder4jException {
		DataMaps.setCrewMap(connectAndSelect("Select * from Crew", (r) -> {
			String name = r.getString("name");
			Double salary = getWrapper(r, "salary");
//
			BeanCrew b = new BeanCrew(name, salary);
			return new SimpleEntry<String, BeanCrew>(b.getName(), b);
		}));
	}

	@Override
	public void loadCrewCommands() throws Avuilder4jException {
		DataMaps.setCrewCommandMap(connectAndSelect("Select * from CrewCommand", (r) -> {
			String commander = r.getString("commander");
			String commanded = r.getString("commanded");
			Double commandRatio = getWrapper(r, "commandRatio");

			BeanCrewCommand.MapIndex idx = new BeanCrewCommand.MapIndex(commander, commanded);
			BeanCrewCommand b = new BeanCrewCommand(idx, commandRatio);

			return new SimpleEntry<BeanCrewCommand.MapIndex, BeanCrewCommand>(b.getIndex(), b);
		}));
	}

	@Override
	public void loadEffects() throws Avuilder4jException {
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
	public void loadMaterials() throws Avuilder4jException {
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
	public void loadShapes() throws Avuilder4jException {
		DataMaps.setShapesMap(connectAndSelect("Select * from Shape", (r) -> {
			String n = r.getString("name");
			Double v = getWrapper(r, "cuboidFilledIn");
			String s = r.getString("symmetricIdx");
			BeanShape shape = new BeanShape(n, v, s);
			return new SimpleEntry<String, BeanShape>(shape.getName(), shape);
		}));
	}

	@Override
	public void loadTypeModels() throws Avuilder4jException {
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
	public void loadTypeModelsByMaterials() throws Avuilder4jException {
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
	public void loadTypes() throws Avuilder4jException {
		DataMaps.setTypesMap(connectAndSelect("Select * from Type", (r) -> {
			Integer idx = getWrapper(r, "idx");
			Integer typeModelIndex = getWrapper(r, "typeModelIdx");
			String shape = getWrapper(r, "shape");
			BeanType type = new BeanType(idx, typeModelIndex, shape);
			return new SimpleEntry<Integer, BeanType>(type.getIndex(), type);
		}));
	}

}
