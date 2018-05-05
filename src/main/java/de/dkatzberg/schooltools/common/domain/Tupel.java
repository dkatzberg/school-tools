/**
 * 
 */
package main.java.de.dkatzberg.schooltools.common.domain;

/**
 * This class defines a tupel.
 * 
 * @author Daniel Katzberg
 *
 */
public class Tupel<T> {
	
	private T firstTupelElement;
	
	private T secondTupelElement;
	
	
	/**
	 * Constructor for Tupel to set all tupel elements directly.
	 * 
	 * @param firstTupelElement The first tupel element.
	 * @param secondTupelElement The second tupel element.
	 */
	public Tupel(T firstTupelElement, T secondTupelElement) {
		this.firstTupelElement = firstTupelElement;
		this.secondTupelElement = secondTupelElement;
	}

	/* GETTER / SETTER */
	public T getFirstTupelElement() {
		return firstTupelElement;
	}

	public void setFirstTupelElement(T firstTupelElement) {
		this.firstTupelElement = firstTupelElement;
	}

	public T getSecondTupelElement() {
		return secondTupelElement;
	}

	public void setSecondTupelElement(T secondTupelElement) {
		this.secondTupelElement = secondTupelElement;
	}
}
