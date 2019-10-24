package avuilder4j.entities.game.base;

import avuilder4j.entities.game.BlockFactory;

public class BaseBlockFactory implements BlockFactory<BlockBase> {

	@Override
	public BlockBase getNewBlock() {
		return new BlockBase();
	}

	@Override
	public BlockBase getDefaultBlock() {
		return null;
	}

}
