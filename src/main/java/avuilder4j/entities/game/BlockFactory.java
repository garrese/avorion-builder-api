package avuilder4j.entities.game;

public interface BlockFactory<E> {

	public E getNewBlock();

	public E getDefaultBlock();

}
