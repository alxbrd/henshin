/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.emf.henshin.model.Action;
import org.eclipse.emf.henshin.model.And;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.AttributeCondition;
import org.eclipse.emf.henshin.model.BinaryFormula;
import org.eclipse.emf.henshin.model.ConditionalUnit;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Formula;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.GraphElement;
import org.eclipse.emf.henshin.model.HenshinFactory;
import org.eclipse.emf.henshin.model.HenshinPackage;
import org.eclipse.emf.henshin.model.IndependentUnit;
import org.eclipse.emf.henshin.model.IteratedUnit;
import org.eclipse.emf.henshin.model.LoopUnit;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.Module;
import org.eclipse.emf.henshin.model.MultiUnit;
import org.eclipse.emf.henshin.model.NamedElement;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Not;
import org.eclipse.emf.henshin.model.Or;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.ParameterMapping;
import org.eclipse.emf.henshin.model.PriorityUnit;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.SequentialUnit;
import org.eclipse.emf.henshin.model.UnaryFormula;
import org.eclipse.emf.henshin.model.UnaryUnit;
import org.eclipse.emf.henshin.model.Unit;
import org.eclipse.emf.henshin.model.Xor;
import org.eclipse.emf.henshin.model.util.HenshinValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinPackageImpl extends EPackageImpl implements HenshinPackage {
	
	/*
	 * Fake 'rules' feature ID for modules. Only to ensure backward compatibility.
	 */
	static final int FAKE_RULES_FEATURE_ID = MODULE_FEATURE_COUNT;
	
	/*
	 * Fake 'rules' feature for modules. Only to ensure backward compatibility.
	 */
	private EReference fakeRulesFeature;
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ruleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass moduleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mappingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass edgeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass independentUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequentialUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conditionalUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass priorityUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratedUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loopUnitEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nestedConditionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass formulaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unaryFormulaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass binaryFormulaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass andEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass notEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType actionEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass xorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterMappingEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.emf.henshin.model.HenshinPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private HenshinPackageImpl() {
		super(eNS_URI, HenshinFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link HenshinPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static HenshinPackage init() {
		if (isInited) return (HenshinPackage)EPackage.Registry.INSTANCE.getEPackage(HenshinPackage.eNS_URI);

		// Obtain or create and register package
		HenshinPackageImpl theHenshinPackage = (HenshinPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof HenshinPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new HenshinPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theHenshinPackage.createPackageContents();

		// Initialize created meta-data
		theHenshinPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theHenshinPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return HenshinValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theHenshinPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(HenshinPackage.eNS_URI, theHenshinPackage);
		return theHenshinPackage;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EPackageImpl#getEClassifier(java.lang.String)
	 */
	@Override
	public EClassifier getEClassifier(String name) {
		// Backward compatibility:
		if ("TransformationSystem".equals(name)) {
			return getModule();
		}
		return super.getEClassifier(name);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.impl.EPackageImpl#createEClass(int)
	 */
	@Override
	protected EClass createEClass(int id) {
		// Backward compatibility:
		if (id==MODULE) {
			EClassImpl c = new EClassImpl() {
				@Override
				public EStructuralFeature getEStructuralFeature(String name) {
					if ("transformationUnits".equals(name)) {
						return getModule_Units();
					}
					if ("rules".equals(name)) {
						return getFakeRulesFeature();
					}
					return super.getEStructuralFeature(name);
				}
			};
			c.setClassifierID(id);
			getEClassifiers().add(c);
			return c;
		}
		return super.createEClass(id);
	}

	/*
	 * Only for backward compatibility.
	 */
	EReference getFakeRulesFeature() {
		if (fakeRulesFeature==null) {
			fakeRulesFeature = EcoreFactory.eINSTANCE.createEReference();
			((EReferenceImpl) fakeRulesFeature).setName("rules");
			((EReferenceImpl) fakeRulesFeature).setFeatureID(FAKE_RULES_FEATURE_ID);
			((EReferenceImpl) fakeRulesFeature).setEType(getRule());
			((EReferenceImpl) fakeRulesFeature).setUpperBound(-1);
		}
		return fakeRulesFeature;		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getNamedElement_Description() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRule() {
		return ruleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Lhs() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Rhs() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_AttributeConditions() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_Mappings() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_CheckDangling() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRule_InjectiveMatching() {
		return (EAttribute)ruleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_MultiRules() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRule_MultiMappings() {
		return (EReference)ruleEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttributeCondition() {
		return attributeConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttributeCondition_Rule() {
		return (EReference)attributeConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttributeCondition_ConditionText() {
		return (EAttribute)attributeConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameter() {
		return parameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameter_Unit() {
		return (EReference)parameterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameter_Type() {
		return (EReference)parameterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraph() {
		return graphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraph_Nodes() {
		return (EReference)graphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraph_Edges() {
		return (EReference)graphEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGraph_Formula() {
		return (EReference)graphEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGraphElement() {
		return graphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getGraphElement_Action() {
		return (EAttribute)graphElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getModule() {
		return moduleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_SubModules() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_Imports() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_Units() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getModule_Instances() {
		return (EReference)moduleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnit() {
		return unitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnit_Parameters() {
		return (EReference)unitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnit_ParameterMappings() {
		return (EReference)unitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUnit_Activated() {
		return (EAttribute)unitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMapping() {
		return mappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapping_Origin() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMapping_Image() {
		return (EReference)mappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Type() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Attributes() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Graph() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Incoming() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_Outgoing() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNode_AllEdges() {
		return (EReference)nodeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_Type() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Value() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAttribute_Node() {
		return (EReference)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Constant() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Null() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEdge() {
		return edgeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Source() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Target() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Type() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getEdge_Graph() {
		return (EReference)edgeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryUnit() {
		return unaryUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryUnit_SubUnit() {
		return (EReference)unaryUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMultiUnit() {
		return multiUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getMultiUnit_SubUnits() {
		return (EReference)multiUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIndependentUnit() {
		return independentUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSequentialUnit() {
		return sequentialUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequentialUnit_Strict() {
		return (EAttribute)sequentialUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSequentialUnit_Rollback() {
		return (EAttribute)sequentialUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConditionalUnit() {
		return conditionalUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalUnit_If() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalUnit_Then() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConditionalUnit_Else() {
		return (EReference)conditionalUnitEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPriorityUnit() {
		return priorityUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIteratedUnit() {
		return iteratedUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIteratedUnit_Iterations() {
		return (EAttribute)iteratedUnitEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLoopUnit() {
		return loopUnitEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNestedCondition() {
		return nestedConditionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNestedCondition_Conclusion() {
		return (EReference)nestedConditionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNestedCondition_Mappings() {
		return (EReference)nestedConditionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFormula() {
		return formulaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnaryFormula() {
		return unaryFormulaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnaryFormula_Child() {
		return (EReference)unaryFormulaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getBinaryFormula() {
		return binaryFormulaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryFormula_Left() {
		return (EReference)binaryFormulaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getBinaryFormula_Right() {
		return (EReference)binaryFormulaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAnd() {
		return andEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getOr() {
		return orEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNot() {
		return notEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getAction() {
		return actionEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getXor() {
		return xorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getParameterMapping() {
		return parameterMappingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterMapping_Source() {
		return (EReference)parameterMappingEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getParameterMapping_Target() {
		return (EReference)parameterMappingEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HenshinFactory getHenshinFactory() {
		return (HenshinFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__DESCRIPTION);

		graphElementEClass = createEClass(GRAPH_ELEMENT);
		createEAttribute(graphElementEClass, GRAPH_ELEMENT__ACTION);

		moduleEClass = createEClass(MODULE);
		createEReference(moduleEClass, MODULE__SUB_MODULES);
		createEReference(moduleEClass, MODULE__IMPORTS);
		createEReference(moduleEClass, MODULE__UNITS);
		createEReference(moduleEClass, MODULE__INSTANCES);

		unitEClass = createEClass(UNIT);
		createEReference(unitEClass, UNIT__PARAMETERS);
		createEReference(unitEClass, UNIT__PARAMETER_MAPPINGS);
		createEAttribute(unitEClass, UNIT__ACTIVATED);

		ruleEClass = createEClass(RULE);
		createEReference(ruleEClass, RULE__LHS);
		createEReference(ruleEClass, RULE__RHS);
		createEReference(ruleEClass, RULE__ATTRIBUTE_CONDITIONS);
		createEReference(ruleEClass, RULE__MAPPINGS);
		createEAttribute(ruleEClass, RULE__CHECK_DANGLING);
		createEAttribute(ruleEClass, RULE__INJECTIVE_MATCHING);
		createEReference(ruleEClass, RULE__MULTI_RULES);
		createEReference(ruleEClass, RULE__MULTI_MAPPINGS);

		parameterEClass = createEClass(PARAMETER);
		createEReference(parameterEClass, PARAMETER__UNIT);
		createEReference(parameterEClass, PARAMETER__TYPE);

		parameterMappingEClass = createEClass(PARAMETER_MAPPING);
		createEReference(parameterMappingEClass, PARAMETER_MAPPING__SOURCE);
		createEReference(parameterMappingEClass, PARAMETER_MAPPING__TARGET);

		graphEClass = createEClass(GRAPH);
		createEReference(graphEClass, GRAPH__NODES);
		createEReference(graphEClass, GRAPH__EDGES);
		createEReference(graphEClass, GRAPH__FORMULA);

		nodeEClass = createEClass(NODE);
		createEReference(nodeEClass, NODE__TYPE);
		createEReference(nodeEClass, NODE__ATTRIBUTES);
		createEReference(nodeEClass, NODE__GRAPH);
		createEReference(nodeEClass, NODE__INCOMING);
		createEReference(nodeEClass, NODE__OUTGOING);
		createEReference(nodeEClass, NODE__ALL_EDGES);

		edgeEClass = createEClass(EDGE);
		createEReference(edgeEClass, EDGE__SOURCE);
		createEReference(edgeEClass, EDGE__TARGET);
		createEReference(edgeEClass, EDGE__TYPE);
		createEReference(edgeEClass, EDGE__GRAPH);

		attributeEClass = createEClass(ATTRIBUTE);
		createEReference(attributeEClass, ATTRIBUTE__TYPE);
		createEAttribute(attributeEClass, ATTRIBUTE__VALUE);
		createEReference(attributeEClass, ATTRIBUTE__NODE);
		createEAttribute(attributeEClass, ATTRIBUTE__CONSTANT);
		createEAttribute(attributeEClass, ATTRIBUTE__NULL);

		attributeConditionEClass = createEClass(ATTRIBUTE_CONDITION);
		createEReference(attributeConditionEClass, ATTRIBUTE_CONDITION__RULE);
		createEAttribute(attributeConditionEClass, ATTRIBUTE_CONDITION__CONDITION_TEXT);

		mappingEClass = createEClass(MAPPING);
		createEReference(mappingEClass, MAPPING__ORIGIN);
		createEReference(mappingEClass, MAPPING__IMAGE);

		unaryUnitEClass = createEClass(UNARY_UNIT);
		createEReference(unaryUnitEClass, UNARY_UNIT__SUB_UNIT);

		multiUnitEClass = createEClass(MULTI_UNIT);
		createEReference(multiUnitEClass, MULTI_UNIT__SUB_UNITS);

		independentUnitEClass = createEClass(INDEPENDENT_UNIT);

		sequentialUnitEClass = createEClass(SEQUENTIAL_UNIT);
		createEAttribute(sequentialUnitEClass, SEQUENTIAL_UNIT__STRICT);
		createEAttribute(sequentialUnitEClass, SEQUENTIAL_UNIT__ROLLBACK);

		conditionalUnitEClass = createEClass(CONDITIONAL_UNIT);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__IF);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__THEN);
		createEReference(conditionalUnitEClass, CONDITIONAL_UNIT__ELSE);

		priorityUnitEClass = createEClass(PRIORITY_UNIT);

		iteratedUnitEClass = createEClass(ITERATED_UNIT);
		createEAttribute(iteratedUnitEClass, ITERATED_UNIT__ITERATIONS);

		loopUnitEClass = createEClass(LOOP_UNIT);

		formulaEClass = createEClass(FORMULA);

		nestedConditionEClass = createEClass(NESTED_CONDITION);
		createEReference(nestedConditionEClass, NESTED_CONDITION__CONCLUSION);
		createEReference(nestedConditionEClass, NESTED_CONDITION__MAPPINGS);

		unaryFormulaEClass = createEClass(UNARY_FORMULA);
		createEReference(unaryFormulaEClass, UNARY_FORMULA__CHILD);

		binaryFormulaEClass = createEClass(BINARY_FORMULA);
		createEReference(binaryFormulaEClass, BINARY_FORMULA__LEFT);
		createEReference(binaryFormulaEClass, BINARY_FORMULA__RIGHT);

		andEClass = createEClass(AND);

		orEClass = createEClass(OR);

		xorEClass = createEClass(XOR);

		notEClass = createEClass(NOT);

		// Create data types
		actionEDataType = createEDataType(ACTION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		moduleEClass.getESuperTypes().add(this.getNamedElement());
		unitEClass.getESuperTypes().add(this.getNamedElement());
		ruleEClass.getESuperTypes().add(this.getUnit());
		parameterEClass.getESuperTypes().add(this.getNamedElement());
		graphEClass.getESuperTypes().add(this.getNamedElement());
		nodeEClass.getESuperTypes().add(this.getNamedElement());
		nodeEClass.getESuperTypes().add(this.getGraphElement());
		edgeEClass.getESuperTypes().add(this.getGraphElement());
		attributeEClass.getESuperTypes().add(this.getGraphElement());
		attributeConditionEClass.getESuperTypes().add(this.getNamedElement());
		unaryUnitEClass.getESuperTypes().add(this.getUnit());
		multiUnitEClass.getESuperTypes().add(this.getUnit());
		independentUnitEClass.getESuperTypes().add(this.getMultiUnit());
		sequentialUnitEClass.getESuperTypes().add(this.getMultiUnit());
		conditionalUnitEClass.getESuperTypes().add(this.getUnit());
		priorityUnitEClass.getESuperTypes().add(this.getMultiUnit());
		iteratedUnitEClass.getESuperTypes().add(this.getUnaryUnit());
		loopUnitEClass.getESuperTypes().add(this.getUnaryUnit());
		nestedConditionEClass.getESuperTypes().add(this.getFormula());
		unaryFormulaEClass.getESuperTypes().add(this.getFormula());
		binaryFormulaEClass.getESuperTypes().add(this.getFormula());
		andEClass.getESuperTypes().add(this.getBinaryFormula());
		orEClass.getESuperTypes().add(this.getBinaryFormula());
		xorEClass.getESuperTypes().add(this.getBinaryFormula());
		notEClass.getESuperTypes().add(this.getUnaryFormula());

		// Initialize classes and features; add operations and parameters
		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNamedElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphElementEClass, GraphElement.class, "GraphElement", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGraphElement_Action(), this.getAction(), "action", null, 0, 1, GraphElement.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(graphElementEClass, this.getGraph(), "getGraph", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(moduleEClass, Module.class, "Module", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getModule_SubModules(), this.getModule(), null, "subModules", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_Imports(), ecorePackage.getEPackage(), null, "imports", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_Units(), this.getUnit(), null, "units", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModule_Instances(), this.getGraph(), null, "instances", null, 0, -1, Module.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(moduleEClass, this.getUnit(), "getUnit", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(moduleEClass, this.getModule(), "getSubModule", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(unitEClass, Unit.class, "Unit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnit_Parameters(), this.getParameter(), this.getParameter_Unit(), "parameters", null, 0, -1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnit_ParameterMappings(), this.getParameterMapping(), null, "parameterMappings", null, 0, -1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnit_Activated(), ecorePackage.getEBoolean(), "activated", "true", 0, 1, Unit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(unitEClass, this.getModule(), "getModule", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(unitEClass, this.getUnit(), "getSubUnits", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "deep", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(unitEClass, this.getParameter(), "getParameter", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "parameter", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(ruleEClass, Rule.class, "Rule", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRule_Lhs(), this.getGraph(), null, "lhs", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_Rhs(), this.getGraph(), null, "rhs", null, 1, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_AttributeConditions(), this.getAttributeCondition(), this.getAttributeCondition_Rule(), "attributeConditions", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_CheckDangling(), ecorePackage.getEBoolean(), "checkDangling", "true", 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRule_InjectiveMatching(), ecorePackage.getEBoolean(), "injectiveMatching", "true", 0, 1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_MultiRules(), this.getRule(), null, "multiRules", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRule_MultiMappings(), this.getMapping(), null, "multiMappings", null, 0, -1, Rule.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(ruleEClass, this.getRule(), "getKernelRule", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(ruleEClass, this.getRule(), "getRootRule", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, this.getRule(), "getMultiRule", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(ruleEClass, this.getRule(), "getAllMultiRules", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, this.getNode(), "getActionNodes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAction(), "action", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, this.getEdge(), "getActionEdges", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAction(), "action", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(ruleEClass, ecorePackage.getEBoolean(), "isMultiRule", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, this.getNode(), "createNode", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, this.getEdge(), "createEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, ecorePackage.getEBoolean(), "canCreateEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "target", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, ecorePackage.getEBoolean(), "removeEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEdge(), "edge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "removeMapped", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(ruleEClass, ecorePackage.getEBoolean(), "removeNode", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEBoolean(), "removeMapped", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameter_Unit(), this.getUnit(), this.getUnit_Parameters(), "unit", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameter_Type(), ecorePackage.getEClassifier(), null, "type", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(parameterMappingEClass, ParameterMapping.class, "ParameterMapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getParameterMapping_Source(), this.getParameter(), null, "source", null, 1, 1, ParameterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getParameterMapping_Target(), this.getParameter(), null, "target", null, 1, 1, ParameterMapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(graphEClass, Graph.class, "Graph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGraph_Nodes(), this.getNode(), this.getNode_Graph(), "nodes", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraph_Edges(), this.getEdge(), this.getEdge_Graph(), "edges", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGraph_Formula(), this.getFormula(), null, "formula", null, 0, 1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(graphEClass, this.getRule(), "getRule", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getNode(), "getNodes", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, ecorePackage.getEClass(), "nodeType", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getEdge(), "getEdges", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "edgeType", 1, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(graphEClass, this.getNestedCondition(), "getNestedConditions", 0, -1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getNestedCondition(), "getPAC", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getNestedCondition(), "getNAC", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(graphEClass, ecorePackage.getEBoolean(), "isLhs", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(graphEClass, ecorePackage.getEBoolean(), "isRhs", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(graphEClass, ecorePackage.getEBoolean(), "isNestedCondition", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getNestedCondition(), "createPAC", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, this.getNestedCondition(), "createNAC", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "name", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, ecorePackage.getEBoolean(), "removeNode", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "node", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, ecorePackage.getEBoolean(), "removeEdge", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getEdge(), "edge", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(graphEClass, ecorePackage.getEBoolean(), "removeNestedCondition", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNestedCondition(), "nestedCondition", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNode_Type(), ecorePackage.getEClass(), null, "type", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Attributes(), this.getAttribute(), this.getAttribute_Node(), "attributes", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Graph(), this.getGraph(), this.getGraph_Nodes(), "graph", null, 0, 1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Incoming(), this.getEdge(), this.getEdge_Target(), "incoming", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_Outgoing(), this.getEdge(), this.getEdge_Source(), "outgoing", null, 0, -1, Node.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNode_AllEdges(), this.getEdge(), null, "allEdges", null, 0, -1, Node.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, !IS_ORDERED);

		op = addEOperation(nodeEClass, this.getEdge(), "getOutgoing", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nodeEClass, this.getEdge(), "getIncoming", 0, -1, IS_UNIQUE, !IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nodeEClass, this.getEdge(), "getOutgoing", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "target", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nodeEClass, this.getEdge(), "getIncoming", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEReference(), "type", 1, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getNode(), "source", 1, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nodeEClass, this.getAttribute(), "getAttribute", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEAttribute(), "type", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(nodeEClass, this.getAttribute(), "getActionAttributes", 0, -1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getAction(), "action", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nodeEClass, this.getNode(), "getActionNode", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(edgeEClass, Edge.class, "Edge", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEdge_Source(), this.getNode(), this.getNode_Outgoing(), "source", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Target(), this.getNode(), this.getNode_Incoming(), "target", null, 1, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Type(), ecorePackage.getEReference(), null, "type", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEdge_Graph(), this.getGraph(), this.getGraph_Edges(), "graph", null, 0, 1, Edge.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(edgeEClass, this.getEdge(), "getActionEdge", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttribute_Type(), ecorePackage.getEAttribute(), null, "type", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Value(), ecorePackage.getEString(), "value", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_Node(), this.getNode(), this.getNode_Attributes(), "node", null, 0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Constant(), ecorePackage.getEJavaObject(), "constant", "ACED0005740000", 0, 1, Attribute.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Null(), ecorePackage.getEBoolean(), "null", "false", 0, 1, Attribute.class, IS_TRANSIENT, !IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		addEOperation(attributeEClass, this.getAttribute(), "getActionAttribute", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(attributeConditionEClass, AttributeCondition.class, "AttributeCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttributeCondition_Rule(), this.getRule(), this.getRule_AttributeConditions(), "rule", null, 1, 1, AttributeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttributeCondition_ConditionText(), ecorePackage.getEString(), "conditionText", null, 0, 1, AttributeCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingEClass, Mapping.class, "Mapping", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMapping_Origin(), this.getNode(), null, "origin", null, 1, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMapping_Image(), this.getNode(), null, "image", null, 1, 1, Mapping.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unaryUnitEClass, UnaryUnit.class, "UnaryUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryUnit_SubUnit(), this.getUnit(), null, "subUnit", null, 0, 1, UnaryUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(multiUnitEClass, MultiUnit.class, "MultiUnit", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMultiUnit_SubUnits(), this.getUnit(), null, "subUnits", null, 0, -1, MultiUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(independentUnitEClass, IndependentUnit.class, "IndependentUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sequentialUnitEClass, SequentialUnit.class, "SequentialUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSequentialUnit_Strict(), ecorePackage.getEBoolean(), "strict", "true", 0, 1, SequentialUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSequentialUnit_Rollback(), ecorePackage.getEBoolean(), "rollback", "true", 0, 1, SequentialUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(conditionalUnitEClass, ConditionalUnit.class, "ConditionalUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConditionalUnit_If(), this.getUnit(), null, "if", null, 1, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionalUnit_Then(), this.getUnit(), null, "then", null, 1, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConditionalUnit_Else(), this.getUnit(), null, "else", null, 0, 1, ConditionalUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(priorityUnitEClass, PriorityUnit.class, "PriorityUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(iteratedUnitEClass, IteratedUnit.class, "IteratedUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIteratedUnit_Iterations(), ecorePackage.getEString(), "iterations", null, 0, 1, IteratedUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(loopUnitEClass, LoopUnit.class, "LoopUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(formulaEClass, Formula.class, "Formula", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		addEOperation(formulaEClass, ecorePackage.getEBoolean(), "isTrue", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(formulaEClass, ecorePackage.getEBoolean(), "isFalse", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(nestedConditionEClass, NestedCondition.class, "NestedCondition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNestedCondition_Conclusion(), this.getGraph(), null, "conclusion", null, 0, 1, NestedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNestedCondition_Mappings(), this.getMapping(), null, "mappings", null, 0, -1, NestedCondition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(nestedConditionEClass, this.getGraph(), "getHost", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nestedConditionEClass, ecorePackage.getEBoolean(), "isPAC", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(nestedConditionEClass, ecorePackage.getEBoolean(), "isNAC", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(unaryFormulaEClass, UnaryFormula.class, "UnaryFormula", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnaryFormula_Child(), this.getFormula(), null, "child", null, 1, 1, UnaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(binaryFormulaEClass, BinaryFormula.class, "BinaryFormula", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBinaryFormula_Left(), this.getFormula(), null, "left", null, 1, 1, BinaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBinaryFormula_Right(), this.getFormula(), null, "right", null, 1, 1, BinaryFormula.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(andEClass, And.class, "And", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(orEClass, Or.class, "Or", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(xorEClass, Xor.class, "Xor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(notEClass, Not.class, "Not", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(actionEDataType, Action.class, "Action", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
		// http://www.eclipse.org/emf/2010/Henshin/OCL
		createOCLAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";		
		addAnnotation
		  (namedElementEClass, 
		   source, 
		   new String[] {
			 "constraints", "ValidName"
		   });			
		addAnnotation
		  (moduleEClass, 
		   source, 
		   new String[] {
			 "constraints", "uniqueUnitNames noCyclicUnits parameterNamesNotTypeName"
		   });			
		addAnnotation
		  (unitEClass, 
		   source, 
		   new String[] {
			 "constraints", "uniqueParameterNames parameterMappingsPointToDirectSubUnit"
		   });			
		addAnnotation
		  (ruleEClass, 
		   source, 
		   new String[] {
			 "constraints", "lhsAndRhsNotNull mappingsFromLeft2Right createdNodesNotAbstract createdEdgesNotDerived deletedEdgesNotDerived"
		   });			
		addAnnotation
		  (parameterEClass, 
		   source, 
		   new String[] {
			 "constraints", "nameRequired"
		   });			
		addAnnotation
		  (graphEClass, 
		   source, 
		   new String[] {
			 "constraints", "uniqueNodeNames"
		   });			
		addAnnotation
		  (nodeEClass, 
		   source, 
		   new String[] {
			 "constraints", "uniqueAttributeTypes"
		   });			
		addAnnotation
		  (edgeEClass, 
		   source, 
		   new String[] {
			 "constraints", "equalParentGraphs"
		   });			
		addAnnotation
		  (mappingEClass, 
		   source, 
		   new String[] {
			 "constraints", "ruleMapping_TypeEquality"
		   });			
		addAnnotation
		  (nestedConditionEClass, 
		   source, 
		   new String[] {
			 "constraints", "mappingOriginContainedInParentCondition mappingImageContainedInCurrent"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2010/Henshin/OCL</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createOCLAnnotations() {
		String source = "http://www.eclipse.org/emf/2010/Henshin/OCL";			
		addAnnotation
		  (namedElementEClass, 
		   source, 
		   new String[] {
			 "ValidName", "not self.name.oclIsUndefined() implies self.name<>\'\'",
			 "ValidName.Msg", "_Ocl_Msg_NamedElement_ValidName",
			 "ValidName.Severity", "Warning"
		   });			
		addAnnotation
		  (moduleEClass, 
		   source, 
		   new String[] {
			 "uniqueUnitNames", "transformationUnits->forAll(unit1,unit2:TransformationUnit | unit1 <> unit2 implies unit1.name <> unit2.name)",
			 "uniqueUnitNames.Msg", "_Ocl_Msg_TransformationSystem_uniqueUnitNames"
		   });			
		addAnnotation
		  (unitEClass, 
		   source, 
		   new String[] {
			 "uniqueParameterNames", "parameters->forAll( param1, param2 : Parameter | param1 <> param2 implies param1.name <> param2.name)",
			 "uniqueParameterNames.Msg", "_Ocl_Msg_TransformationUnit_uniqueParameterNames"
		   });			
		addAnnotation
		  (ruleEClass, 
		   source, 
		   new String[] {
			 "lhsAndRhsNotNull", "not lhs->isEmpty() and not rhs->isEmpty()",
			 "lhsAndRhsNotNull.Msg", "_Ocl_Msg_Rule_lhsAndRhsNotNull",
			 "mappingsFromLeft2Right", "mappings->forAll(mapping : Mapping | \r\n\tlhs.nodes->includes(mapping.origin)\r\n\tand\r\n\trhs.nodes->includes(mapping.image)\r\n)",
			 "mappingsFromLeft2Right.Msg", "_Ocl_Msg_Rule_mappingsFromLeft2Right"
		   });			
		addAnnotation
		  (parameterEClass, 
		   source, 
		   new String[] {
			 "nameRequired", "not name.oclIsUndefined() and name.size() > 0",
			 "nameRequired.Msg", "_Ocl_Msg_Parameter_nameRequired"
		   });			
		addAnnotation
		  (graphEClass, 
		   source, 
		   new String[] {
			 "uniqueNodeNames", "nodes->forAll( node1, node2 : Node | (node1 <> node2 and not node1.name.oclIsUndefined() ) implies node1.name <> node2.name)",
			 "uniqueNodeNames.Msg", "_Ocl_Msg_Graph_uniqueNodeNames"
		   });			
		addAnnotation
		  (nodeEClass, 
		   source, 
		   new String[] {
			 "uniqueAttributeTypes", "attributes->forAll(attr1,attr2 : Attribute| attr1<>attr2 implies attr1.type <> attr2.type)",
			 "uniqueAttributeTypes.Msg", "_Ocl_Msg_Node_uniqueAttributeTypes"
		   });			
		addAnnotation
		  (edgeEClass, 
		   source, 
		   new String[] {
			 "equalParentGraphs", "source.graph=target.graph",
			 "equalParentGraphs.Msg", "_Ocl_Msg_Edge_equalParentGraphs"
		   });			
		addAnnotation
		  (mappingEClass, 
		   source, 
		   new String[] {
			 "ruleMapping_TypeEquality", "Rule.allInstances()->exists(rule : Rule | rule.mappings->includes(self)) implies origin.type = image.type",
			 "ruleMapping_TypeEquality.Msg", "_Ocl_Msg_Mapping_ruleMapping_TypeEquality"
		   });	
	}

} //HenshinPackageImpl
