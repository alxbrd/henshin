package org.eclipse.emf.henshin.diagram.edit.maps;

import java.util.List;

import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.Mapping;

public interface MapEditor<E> {
	
	/**
	 * Get the source graph.
	 * @return Source graph.
	 */
	Graph getSource();

	/**
	 * Get the target graph.
	 * @return target graph.
	 */
	Graph getTarget();
	
	/**
	 * Get the list of mappings from source to target.
	 * @return List of mappings.
	 */
	List<Mapping> getMappings();
	
	/**
	 * Get the opposite of an element.
	 * @param e Element.
	 * @return Its opposite, if existing.
	 */
	E getOpposite(E e);
	
	/**
	 * Copy an element from source to target or vice versa. 
	 * @param e Element to be copied.
	 * @return The copied element.
	 */
	E copy(E e);

	/**
	 * Move an element from source to target or vice versa. 
	 * @param e Element to be moved.
	 */
	void move(E e);

	/**
	 * Replace an element by its opposite counter part.
	 * @param e Element to be replaced.
	 */
	E replace(E e);

	/**
	 * Remove an element from the source or the target graph. 
	 * @param e Element to be removed.
	 */
	void remove(E e);

}