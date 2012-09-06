/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.examples.mutualexclusion;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.henshin.interpreter.EGraph;
import org.eclipse.emf.henshin.interpreter.Engine;
import org.eclipse.emf.henshin.interpreter.RuleApplication;
import org.eclipse.emf.henshin.interpreter.UnitApplication;
import org.eclipse.emf.henshin.interpreter.impl.EGraphImpl;
import org.eclipse.emf.henshin.interpreter.impl.EngineImpl;
import org.eclipse.emf.henshin.interpreter.impl.RuleApplicationImpl;
import org.eclipse.emf.henshin.interpreter.impl.UnitApplicationImpl;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.resource.HenshinResourceSet;

public class LTSBenchmark {
	
	/**
	 * Relative path to the example files.
	 */
	public static final String PATH = "src/org/eclipse/emf/henshin/examples/mutualexclusion";

	final static int GRAPH_SIZE_MIN = 20;

	final static int GRAPH_SIZE_MAX = GRAPH_SIZE_MIN;

	final static int STEP = 1;

	final static int ITERATIONS = 4; //must have at least 2 iterations

	final static int ROUNDS = 10000;

	
	
	public static void run(String path) {

		System.out.println("***************** Long Transformation Sequence ****************");
		System.out.println("Memory allocated:"+ Runtime.getRuntime().maxMemory() / 1024 / 1024 + "M");
		System.out.println("GRAPH_SIZE_MIN:" + GRAPH_SIZE_MIN);
		System.out.println("GRAPH_SIZE_MAX:" + GRAPH_SIZE_MAX);
		System.out.println("Rounds: " + ROUNDS);
		System.out.println("STEP:" + STEP);
		System.out.println("ITERATIONS:" + ITERATIONS + " , first iteration is not included into evaluation");
		System.out.println("***************************************************************");

		// Create a resource set with a base directory:
		HenshinResourceSet resourceSet = new HenshinResourceSet(path);

		// Load the module:
		Module module = resourceSet.getModule("mutualexclusion.henshin");
		EObject container = resourceSet.getEObject("initialgraph.xmi");

		// Load the rules:
		Rule newRule = (Rule) module.getUnit("newRule");
		Rule mountAllRule = (Rule) module.getUnit("mountAllRule");
		Unit ltsUnit = module.getUnit("lts2");
		Unit loopStsUnit = module.getUnit("loopLts");
		Unit finalStsUnit = module.getUnit("finalLts");

		// Perform benchmark for several graph sizes
		for (int graphSize = GRAPH_SIZE_MIN; graphSize <= GRAPH_SIZE_MAX; graphSize += STEP) {

			long sum = 0; //to calculate the average time

			for (int j = 0; j < ITERATIONS; j++) {

				EObject container2 = EcoreUtil.copy(container);

				// Initialize the Henshin interpreter:
				EGraph graph = new EGraphImpl(container2);
				Engine engine = new EngineImpl();

				RuleApplication ruleAppl = new RuleApplicationImpl(engine);
				ruleAppl.setEGraph(graph);
				UnitApplication unitAppl = new UnitApplicationImpl(engine);
				unitAppl.setEGraph(graph);

				// get Starting Time
				long startTime = System.currentTimeMillis();

				// create initial graph
				ruleAppl.setRule(newRule);
				for (int i = 0; i < graphSize - 2; i++) {
					ruleAppl.execute(null);
				}

				//mount resources
				ruleAppl.setRule(mountAllRule);
				ruleAppl.execute(null);

				for (int k = 0; k < ROUNDS; k++) {

					unitAppl.setUnit(ltsUnit);
					unitAppl.execute(null);

					for (int i = 0; i < graphSize - 1; i++) {
						unitAppl.setUnit(loopStsUnit);
						unitAppl.execute(null);
					}
					unitAppl.setUnit(finalStsUnit);
					unitAppl.execute(null);
				}

				// get finish time
				long finishTime = System.currentTimeMillis();
				System.out.println("Time: " + (finishTime - startTime));
				if (j != 0) { // don't include first ITERATION; emf classes are loaded
					sum = sum + finishTime - startTime;
				}

				//if (j == ITERATIONS -1)// persist the resulting graph on the last iteration
				//	BenchmarkHelper.persistGraph(BENCHMARK_CASE, resourceSet, container2);
			}
			System.out.println(" Graph size: " + graphSize + "  average time:"	+ (sum / (ITERATIONS - 1)));
		}

	}
	
	public static void main(String[] args) {
		run(PATH);
	}
	
}
