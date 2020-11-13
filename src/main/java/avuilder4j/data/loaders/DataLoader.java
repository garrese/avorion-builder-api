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
import avuilder4j.error.AvuilderException;
import avuilder4j.util.java.Nullable;
import avuilder4j.util.java.NumberUtils;

public abstract class DataLoader {

	public abstract void loadConstants() throws AvuilderException;

	public abstract void loadCrew() throws AvuilderException;

	public abstract void loadCrewCommands() throws AvuilderException;

	public abstract void loadEffects() throws AvuilderException;

	public abstract void loadMaterials() throws AvuilderException;

	public abstract void loadShapes() throws AvuilderException;

	public abstract void loadTypeModels() throws AvuilderException;

	public abstract void loadTypeModelsByMaterials() throws AvuilderException;

	public abstract void loadTypes() throws AvuilderException;

	public void loadAll() throws AvuilderException {
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

	public void composeBlockArchetypes() throws AvuilderException {
		try {
			Map<BlockArchetype.MapIndex, BlockArchetype> map = new HashMap<BlockArchetype.MapIndex, BlockArchetype>();
			for (BeanType type : DataMaps.getTypesMap().values()) {
				for (BeanMaterial mat : DataMaps.getMaterialsMap().values()) {
					BeanTypeModel typeModel = DataMaps.getTypeModel(type.getTypeModelIndex());
					if (typeModel != null) {
						BeanTypeModelByMaterial tmbm = DataMaps.getTypeModelByMaterial(typeModel.getIndex(), mat.getIndex());
						if (tmbm != null) {
							BlockArchetypeParams p = new BlockArchetypeParams();

							p.setMaterialIndex(mat.getIndex());
							p.setMaterialName(mat.getName());

							p.setTypeIndex(type.getIndex());
							p.setShapeIndex(type.getShapeIdx());
							p.setTypeModelIndex(type.getTypeModelIndex());

							p.setCollisionReduction(typeModel.getCollisionReduction());
							p.setEngineers(typeModel.getEngineers());
							p.setMechanics(typeModel.getMechanics());
							p.setTypeModelName(typeModel.getName());
							p.setHasVolume(typeModel.getHasVolume());
							p.setProcess(typeModel.getProcess());

							BeanShape shape = DataMaps.getShape(type.getShapeIdx());
							p.setShapeName(shape.getName());
							p.setCuboidFilledIn(shape.getCuboidFilledIn());
							p.setSymmetricTypeIndex(DataMaps.getTypesMap().values().stream().filter(t -> Nullable.m(() -> {
								boolean modelFound = t.getTypeModelIndex().equals(type.getTypeModelIndex());
								boolean shapeFound = t.getShapeIdx().equals(shape.getSymmetricIndex());
								return modelFound && shapeFound;
							}, false)).map(t -> t.getIndex()).findFirst().orElse(null));

							List<Double> es = DataMaps.getEffectsValue(typeModel.getIndex(), mat.getIndex());
							p.setEffects(Collections.unmodifiableList(es));

							Double cCost = Nullable.m(() -> tmbm.getCreditCostMod() * mat.getCreditCost());
							p.setCreditCost(NumberUtils.round(cCost, 10));
							Double mCost = Nullable.m(() -> typeModel.getMaterialCostMod() * mat.getMaterialCost());
							p.setMaterialCost(NumberUtils.round(mCost, 10));
							Double dur = Nullable.m(() -> typeModel.getDurabilityMod() * mat.getDurability());
							p.setDurability(NumberUtils.round(dur, 10));
							Double dens = Nullable.m(() -> typeModel.getDensityMod() * mat.getDensity());
							p.setDensity(NumberUtils.round(dens, 10));

							BlockArchetype a = new BlockArchetype(p);
							BlockArchetype.MapIndex i = new BlockArchetype.MapIndex(type.getIndex(), mat.getIndex());
							map.put(i, a);
						}
					}
				}
			}
			DataMaps.setBlockArchetypesMap(map);
		} catch (Exception e) {
			throw new AvuilderException(e);
		}
	}

}
