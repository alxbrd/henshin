import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.henshin.interpreter.util.ModelHelper;
import org.eclipse.emf.henshin.model.BinaryFormula;
import org.eclipse.emf.henshin.model.ConditionalUnit;
import org.eclipse.emf.henshin.model.CountedUnit;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Not;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.eclipse.emf.henshin.model.TransformationUnit;
import org.eclipse.emf.henshin.model.impl.HenshinFactoryImpl;
import org.eclipse.emf.henshin.model.impl.HenshinPackageImpl;
import org.eclipse.emf.henshin.testframework.Tools;



public class Transformation {

	private static Map<EObject, EObject> newElements = new HashMap<EObject, EObject>();		// map from old element to new element
	private static ArrayList<EObject> createdElements = new ArrayList<EObject>();	// list of newly created elements
	
	private static ArrayList<NestedCondition> nacList = new ArrayList<NestedCondition>();	// list of NACs
	private static ArrayList<String> errorList = new ArrayList<String>();
	
	private static Map<Rule, Rule> amalgamationUnitRuleCopies = new HashMap<Rule, Rule>();  // Map from (Rules used in AmalgamationUnits) to (Copies of Rules)
	
	private static class AmalgamationUnitHelper {
		/**
		 * Data structure for storing information related to amalgamationUnits
		 */
		EObject kernelRule;
		ArrayList<EObject> multiRules = new ArrayList<EObject>();
		ArrayList<EObject> lhsMappings = new ArrayList<EObject>();
		ArrayList<EObject> rhsMappings = new ArrayList<EObject>();
		String amalgamationUnitName = "";
		
		@Override
		public String toString() {
			String mrString = "";
			for (EObject eo : multiRules) {
				mrString += eo + "\t";
			}
			return "kernel: " + kernelRule + "  ;;; multi: " + mrString;
		}
	}
	
