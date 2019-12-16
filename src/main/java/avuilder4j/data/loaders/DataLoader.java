package avuilder4j.data.loaders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import avuilder4j.data.DataMaps;
import avuilder4j.data.beans.BeanMaterial;
import avuilder4j.data.beans.BeanShape;
import avuilder4j.data.beans.BeanType;
import avuilder4j.data.beans.BeanTypeModel;
import avuilder4j.data.beans.BeanTypeModelByMaterial;
import avuilder4j.data.dtos.BlockArchetype;
import avuilder4j.data.dtos.BlockArchetypeParams;
import avuilder4j.error.Avuilder4jException;
import avuilder4j.util.java.NullSafe;
import avuilder4j.util.java.Utils;

public abstract class DataLoader {

	public abstract void loadConstants() throws Avuilder4jException;

	public abstract void loadCrew() throws Avuilder4jException;

	public abstract void loadCrewCommands() throws Avuilder4jException;

	public abstract void loadEffects() throws Avuilder4jException;

	public abstract void loadMaterials() throws Avuilder4jException;

	public abstract void loadShapes() throws Avuilder4jException;

	public abstract void loadTypeModels() throws Avuilder4jException;

	public abstract void loadTypeModelsByMaterials() throws Avuilder4jException;

	public abstract void loadTypes() throws Avuilder4jException;

	public void loadAll() throws Avuilder4jException {
		loadConstants();
		loadCrew();
		loadCrewCommands();
		loadEffects();
		loadMaterials();
		loadShapes();
		loadTypeModels();
		loadTypeModelsByMaterials();
		loadTypes();

		composeBlockArchetypes();
	}

	public void composeBlockArchetypes() throws Avuilder4jException {
		try {
			Map<BlockArchetype.MapIndex, BlockArchetype> map = new HashMap<BlockArchetype.MapIndex, BlockArchetype>();
			for (BeanType t : DataMaps.getTypesMap().values()) {
				for (BeanMaterial m : DataMaps.getMaterialsMap().values()) {
					BeanTypeModel tm = DataMaps.getTypeModel(t.getTypeModelIndex());
					if (tm != null) {
						BeanTypeModelByMaterial tmbm = DataMaps.getTypeModelByMaterial(tm.getIndex(), m.getIndex());
						if (tmbm != null) {
							BlockArchetypeParams p = new BlockArchetypeParams();

							p.setMaterialIndex(m.getIndex());
							p.setMaterialName(m.getName());

							p.setTypeIndex(t.getIndex());
							p.setShapeIndex(t.getShapeIdx());
							p.setTypeModelIndex(t.getTypeModelIndex());

							p.setCollisionReduction(tm.getCollisionReduction());
							p.setEngineers(tm.getEngineers());
							p.setMechanics(tm.getMechanics());
							p.setTypeModelName(tm.getName());
							p.setHasVolume(tm.getHasVolume());
							p.setProcess(tm.getProcess());

							BeanShape s = DataMaps.getShape(t.getShapeIdx());
							p.setShapeName(s.getName());
							p.setCuboidFilledIn(s.getCuboidFilledIn());
							p.setSymmetricShape(s.getSymmetric());

							List<Double> es = DataMaps.getEffectsValue(tm.getIndex(), m.getIndex());
							p.setEffects(Collections.unmodifiableList(es));

							Double cCost = NullSafe.run(() -> tmbm.getCreditCostMod() * m.getCreditCost());
							p.setCreditCost(Utils.round(cCost, 6));
							Double mCost = NullSafe.run(() -> tm.getMaterialCostMod() * m.getMaterialCost());
							p.setMaterialCost(Utils.round(mCost, 6));
							Double dur = NullSafe.run(() -> tm.getDurabilityMod() * m.getDurability());
							p.setDurability(Utils.round(dur, 6));
							Double dens = NullSafe.run(() -> tm.getDensityMod() * m.getDensity());
							p.setDensity(Utils.round(dens, 6));

							BlockArchetype a = new BlockArchetype(p);
							BlockArchetype.MapIndex i = new BlockArchetype.MapIndex(t.getIndex(), m.getIndex());
							map.put(i, a);
						}
					}
				}
			}
			DataMaps.setBlockArchetypesMap(map);
		} catch (Exception e) {
			throw new Avuilder4jException(e);
		}

	}

}
