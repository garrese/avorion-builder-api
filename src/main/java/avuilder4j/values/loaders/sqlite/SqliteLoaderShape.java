package avuilder4j.values.loaders.sqlite;

import java.sql.ResultSet;

import avuilder4j.dtos.map.MapIndex;
import avuilder4j.dtos.map.Shape;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.values.loaders.EntityMap;
import avuilder4j.values.loaders.ShapeLoader;

public class SqliteLoaderShape extends SqliteLoader<Shape> implements ShapeLoader {

	public static final String TABLE_NAME = "Shape";

	@Override
	public EntityMap<Shape> loadAll() throws Avuilder4jException {
		return super.doConnectedAction(TABLE_NAME);
	}

	@Override
	public Shape onSqliteDataMapping(ResultSet r) throws Exception {
		MapIndex<Integer> i = new MapIndex<Integer>(r.getInt("idx"));
		String n = r.getString("name");
		Double v = r.getDouble("volumeMod");
		Shape shape = new Shape(i, n, v);
		return shape;
	}

//	@Override
//	public EntityMap<Shape> loadAll() throws Avuilder4jException {
//		return super.doConnectedAction();
//	}
//
//	@Override
//	public EntityMap<Shape> onDataMapping(Connection c) throws Exception {
//		EntityMap<Shape> map = new EntityMap<>();
//
//		String select = "SELECT * FROM Shape";
//		ResultSet r = null;
//		PreparedStatement st = c.prepareStatement(select);
//		r = st.executeQuery();
//
//		while (r.next()) {
//			MapIndex<Integer> idx = new MapIndex<Integer>(r.getInt("idx"));
//			MaterialParams p = new MaterialParams(idx);
//			p.setName(r.getString("name"));
//			p.setDensity(r.getDouble("density"));
//			p.setDurability(r.getDouble("durability"));
//			p.setCreditCost(r.getDouble("creditCost"));
//			p.setMaterialCost(r.getDouble("materialCost"));
//			map.add(new Material(p));
//		}
//
//		return map;
//	}

//	@Override
//	public EntityMap<Material> loadAll() throws Avuilder4jException {
//		return super.doConnectedAction();
//	}
//
//	@Override
//	public EntityMap<Material> onConectedAction(Connection c) throws Exception {
//		EntityMap<Material> map = new EntityMap<>();
//
//		String select = "SELECT * FROM Material";
//		ResultSet r = null;
//		PreparedStatement st = c.prepareStatement(select);
//		r = st.executeQuery();
//
//		while (r.next()) {
//			MapIndex<Integer> idx = new MapIndex<Integer>(r.getInt("idx"));
//			MaterialParams p = new MaterialParams(idx);
//			p.setName(r.getString("name"));
//			p.setDensity(r.getDouble("density"));
//			p.setDurability(r.getDouble("durability"));
//			p.setCreditCost(r.getDouble("creditCost"));
//			p.setMaterialCost(r.getDouble("materialCost"));
//			map.add(new Material(p));
//		}
//
//		return map;
//	}

}
