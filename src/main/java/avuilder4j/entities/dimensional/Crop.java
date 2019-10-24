package avuilder4j.entities.dimensional;

public class Crop {

	public double upperX;
	public double lowerX;
	public double upperY;
	public double lowerY;
	public double upperZ;
	public double lowerZ;

	public Crop() {

	}

	public Crop(double upperX, double lowerX, double upperY, double lowerY, double upperZ, double lowerZ) {
		this.upperX = upperX;
		this.lowerX = lowerX;
		this.upperY = upperY;
		this.lowerY = lowerY;
		this.upperZ = upperZ;
		this.lowerZ = lowerZ;
	}

	public Crop(double x, double y, double z) {
		this.upperX = x;
		this.lowerX = x;
		this.upperY = y;
		this.lowerY = y;
		this.upperZ = z;
		this.lowerZ = z;
	}

	public Crop(double margin) {
		this.upperX = margin;
		this.lowerX = margin;
		this.upperY = margin;
		this.lowerY = margin;
		this.upperZ = margin;
		this.lowerZ = margin;
	}

	/**
	 * Gets the {@link #upperX}.
	 * 
	 * @return the {@link #upperX}.
	 */
	public double getUpperX() {
		return upperX;
	}

	/**
	 * Sets the {@link #upperX}.
	 * 
	 * @param upperX the {@link #upperX} to set.
	 */
	public void setUpperX(double upperX) {
		this.upperX = upperX;
	}

	/**
	 * Gets the {@link #lowerX}.
	 * 
	 * @return the {@link #lowerX}.
	 */
	public double getLowerX() {
		return lowerX;
	}

	/**
	 * Sets the {@link #lowerX}.
	 * 
	 * @param lowerX the {@link #lowerX} to set.
	 */
	public void setLowerX(double lowerX) {
		this.lowerX = lowerX;
	}

	/**
	 * Gets the {@link #upperY}.
	 * 
	 * @return the {@link #upperY}.
	 */
	public double getUpperY() {
		return upperY;
	}

	/**
	 * Sets the {@link #upperY}.
	 * 
	 * @param upperY the {@link #upperY} to set.
	 */
	public void setUpperY(double upperY) {
		this.upperY = upperY;
	}

	/**
	 * Gets the {@link #lowerY}.
	 * 
	 * @return the {@link #lowerY}.
	 */
	public double getLowerY() {
		return lowerY;
	}

	/**
	 * Sets the {@link #lowerY}.
	 * 
	 * @param lowerY the {@link #lowerY} to set.
	 */
	public void setLowerY(double lowerY) {
		this.lowerY = lowerY;
	}

	/**
	 * Gets the {@link #upperZ}.
	 * 
	 * @return the {@link #upperZ}.
	 */
	public double getUpperZ() {
		return upperZ;
	}

	/**
	 * Sets the {@link #upperZ}.
	 * 
	 * @param upperZ the {@link #upperZ} to set.
	 */
	public void setUpperZ(double upperZ) {
		this.upperZ = upperZ;
	}

	/**
	 * Gets the {@link #lowerZ}.
	 * 
	 * @return the {@link #lowerZ}.
	 */
	public double getLowerZ() {
		return lowerZ;
	}

	/**
	 * Sets the {@link #lowerZ}.
	 * 
	 * @param lowerZ the {@link #lowerZ} to set.
	 */
	public void setLowerZ(double lowerZ) {
		this.lowerZ = lowerZ;
	}

}