	private static ArrayList<AmalgamationUnitHelper> amuList = new ArrayList<AmalgamationUnitHelper>();	// List of amalgamation units
	
	
	private static TransformationSystem newRoot;	// root object of the new Transformation System
	
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {		
		String oldHenshinFilename = "dbg/philNew.henshin";

		HenshinPackageImpl.init();

		
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
		URI henshinUri = URI.createFileURI(new File("model/henshin-080.ecore").getAbsolutePath());
		ModelHelper.registerEPackageByEcoreFile(CommonPlugin.resolve(henshinUri));
		
		
		ModelHelper.registerFileExtension("henshin");	// TODO: aus modelhelper kopieren
		EObject graphRoot = ModelHelper.loadFile(oldHenshinFilename);

		
		addRootObjectToMap(graphRoot);	// fill map by creating new objects
		updateAmalgamationUnitReferences();
		System.out.println("\n ****** \n");
		newRoot = (TransformationSystem) newElements.get(graphRoot);

		updateReferences();	// update references between objects
		buildAmalgamationUnits();
		wrapNACs();	// wrap NACs
//		cleanUpNotFormulas();	// clean up not formulas
		moveRulesToRulesReference();	// move Rules (i.e. former amalgamation units) from "TransformationUnits" to "Rules" reference

		for (TransformationUnit tu : newRoot.getTransformationUnits()) {
			System.out.println("updating amalgamation unit references for " + tu.getName());
			updateAmalgamationUnitReferences(tu);
		}
		
		
		System.out.println("\n\n####################");
		System.out.println(newRoot);
		Tools.printCollection(newRoot.getRules());
		Tools.persist(newRoot, "dbg/newHenshin.henshin");
		
		System.out.println("naclist:");
		Tools.printCollection(nacList);
		
		if (nacList.size() != 0) {
			addError("Some NACs couldn't be converted.");
		}
		
		
		for (Entry<EObject, EObject> entry : newElements.entrySet()) {
			System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		
		
		System.out.println("\n\n\n");
		for (AmalgamationUnitHelper auh : amuList) {
			System.out.println(auh);
		}
		
	}
	
	
	/**
	 * When an amalgamationUnit is converted, it becomes a Rule, but will still be contained in the TransformationSystem's
	 * TransformationUnits. For improved model consistency, move these Rules to the "Rules" reference.
	 */
	private static void moveRulesToRulesReference() {
		ArrayList<TransformationUnit> ruleList = new ArrayList<TransformationUnit>();
		for (TransformationUnit tu : newRoot.getTransformationUnits()) {
			if (tu instanceof Rule) {
				ruleList.add(tu);
			}
		}
		
		for (TransformationUnit tu : ruleList) {
			newRoot.getTransformationUnits().remove(tu);
			newRoot.getRules().add((Rule) tu);
		}
		
	}
	
	/**
	 * Update references to Amalgamation Units
	 * @param tu
	 */
	private static void updateAmalgamationUnitReferences(TransformationUnit tu) {
		updateAmalgamationUnitReferences(new ArrayList<TransformationUnit>(), tu);
	}
	
	/**
	 * Update references to Amalgamation Units recursively.
	 * @param stack	Contains the TransformationUnits which were already visited
	 * @param tu	TransformationUnit to update
	 */
	private static void updateAmalgamationUnitReferences(ArrayList<TransformationUnit> stack, TransformationUnit tu) {
		// TODO: If the kernel rule was previously used outside of the amalgamation unit, this method will replace this occurence with the amalgamation unit
		// Needed: List of kernel rules used outside of amalgamation units to check against.
		
		if (stack.contains(tu)) { // unit already is on stack, so we have a cycle
			return;
		}
		stack.add(tu);	// push the current transformation unit on the stack
		if (tu instanceof CountedUnit) {
			TransformationUnit su = ((CountedUnit) tu).getSubUnit();
			
			if (su instanceof TransformationUnit && !(su instanceof Rule)) { // check for nested transformation units
				updateAmalgamationUnitReferences(stack, su);	// recursively check nested transformation units
			} else if (amalgamationUnitRuleCopies.get(su) != null) { // is the rule known to have been an amalgamation unit?
				((CountedUnit) tu).setSubUnit(amalgamationUnitRuleCopies.get(su));
			}
		} else if (tu instanceof Rule) {
			// Nothing to do here
		} else if (tu instanceof ConditionalUnit) {
			// get the sub-units
			ConditionalUnit cu = (ConditionalUnit) tu;
			TransformationUnit suIf = cu.getIf();
			TransformationUnit suThen = cu.getThen();
			TransformationUnit suElse = cu.getElse();
			
			if (suIf instanceof TransformationUnit && !(suIf instanceof Rule)) {
				updateAmalgamationUnitReferences (stack, suIf);
			} else if (amalgamationUnitRuleCopies.get(suIf) != null) {
				cu.setIf(amalgamationUnitRuleCopies.get(suIf));
			}
			
			if (suThen instanceof TransformationUnit && !(suThen instanceof Rule)) {
				updateAmalgamationUnitReferences (stack, suThen);
			} else if (amalgamationUnitRuleCopies.get(suThen) != null) {
				cu.setThen(amalgamationUnitRuleCopies.get(suThen));
			}
			
			if (suElse instanceof TransformationUnit && !(suElse instanceof Rule)) {
				updateAmalgamationUnitReferences (stack, suElse);
			} else if (amalgamationUnitRuleCopies.get(suElse) != null) {
				cu.setElse(amalgamationUnitRuleCopies.get(suElse));
			}
			
		} else {
			// get the sub-units.
			// this list needs to be modified, so the getSubUnits(boolean deep) method can not be used here
			EList<TransformationUnit> subUnits = (EList<TransformationUnit>) tu.eGet(getEReferenceByName("subUnits", tu.eClass()));
			for (TransformationUnit su : subUnits) {
				if (su instanceof TransformationUnit && !(su instanceof Rule)) { // check for nested TransformationUnits
					updateAmalgamationUnitReferences(stack,  su);
				} else if (amalgamationUnitRuleCopies.get(su) != null) {
					int currentIndex = subUnits.indexOf(su);
					subUnits.set(currentIndex, amalgamationUnitRuleCopies.get(su));
				}
			}
		}
	}
	
	/**
	 * Build new Rules in place of the old AmalgamationUnits
	 * Note: Kernel and Multi Rules will be copied, although this will not be neccessary most of the time.
	 */
	private static void buildAmalgamationUnits() {
		for (AmalgamationUnitHelper amu : amuList) {
			Rule krlRuleTmp = (Rule) newElements.get(amu.kernelRule);
			Rule krlRule = (Rule) EcoreUtil.copy(krlRuleTmp);
			krlRule.getMultiRules().clear();
			amalgamationUnitRuleCopies.put(krlRuleTmp, krlRule);
			krlRule.setName(amu.amalgamationUnitName + "_krl_" + krlRule.getName());
			System.out.println("\n\n\n\nKERNEL RULE: " + krlRule);
			newRoot.getRules().add(krlRule);
			
			for (EObject mr : amu.multiRules) {
				Rule multiRuleTmp = (Rule) newElements.get(mr);
				Rule multiRule = (Rule) EcoreUtil.copy(multiRuleTmp);
				amalgamationUnitRuleCopies.put(multiRuleTmp, multiRule);
				multiRule.setName(amu.amalgamationUnitName + "_mul_" + multiRule.getName());
				System.out.println("multi rule: " + multiRule);
				System.out.println("######################## " + krlRule.getMultiRules().remove(multiRuleTmp));
				newRoot.getRules().add(multiRuleTmp);
				krlRule.getMultiRules().add(multiRule);
			}
			
			
			System.out.println("lhs mappings:");
			// Convert the mappings
			/* The idea:
			 * originalKernelRule                                       kernelRuleCopy
			 *       |- LHS                                                |- LHS (copy)
			 *       |  '- Node   <----.                                   |  '- Node (copy)        <--.
			 *       '- RHS             |                                  |- RHS (copy)               |
			 *          '- ...          |                                  '- multiRule (copy)         | mapping[1]
			 *                       mapping[1]     ==transform.==>              |- LHS (copy)         |
			 *                          |                                        |  '- Node (copy)   --'
			 * 	originalMultiRule       |                                        |- RHS
			 *       |- LHS             /                                        '- mappings  
			 *       |  '- Node     ---'                                             '- mapping[1]
			 *       '- RHS
			 *          '- ...  
			 *          
			 *  lhsMappings
			 *       '- mapping[1]
			 */
			
			for (ArrayList<EObject> al : new ArrayList[]{amu.lhsMappings, amu.rhsMappings}) { 
				System.out.println("***!");
				for (EObject mp : al) {
					Mapping newMp = (Mapping) newElements.get(mp);
					System.out.println("\tnew mapping:" + newMp + " : " + newMp.getOrigin().getGraph().getContainerRule().getName() + " -> " + newMp.getImage().getGraph().getContainerRule().getName());
					
					// get the origin node and the rule containing the origin node
					Node originNodeTmp = newMp.getOrigin();
					Rule originNodeContainerRuleTmp = originNodeTmp.getGraph().getContainerRule();
					
					// get the copied rule containing the new origin node
					Rule originNodeContainerRule = amalgamationUnitRuleCopies.get(originNodeContainerRuleTmp);
					System.out.println("\tor:" + originNodeContainerRule);
	
					// find out whether the node was in the LHS or RHS and obtain the new Node
					Node newOriginNode;
					if (originNodeContainerRuleTmp.getLhs().equals(originNodeTmp.getGraph())) { // Node in LHS
						int nodeIndex = originNodeContainerRuleTmp.getLhs().getNodes().indexOf(originNodeTmp);
						newOriginNode = originNodeContainerRule.getLhs().getNodes().get(nodeIndex);
						System.out.println("node in LHS @ index " + nodeIndex);
					} else { // Node in RHS
						int nodeIndex = originNodeContainerRuleTmp.getRhs().getNodes().indexOf(originNodeTmp);
						newOriginNode = originNodeContainerRule.getRhs().getNodes().get(nodeIndex);
						System.out.println("node in RHS @ index " + nodeIndex);					
					}
					System.out.println(originNodeTmp + " ==> " + newOriginNode);
					
					newMp.setOrigin(newOriginNode);
					
					Node imageNodeTmp = newMp.getImage();
					Rule imageNodeContainerRuleTmp = imageNodeTmp.getGraph().getContainerRule();
					
					Rule imageNodeContainerRule = amalgamationUnitRuleCopies.get(imageNodeContainerRuleTmp);
					
					Node newImageNode;
					if (imageNodeContainerRuleTmp.getLhs().equals(imageNodeTmp.getGraph())) { // Node in LHS
						int nodeIndex = imageNodeContainerRuleTmp.getLhs().getNodes().indexOf(imageNodeTmp);
						newImageNode = imageNodeContainerRule.getLhs().getNodes().get(nodeIndex);
						System.out.println("node in LHS @ index " + nodeIndex);
					} else {	// Node in RHS
						int nodeIndex = imageNodeContainerRuleTmp.getRhs().getNodes().indexOf(imageNodeTmp);
						newImageNode = imageNodeContainerRule.getRhs().getNodes().get(nodeIndex);
						System.out.println("node in RHS @ index " + nodeIndex);
					}
					
					
					System.out.println("\tim:" + imageNodeContainerRule);
	
					newMp.setImage(newImageNode);
					imageNodeContainerRule.getMultiMappings().add(newMp);
					System.out.println("mapping container:" + newMp.eContainer());
					
	
				}
			}
			
			
			
		}
	}


	/**
	 * Update references of the new objects. All references of 2011 objects should point to other 2011 objects, so
	 * traverse the newElements map and update all references between objects.
	 */
	private static void updateReferences() {
		for (Entry e : newElements.entrySet()) {
			System.out.println("\nREFERENCES FOR " + e.getKey());

			for (EReference er : ((EObject) e.getKey()).eClass().getEAllReferences()) {	// XXX: remove
				System.out.println("\t" + er);
			}
			for (EReference er : ((EObject) e.getValue()).eClass().getEAllReferences()) {
				System.out.println("#\t" + er);
				EReference oldErefType = getEReferenceByName(er.getName(), ((EObject) e.getKey()).eClass());
				if (er.isChangeable()) {
					if (oldErefType != null) {
						if (((EObject) e.getKey()).eGet(oldErefType) instanceof EList) {
							// TODO: imports!
							EList<EObject> oldReferencedObjects = (EList<EObject>) ((EObject) e.getKey()).eGet(oldErefType);
							System.out.println("lst:: " + oldReferencedObjects);
							if (oldReferencedObjects != null) { // reference is a list
								EList<EObject> newReferencedObjects = ((EList<EObject>) ((EObject) e.getValue()).eGet(er));
								
								if (er.getName().equals("imports")) { // special case for imports
									System.out.println("special case for imports");
									((EObject) e.getValue()).eSet(er, oldReferencedObjects);
								} else {
									convertObjectList(oldReferencedObjects, newReferencedObjects);
									System.out.println("NEW:: " + newReferencedObjects);
									//((EObject) e.getValue()).eSet(er, newReferencedObjects);
									// TODO: Set reference
									System.out.println("new:: " + ((EObject) e.getValue()).eGet(er) + " // " + newReferencedObjects);
									System.out.println("non-null");
								}
							}
						} else {	// reference is not a list, but (maybe?) a single object
							if (er.getName().equals("type")) { // special case for types
								System.out.println("special case for types");
								EObject objectType = (EObject) ((EObject) e.getKey()).eGet(oldErefType);
								((EObject) e.getValue()).eSet(er, objectType);
								
							} else if (((EObject) e.getKey()).eGet(oldErefType) instanceof EObject) {
								EObject oldReferencedObject = (EObject) ((EObject) e.getKey()).eGet(oldErefType);
								System.out.println("obj:: " + oldReferencedObject);
								if (oldReferencedObject != null) {
									EObject newReferencedObject = newElements.get(oldReferencedObject);
									((EObject) e.getValue()).eSet(er, newReferencedObject);
									System.out.println("non-null");
								}
							} else {
								System.out.println("no list, no single object ... ??? ->" + ((EObject) e.getKey()).eGet(oldErefType));
							}
						}
					} else {
						System.err.println(er + " has no old reference equivalent");
						System.err.flush();
					}
				} else {
					System.out.println("reference not changeable");
				}
			}
		}
	}
	
	/**
	 * Convert a List of old (2010) Objects to a List of new (2011) Objects. The order will be preserved as best as possible.
	 * @param oldList	List of '2010' objects
	 * @param newList	New List of '2011' objects
	 */
	private static void convertObjectList(EList<EObject> oldList, EList<EObject> newList) {
		System.out.print("{");
	
		for (int i = 0; i < oldList.size(); i++) {
			System.out.print(newElements.get(oldList.get(i)) + " ?->");						
			System.out.print(newList.add(newElements.get(oldList.get(i))) + ", ");
		}
		
		// sort the new list so the new items are in the same place as the old items
		for (int i = 0; i < oldList.size(); i++) {
			newList.move(i, (EObject) newElements.get(oldList.get(i)));
		}
		
		System.out.println("} => " + newList);
		
	}
	
	/**
	 * Update references to amalgamation units to point to their kernel rules
	 */
	private static void updateAmalgamationUnitReferences() {
		for (Entry<EObject, EObject> e : newElements.entrySet()) {
			if (newElements.containsKey(e.getValue())) {
				EObject ruleReplacingAmalgamationUnit = newElements.get(e.getValue());
				newElements.put(e.getKey(), ruleReplacingAmalgamationUnit);
			}
		}
	}
	
	/**
	 * Fill the old Element->new Element map (newElements) recursively.
	 * @param rootObject
	 */
	private static void fillMap(EObject rootObject) {
		EPackage henshinNew = HenshinPackageImpl.eINSTANCE;
		//EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2011/Henshin");
		
		
		for (EObject eo : rootObject.eContents()) {
		
			boolean detectedNC = false;
			
			String type = eo.eClass().getName();
			System.out.println(type);
		
			if ("AmalgamationUnit".equals(type)) {
				// Handle AmalgamationUnit
				EClass ec = (EClass) henshinNew.getEClassifier(type); 
		
				EObject amalgamationUnitKernelRule = (EObject) eo.eGet(getEReferenceByName("kernelRule", eo.eClass()));
				
				AmalgamationUnitHelper amu = new AmalgamationUnitHelper();
				amu.kernelRule = amalgamationUnitKernelRule;
				amu.amalgamationUnitName = (String) eo.eGet(getEAttributeForName("name", eo.eClass()));
				
				EList<EObject> amalgamationUnitMultiRules = (EList<EObject>) eo.eGet(getEReferenceByName("multiRules", eo.eClass()));
				for (Iterator iter = amalgamationUnitMultiRules.iterator(); iter.hasNext(); ) {
					EObject multiRule = (EObject) iter.next();
					amu.multiRules.add(multiRule);
				}
				
				EList<EObject> amalgamationUnitLhsMappings = (EList<EObject>) eo.eGet(getEReferenceByName("lhsMappings", eo.eClass()));
				for (Iterator iter = amalgamationUnitLhsMappings.iterator(); iter.hasNext(); ) {
					EObject lhsMapping = (EObject) iter.next();
					amu.lhsMappings.add(lhsMapping);
					
					Mapping newLhsMapping = HenshinFactory.eINSTANCE.createMapping();
					newElements.put(lhsMapping, newLhsMapping);
				}
				
				EList<EObject> amalgamationUnitRhsMappings = (EList<EObject>) eo.eGet(getEReferenceByName("rhsMappings", eo.eClass()));
				for (Iterator iter = amalgamationUnitRhsMappings.iterator(); iter.hasNext(); ) {
					EObject rhsMapping = (EObject) iter.next();
					amu.rhsMappings.add(rhsMapping);
					
					Mapping newRhsMapping = HenshinFactory.eINSTANCE.createMapping();
					newElements.put(rhsMapping, newRhsMapping);
				}
				
				amuList.add(amu);
				newElements.put(eo, amalgamationUnitKernelRule);
				
				continue;
			} else if ("NestedCondition".equals(type)) {
				// Handle NestedCondition
				// TODO: rewrite when model is correct?
				detectedNC = true;
			}
			
			

			
			EClass ec = (EClass) henshinNew.getEClassifier(type); 
			
			EObject newObject = HenshinFactoryImpl.eINSTANCE.create(ec);

			for (Iterator<EAttribute> iter = eo.eClass().getEAllAttributes().iterator(); iter.hasNext(); ) {
				EAttribute attr = (EAttribute) iter.next();
				Object val = eo.eGet(attr);
				
				EAttribute newAttr = getEAttributeForName(attr.getName(), ec);
				
				// handler for NACs, put them in a List (nacList) 
				if ((detectedNC) && (attr.getName().equals("negated"))) {
					// TODO: rewrite when model is correct?
					if (((Boolean) val) == true) {
						nacList.add((NestedCondition) newObject); 
					}
					continue;
				}
				
				// copy attributes
				
				if (newAttr != null) {
					newObject.eSet(newAttr, val);
				} else {
					System.err.println("deleted attribute: " + attr.getName());
				}
				
				System.out.println("OLD-> " + attr.getName() + " : " + val);
			}
			
			
			// TODO: remove when done, this just prints the new values
			for (Iterator<EAttribute> iter = newObject.eClass().getEAllAttributes().iterator(); iter.hasNext(); ) {
				EAttribute attr = (EAttribute) iter.next();
				Object val = newObject.eGet(attr);
				
				System.out.println("NEW-> " + attr.getName() + " : " + val);
			}
			
			System.out.println(newObject);
			newElements.put(eo, newObject);
			System.out.println(" --- ");
			
			fillMap(eo);			
		}

	}
	
	/**
	 * Add the root object to the newElements map
	 * @param eo
	 */
	private static void addRootObjectToMap(EObject eo) {
		EPackage henshinNew = HenshinPackageImpl.eINSTANCE;
		//.EPackage.Registry.INSTANCE.getEPackage("http://www.eclipse.org/emf/2011/Henshin");
		
			
		String type = eo.eClass().getName();
		System.out.println(type);
	
		if ("AmalgamationUnit".equals(type)) {
			return;
		}
		
	
		EClass ec = (EClass) henshinNew.getEClassifier(type); 
		
		EObject newObject = HenshinFactoryImpl.eINSTANCE.create(ec);

		for (Iterator<EAttribute> iter = eo.eClass().getEAllAttributes().iterator(); iter.hasNext(); ) {
			EAttribute attr = (EAttribute) iter.next();
			Object val = eo.eGet(attr);
			
			EAttribute newAttr = getEAttributeForName(attr.getName(), ec);
			if (newAttr != null) {
				newObject.eSet(newAttr, val);
			} else {
				System.err.println("deleted attribute: " + attr.getName());
			}
			
			System.out.println("OLD-> " + attr.getName() + " : " + val);
		}
		
		
		for (Iterator<EAttribute> iter = newObject.eClass().getEAllAttributes().iterator(); iter.hasNext(); ) {
			EAttribute attr = (EAttribute) iter.next();
			Object val = newObject.eGet(attr);
			
			System.out.println("NEW-> " + attr.getName() + " : " + val);
		}
		
		System.out.println(newObject);
		newElements.put(eo, newObject);
		System.out.println(" --- ");
		
		fillMap(eo);
	}
	

	/**
	 * Get the eClasse's EAttribute with the specified name 
	 * @param name
	 * @param ec
	 * @return
	 */
	private static EAttribute getEAttributeForName(String name, EClass ec) {
		for (Iterator<EAttribute> iter = ec.getEAllAttributes().iterator(); iter.hasNext(); ) {
			EAttribute attr = (EAttribute) iter.next();
			if (attr.getName().equals(name)) {
				return attr;
			}
		}
		return null;
	}
	
	/**
	 * Get the eClasse's EReference with the specified name
	 * @param name
	 * @param ec
	 * @return
	 */
	private static EReference getEReferenceByName(String name, EClass ec) {
		for (Iterator<EReference> iter = ec.getEAllReferences().iterator(); iter.hasNext(); ) {
			EReference eref = (EReference) iter.next();
			System.out.println("\t" + eref.getName());
			if (eref.getName().equals(name)) {
				return eref;
			}
		}
		return null;
	}
	
	
	/**
	 * Wrap NACs in NOT
	 */
	private static void wrapNACs() {
		System.out.println("\n\n --- wrap nacs ---");
		for (Entry<EObject, EObject> entry : newElements.entrySet()) {
			if (entry.getValue() instanceof Graph) {
				Formula nacCandidate = ((Graph) entry.getValue()).getFormula();
				if (nacCandidate instanceof NestedCondition) {
					// check if nac, then wrap in not
					if (nacList.contains(nacCandidate)) {	 // candidate is a NAC
						NestedCondition nac = (NestedCondition) nacCandidate;
						nacList.remove(nac);			// remove the nac from the nac list. 
														// If there are any problems, the list will not
														// be empty when the program is finished.
						Not nacNotWrapper = HenshinFactory.eINSTANCE.createNot(); 
						nacNotWrapper.setChild(nac);
						((Graph) entry.getValue()).setFormula(nacNotWrapper);
						
						createdElements.add(nacNotWrapper);
					} else {
						// candidate is a PAC, so do nothing
					}
				} else if (nacCandidate != null) {
					// otherwise, nac may be more deeply nested in formula; 
					// traverse formula to find out if nac is contained there,
					// and if so, wrap it
					System.out.println("traverse formula!" + nacCandidate);
					traverseFormula(nacCandidate);
				}
			}
		}
	}

	
	/**
	 * Traverse formulas recursively for searching for NACs.
	 * @param f
	 */
	private static void traverseFormula(Formula f) {
		System.out.println("... traversing formula " + f);
		if (f instanceof Not) {
			 if (((Not) f).getChild() instanceof NestedCondition) {
				 if (nacList.contains(((NestedCondition) ((Not) f).getChild()))) {
					 ((Not) f).setChild(wrapNac((NestedCondition) ((Not) f).getChild())); 
				 }
			 } else {
				 // it's a formula!
				 traverseFormula(((Not) f).getChild());
			 }
		} else if (f instanceof BinaryFormula) {
			BinaryFormula binF = (BinaryFormula) f;
			
			System.out.println(".. binary formula: <<" + binF.getLeft() + " ; " + binF.getRight() + ">>");
			
			if (binF.getLeft() instanceof NestedCondition) {
				if (nacList.contains((NestedCondition) binF.getLeft())) {
					binF.setLeft(wrapNac((NestedCondition) binF.getLeft()));
				}
			} else {
				// it's a formula!
				traverseFormula(binF.getLeft());
			}
			
			if (binF.getRight() instanceof NestedCondition) {
				if (nacList.contains((NestedCondition) binF.getRight())) {
					binF.setRight(wrapNac((NestedCondition) binF.getRight()));
				}
			} else {
				// it's a formula!
				traverseFormula(binF.getRight());
			}
			
		}
	}
	
	/*
	 * Wrap a NAC and return Not<>--NC
	 */
	private static Not wrapNac(NestedCondition nac) {
		nacList.remove(nac);
		Not nacNotWrapper = HenshinFactory.eINSTANCE.createNot();
		nacNotWrapper.setChild(nac);
		createdElements.add(nacNotWrapper);
		return nacNotWrapper;
	}
	
	
	/**
	 * Clean up double negations
	 */
	private static void cleanUpNotFormulas() {
		ArrayList<EObject> newObjectList = new ArrayList<EObject>();
		newObjectList.addAll(newElements.values());
		newObjectList.addAll(createdElements);
		
		for (EObject val : newObjectList) {
			if (val instanceof Not) {
				Not tempN = (Not) val;
				System.out.println("tempn container::::::: " + tempN.eContainer());
				if (tempN.getChild() instanceof Not) {
					if (tempN.eContainer() instanceof Graph) {
						// Graph<>---> Not<>---> Not<>---> Formula    ==>
						// Graph<>-----------------------> Formula
						Graph ncContainer = (Graph) tempN.eContainer();
						ncContainer.setFormula(((Not) tempN.getChild()).getChild());
					} else if (tempN.eContainer() instanceof BinaryFormula) {
						// BinaryFormula<>---> Not<>---> Not<>---> Formula    
						//              <>---> ?
						//                                                     ===>
						// BinaryFormula<>-----------------------> Formula
						//              <>---> ? 

						BinaryFormula binF = (BinaryFormula) tempN.eContainer();
						if ((binF.getLeft() != null) && (binF.getLeft().equals(tempN))) {
							binF.setLeft(((Not) tempN.getChild()).getChild());
						} else if ((binF.getRight() != null) && (binF.getRight().equals(tempN))) {
							binF.setRight(((Not) tempN.getChild()).getChild());
						}
					}
				}
			}
		}
	}
	
	
	private static void addError(String errorText) {
		errorList.add(errorText);
	}

	
}
