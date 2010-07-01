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
package org.eclipse.emf.henshin.internal.matching;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.eclipse.emf.henshin.common.util.GraphSkeleton;
import org.eclipse.emf.henshin.internal.conditions.attribute.AttributeConditionHandler;
import org.eclipse.emf.henshin.internal.conditions.nested.ApplicationCondition;

public class Matchfinder<TType, TNode> extends
		ApplicationCondition<TType, TNode> {
	private List<Solution<TType, TNode>> solutions;
	private AttributeConditionHandler conditionHandler;

	public Matchfinder(
			GraphSkeleton<TType, TNode> graph,
			Map<Variable<TType, TNode>, DomainSlot<TType, TNode>> variableDomainMap,
			AttributeConditionHandler conditionHandler) {
		super(graph, variableDomainMap, false);
		this.conditionHandler = conditionHandler;
	}

	public boolean findSolution() {
		boolean matchIsPossible = false;

		if (solutions == null) {
			solutions = new ArrayList<Solution<TType, TNode>>();
			matchIsPossible = true;
		} else {
			for (int i = variables.size() - 1; i >= 0; i--) {
				Variable<TType, TNode> var = variables.get(i);
				if (domainMap.get(var).unlock(var)) {
					matchIsPossible = true;
					break;
				} else {
					domainMap.get(var).clear(var);
				}
			}
		}

		if (matchIsPossible) {
			boolean success = findGraph();
			if (success)
				solutions.add(new Solution<TType, TNode>(variables, domainMap,
						conditionHandler));

			return success;
		}

		return false;
	}

	/**
	 * Returns a random match. This is as slow as computing all matches because
	 * it actually does compute all matches and randomly chooses one as a
	 * result.
	 * 
	 * @return A random match or null if no match exists.
	 */
	public Solution<TType, TNode> getRandomMatch() {
		while (getNextMatch() != null) {
		}

		if (solutions.size() > 0) {
			int x = new Random().nextInt(solutions.size());
			return solutions.get(x);
		}

		return null;
	}

	/**
	 * Returns a match. On consecutive calls other matches will be returned.
	 * 
	 * @return A match or null if no match exists.
	 */
	public Solution<TType, TNode> getNextMatch() {
		boolean success = findSolution();

		if (success)
			return solutions.get(solutions.size() - 1);

		return null;
	}

	public List<Solution<TType, TNode>> getAllMatches() {
		while (getNextMatch() != null) {
		}
		;

		return solutions;
	}
}