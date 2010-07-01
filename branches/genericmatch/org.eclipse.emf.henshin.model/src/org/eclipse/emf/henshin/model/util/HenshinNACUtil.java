/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University of Berlin, 
 * University of Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University of Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.model.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.model.And;
import org.eclipse.emf.henshin.model.BinaryFormula;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Rule;

/**
 * Common utility function for accessing and modifying
 * negative application conditions of rules (NACs). Negative
 * application conditions are negated {@link NestedCondition}s
 * that are associated to the LHS of a rule.
 * 
 * @author Christian Krause
 */
public class HenshinNACUtil {
	
	/**
	 * Find all negative application conditions (NACs) of a rule.
	 * @param rule Rule.
	 * @return List of negative application conditions.
	 */
	public static List<NestedCondition> getAllNACs(Rule rule) {
		List<NestedCondition> nacs = new ArrayList<NestedCondition>();
		addNACs(rule.getLhs().getFormula(), nacs);
		return nacs;
	}
	
	/**
	 * Find a NAC with a given name.
	 * @param rule Rule.
	 * @param name Name of the NAC.
	 * @return The NAC if found.
	 */
	public static NestedCondition getNAC(Rule rule, String name) {
		for (NestedCondition nac : getAllNACs(rule)) {
			if (name.equals(nac.getConclusion().getName())) return nac;
		}
		return null;
	}
	
	/*
	 * Collect all NACs of rule recursively.
	 */
	private static void addNACs(Formula formula, List<NestedCondition> nacs) {
		
		// Conjunction (And):
		if (formula instanceof And) {
			addNACs(((And) formula).getLeft(),nacs);
			addNACs(((And) formula).getRight(),nacs);
		}
		
		// The actual NACs (NestedCondition):
		else if (formula instanceof NestedCondition) {
			NestedCondition nested = (NestedCondition) formula;
			if (nested.isNegated()) nacs.add(nested);
		}
		
		// Done.
	}

	/**
	 * Create a new NAC.
	 * @param rule Rule.
	 * @param name Name of the NAC.
	 * @return The newly create NAC.
	 */
	public static NestedCondition createNAC(Rule rule, String name) {
		
		// Create the NAC:
		NestedCondition nac = HenshinFactory.eINSTANCE.createNestedCondition();
		nac.setNegated(true);
		Graph graph = HenshinFactory.eINSTANCE.createGraph();
		graph.setName(name);
		nac.setConclusion(graph);
		
		// Add it to the rule:
		if (rule.getLhs().getFormula()==null) {
			rule.getLhs().setFormula(nac);
		} else {
			And and = HenshinFactory.eINSTANCE.createAnd();
			and.setLeft(rule.getLhs().getFormula());
			and.setRight(nac);
			rule.getLhs().setFormula(and);
		}
		
		// Done.
		return nac;
		
	}
	
	/**
	 * Remove a NAC from a rule.
	 * @param rule Rule to be modified.
	 * @param nac NAC to be removed.
	 */
	public static void removeNAC(Rule rule, NestedCondition nac) {
		
		// Remember the container and destroy the object:
		EObject container = nac.eContainer();
		EcoreUtil.remove(nac);
		
		// Check if the container was a binary formula:
		if (container instanceof BinaryFormula) {
			BinaryFormula binary = (BinaryFormula) container;
			
			// Replace the formula by the remaining sub-formula:
			Formula remainder = (binary.getLeft()!=null) ? binary.getLeft() : binary.getRight();
			EcoreUtil.replace(binary, remainder);
		}
		
	}
	
	/**
	 * Check whether a NAC is trivial. A trivial NAC is one that
	 * can always be matched and hence causes the rule never to
	 * be applicable.
	 * @param nac NAC.
	 * @return <code>true</code> if the NAC can always be matched.
	 */
	public static boolean isTrivialNAC(NestedCondition nac) {
		
		// NAC Details:
		Graph graph = nac.getConclusion();
		EList<Mapping> mappings = nac.getMappings();
		
		// Check if any of the nodes is not the image of a mapping.
		for (Node node : graph.getNodes()) {
			if (HenshinMappingUtil.getNodeOrigin(node, mappings)==null) return false;
		}

		// Check if any of the edges is not the image of a mapping.
		for (Edge edge : graph.getEdges()) {
			if (HenshinMappingUtil.getEdgeOrigin(edge, mappings)==null) return false;
		}
		
		// Otherwise it is trivial:
		return true;
		
	}
	
}