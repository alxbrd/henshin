/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.model.exporters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.henshin.HenshinModelExporter;
import org.eclipse.emf.henshin.HenshinModelPlugin;
import org.eclipse.emf.henshin.model.Attribute;
import org.eclipse.emf.henshin.model.AttributeCondition;
import org.eclipse.emf.henshin.model.Edge;
import org.eclipse.emf.henshin.model.Graph;
import org.eclipse.emf.henshin.model.Mapping;
import org.eclipse.emf.henshin.model.MappingList;
import org.eclipse.emf.henshin.model.NestedCondition;
import org.eclipse.emf.henshin.model.Node;
import org.eclipse.emf.henshin.model.Parameter;
import org.eclipse.emf.henshin.model.Rule;
import org.eclipse.emf.henshin.model.TransformationSystem;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Henshin model exporter for AGG.
 * @author Christian Krause
 */
public class HenshinAGGExporter implements HenshinModelExporter {

	/**
	 * ID of this model exporter.
	 */
	public static final String EXPORTER_ID = "org.eclipse.emf.henshin.henshin2agg";

	// Ecore package:
	private static final EcorePackage ECORE = EcorePackage.eINSTANCE;
	
	// Colors for node types:
	private static int[][] COLORS = { 
		{   0,   0,   0 },  // black
		{ 255,   0,   0 },  // red
		{   0,   0, 255 },  // blue
		{ 128,   0, 128 },  // purple
		{ 128, 128,   0 },  // yellow
		{ 128, 128, 128 }	// grey
		// add more colors...
	};
	
	// XML document:
	private Document document;
	
	// Element ID:
	private int elementID = 0;
	
	// Color index:
	private int color = 0;
	
	// Node type IDs:
	private Map<EClass,String> nodeTypeIDs, nodeIDs;

	// Edge type IDs:
	private Map<EReference,String> edgeTypeIDs;

	// Attribute type IDs:
	private Map<EAttribute,String> attrTypeIDs;

	// Graph node IDs:
	private Map<Node,String> graphNodeIDs;
	
	// Graph edge IDs:
	private Map<Edge,String> graphEdgeIDs;
	
	// Warnings:
	private List<String> warnings;
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.HenshinModelExporter#doExport(org.eclipse.emf.henshin.model.TransformationSystem, org.eclipse.emf.common.util.URI)
	 */
	@Override
	public IStatus doExport(TransformationSystem system, URI uri) {

		// Reset first:
		reset();
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			
			// Root element:
			Element root = newElement("Document", document, false);
			root.setAttribute("version", "1.0");

			// Signature:
			Comment comment = document.createComment("Generated by Henshin on " + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
			root.appendChild(comment);

			// Graph transformation system:
			Element systemElem = newElement("GraphTransformationSystem", root, true);
			String name = system.getName();
			if (name==null || name.trim().length()==0) {
				if (system.eResource()!=null) {
					name = system.eResource().getURI().trimFileExtension().lastSegment();
				} else {
					name = "GraGra";
				}
			}
			systemElem.setAttribute("name", name);
			Element javaTag = newTaggedValue("AttrHandler", "Java Expr", systemElem);
			newTaggedValue("Package", "java.lang", javaTag);
			newTaggedValue("Package", "java.util", javaTag);
			newTaggedValue("Package", "com.objectspace.jgl", javaTag);
			newTaggedValue("CSP", "true", systemElem);
			newTaggedValue("injective", "true", systemElem);
			newTaggedValue("dangling", "true", systemElem);
			newTaggedValue("NACs", "true", systemElem);
			newTaggedValue("PACs", "true", systemElem);
			newTaggedValue("TypeGraphLevel", "ENABLED_MAX", systemElem);
			
			// Types:
			Element typesElem = newElement("Types", systemElem, false);
			
			// Type graph:
			Element typeGraphElem = newElement("Graph", typesElem, true);
			typeGraphElem.setAttribute("kind", "TG");
			typeGraphElem.setAttribute("name", "TypeGraph");
			typesElem.removeChild(typeGraphElem);
			
			// Nodes and attribute types:
			for (EPackage epackage : system.getImports()) {
				for (EClassifier eclassifier : epackage.getEClassifiers()) {
					if (eclassifier instanceof EClass) {
						EClass eclass = (EClass) eclassifier;
						
						// Node type:
						Element nodeTypeElem = newElement("NodeType", typesElem, true);
						nodeTypeElem.setAttribute("abstract", String.valueOf(eclass.isAbstract()));
						nodeTypeElem.setAttribute("name", eclass.getName() + "%:RECT:" + newColor() + ":[NODE]:");
						nodeTypeIDs.put(eclass, nodeTypeElem.getAttribute("ID"));
						
						// Node in type graph:
						Element nodeElem = newElement("Node", typeGraphElem, true);
						nodeElem.setAttribute("type", nodeTypeIDs.get(eclass));
						nodeIDs.put(eclass, nodeElem.getAttribute("ID"));
						
						// Attributes:
						for (EAttribute attribute : eclass.getEAttributes()) {
							if (isSupportedPrimitiveType(attribute.getEType())) {
								Element attrElem = newElement("AttrType", nodeTypeElem, true);
								attrElem.setAttribute("attrname", attribute.getName());
								attrElem.setAttribute("typename", getPrimitiveType(attribute.getEType()));
								attrElem.setAttribute("visible", "true");
								attrTypeIDs.put(attribute, attrElem.getAttribute("ID"));
							} else {
								warnings.add(" - Attribute " + eclass.getName() + "." + attribute.getName() + 
										" of type " + attribute.getEAttributeType().getName() + " not supported");
							}
						}
					}
				}
			}

			// Check whether the reference names are unique:
			boolean hasUniqureRefNames = hasUniqueEReferenceNames(system);
			
			// Edge types:
			for (EPackage epackage : system.getImports()) {
				for (EClassifier eclassifier : epackage.getEClassifiers()) {
					if (eclassifier instanceof EClass) {
						EClass eclass = (EClass) eclassifier;
						
						// Edges:
						for (EReference reference : eclass.getEReferences()) {
							
							// Edge type:
							Element edgeTypeElem = newElement("EdgeType", typesElem, true);
							edgeTypeElem.setAttribute("abstract", "false");
							String refName = hasUniqureRefNames ? reference.getName() : getUniqueReferenceName(reference);
							edgeTypeElem.setAttribute("name", refName + "%:SOLID_LINE:java.awt.Color[r=0,g=0,b=0]:[EDGE]:");
							edgeTypeIDs.put(reference, edgeTypeElem.getAttribute("ID"));
							
							// Edge in type graph:
							Element edgeElem = newElement("Edge", typeGraphElem, true);
							edgeElem.setAttribute("type", edgeTypeIDs.get(reference));
							edgeElem.setAttribute("source", nodeIDs.get(eclass));
							edgeElem.setAttribute("target", nodeIDs.get(reference.getEReferenceType()));
							edgeElem.setAttribute("sourcemin", "0");
							edgeElem.setAttribute("targetmin", reference.getLowerBound()+"");
							if (reference.getUpperBound()>=0) {
								edgeElem.setAttribute("targetmax", reference.getUpperBound()+"");
							}
						}
					}
				}
			}

			// Now append the type graph:
			typesElem.appendChild(typeGraphElem);
			
			// Rules:
			for (Rule rule : system.getRules()) {
				Element ruleElem = newElement("Rule", systemElem, true);
				ruleElem.setAttribute("name", rule.getName());
				ruleElem.setAttribute("formula", "true");
				
				// Parameters:
				for (Parameter param : rule.getParameters()) {
					if (isSupportedPrimitiveType(param.getType())) {
						Element paramElem = newElement("Parameter", ruleElem, false);
						paramElem.setAttribute("name", param.getName());
						paramElem.setAttribute("type", getPrimitiveType(param.getType()));
					} else {
						String type = param.getType()!=null ? param.getType().getName() : "null";
						warnings.add(" - Parameter " + rule.getName() + "." + param.getName() + " of type " + type + " not supported");
					}
				}
				
				// LHS, RHS and morphism:
				convertGraph(rule.getLhs(), ruleElem, "LHS", "Left");
				convertGraph(rule.getRhs(), ruleElem, "RHS", "Right");	
				convertMorphism(rule.getName(), rule.getMappings(), rule.getLhs(), rule.getRhs(), ruleElem);
		
				// Application conditions:
				Element applCondElem = newElement("ApplCondition", ruleElem, false);
				
				// Attribute conditions:
				if (!rule.getAttributeConditions().isEmpty()) {
					Element attrCondElem = newElement("AttrCondition", applCondElem, false);
					for (AttributeCondition cond : rule.getAttributeConditions()) {
						Element condElem = newElement("Condition", attrCondElem, false);
						Element valueElem = newElement("Value", condElem, false);
						Element javaElem = newElement("java", valueElem, false);
						javaElem.setAttribute("class", "java.beans.XMLDecoder");
						javaElem.setAttribute("version", "1.6.0_03");
						Element stringElem = newElement("string", javaElem, false);
						stringElem.setTextContent(cond.getConditionText());
					}
				}
				
				// PACs and NACs:
				for (NestedCondition nested : rule.getAllNestedConditions()) {
					if (nested.isNAC()) {
						Element nacElem = newElement("NAC", applCondElem, false);
						convertGraph(nested.getConclusion(), nacElem, "NAC", "Graph");
						convertMorphism(nested.getConclusion().getName(), nested.getMappings(), rule.getLhs(), nested.getConclusion(), nacElem);
					}
					else if (nested.isPAC()) {
						Element pacElem = newElement("PAC", applCondElem, false);
						convertGraph(nested.getConclusion(), pacElem, "PAC", "Graph");
						convertMorphism(nested.getConclusion().getName(), nested.getMappings(), rule.getLhs(), nested.getConclusion(), pacElem);
					}
				}
			}
			
			// Save the XML file:
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer trans = transFactory.newTransformer();
			trans.setOutputProperty(OutputKeys.INDENT, "yes");
			File file = new File(uri.toFileString());
			StreamResult result = new StreamResult(file);
			DOMSource source = new DOMSource(document);
			trans.transform(source, result);			
		}
		catch (Throwable t) {
			reset();
			return new Status(IStatus.ERROR, HenshinModelPlugin.PLUGIN_ID, "Error exporting to AGG", t);
		}
		if (!warnings.isEmpty()) {
			String message = "Warning:\n";
			for (String warning : warnings) {
				message = message + "\n" + warning;
			}
			reset();
			return new Status(IStatus.WARNING, HenshinModelPlugin.PLUGIN_ID, message);
		}
		reset();
		return Status.OK_STATUS;
	}

	/*
	 * Translate a graph into XML.
	 */
	private void convertGraph(Graph graph, Element parent, String kind, String name) {
		Element graphElem = newElement("Graph", parent, true);
		graphElem.setAttribute("kind", kind);
		graphElem.setAttribute("name", name);
		
		// Nodes:
		for (Node node : graph.getNodes()) {
			Element nodeElem = newElement("Node", graphElem, true);
			nodeElem.setAttribute("type", nodeTypeIDs.get(node.getType()));
			graphNodeIDs.put(node, nodeElem.getAttribute("ID"));
			
			// Attributes:
			for (Attribute attribute : node.getAttributes()) {
				if (isSupportedPrimitiveType(attribute.getType().getEType())) {
					EDataType t = attribute.getType().getEAttributeType();
					Element attrElem = newElement("Attribute", nodeElem, false);
					attrElem.setAttribute("type", attrTypeIDs.get(attribute.getType()));
					
					String constValue = getConstant(attribute);
					Element valueElem = null;
					if (constValue!=null) {
						attrElem.setAttribute("constant", "true");
						if (t==ECORE.getEInt()) {		// integers
							valueElem = newElement("Value", attrElem, false);
							Element intElem = newElement("int", valueElem, false);
							intElem.setTextContent(attribute.getValue());
						}
						if (t==ECORE.getEDouble()) {		// integers
							valueElem = newElement("Value", attrElem, false);
							Element doubleElem = newElement("double", valueElem, false);
							doubleElem.setTextContent(attribute.getValue());
						}
						else if (t==ECORE.getEBoolean()) {	// booleans
							valueElem = newElement("Value", attrElem, false);
							Element booleanElem = newElement("boolean", valueElem, false);
							booleanElem.setTextContent(attribute.getValue());
						}
					}
					if (valueElem==null) {  // string and variables
						attrElem.setAttribute("variable", "true");
						valueElem = newElement("Value", attrElem, false);
						Element stringElem = newElement("string", valueElem, false);
						stringElem.setTextContent(constValue!=null ? constValue : attribute.getValue());
					}
				}
			}
		}
		
		// Edges:
		for (Edge edge : graph.getEdges()) {
			Element edgeElem = newElement("Edge", graphElem, true);
			graphEdgeIDs.put(edge, edgeElem.getAttribute("ID"));
			edgeElem.setAttribute("type", edgeTypeIDs.get(edge.getType()));
			edgeElem.setAttribute("source", graphNodeIDs.get(edge.getSource()));
			edgeElem.setAttribute("target", graphNodeIDs.get(edge.getTarget()));
		}
		
	}

	/*
	 * Convert a morphism.
	 */
	private void convertMorphism(String name, MappingList mappings, Graph source, Graph target, Element parent) {
		Element morphismElem = newElement("Morphism", parent, false);
		morphismElem.setAttribute("name", name);
		
		// Node mappings:
		for (Mapping mapping : mappings) {
			if (mapping.getImage().getGraph()==target) {
				Element mappingElem = newElement("Mapping", morphismElem, false);
				mappingElem.setAttribute("orig", graphNodeIDs.get(mapping.getOrigin()));  // "orig" (not "origin")
				mappingElem.setAttribute("image", graphNodeIDs.get(mapping.getImage()));
			}
		}
		
		// Edge mappings:
		for (Edge edge : source.getEdges()) {
			Edge image = mappings.getImage(edge, target);
			if (image!=null) {
				Element mappingElem = newElement("Mapping", morphismElem, false);
				mappingElem.setAttribute("orig", graphEdgeIDs.get(edge));   // "orig" (not "origin")
				mappingElem.setAttribute("image", graphEdgeIDs.get(image));				
			}
		}
		
	}
	
	/*
	 * Get the name of a reference.
	 */
	private String getUniqueReferenceName(EReference reference) {
		String srcName = ((EClass) reference.eContainer()).getName();
		srcName = Character.toLowerCase(srcName.charAt(0)) + srcName.substring(1);
		String refName = reference.getName();
		refName = Character.toUpperCase(refName.charAt(0)) + refName.substring(1);
		return srcName + refName;
	}
	
	/*
	 * Create a new document element.
	 */
	private Element newElement(String type, org.w3c.dom.Node parent, boolean generateID) {
		Element elem = document.createElement(type);
		if (generateID) {
			elem.setAttribute("ID", "I" + (elementID++));
			elem.setIdAttribute("ID", true);
		}
		parent.appendChild(elem);
		return elem;
	}
	
	/*
	 * Create a new color (string representation for AGG).
	 */
	private String newColor() {
		int[] rgb = COLORS[color];
		color = (color+1) % COLORS.length;
		return "java.awt.Color[r=" + rgb[0] + ",g=" + rgb[1] + ",b=" + rgb[2] + "]";
	}

	/*
	 * Create a new tagged value.
	 */
	private Element newTaggedValue(String tag, String tagValue, org.w3c.dom.Node parent) {
		Element elem = document.createElement("TaggedValue");
		elem.setAttribute("Tag", tag);
		elem.setAttribute("TagValue", tagValue);		
		parent.appendChild(elem);
		return elem;
	}

	/*
	 * Reset internal data.
	 */
	private void reset() {
		nodeTypeIDs = new HashMap<EClass, String>();
		nodeIDs = new HashMap<EClass, String>();
		edgeTypeIDs = new HashMap<EReference, String>();
		attrTypeIDs = new HashMap<EAttribute, String>();
		graphNodeIDs = new HashMap<Node, String>();
		graphEdgeIDs = new HashMap<Edge, String>();
		warnings = new ArrayList<String>();
		elementID = 0;
		color = 0;
	}
	
	/*
	 * Check whether a type is a supported primitive type.
	 */
	private boolean isSupportedPrimitiveType(EClassifier type) {
		return (type==ECORE.getEInt() || type==ECORE.getEDouble() || 
				type==ECORE.getEBoolean() || type==ECORE.getEString());
	}
	
	/*
	 * Get the string representation of a primitive type.
	 */
	private String getPrimitiveType(EClassifier type) {
		if (isSupportedPrimitiveType(type)) {
			if (type==ECORE.getEString()) {
				return "String";
			}
			return type.getInstanceClassName();			
		}
		return "null";
	}
		
	/*
	 * Check whether an attribute has an constant value.
	 * Returns the string representation of the constant
	 * if yes. Otherwise null.
	 */
	private String getConstant(Attribute attribute) {
		String val = String.valueOf(attribute.getValue()).trim();
		EDataType type = attribute.getType().getEAttributeType();
		if (type==ECORE.getEInt()) {
			try {
				int intVal = Integer.parseInt(val);
				return String.valueOf(intVal);
			} catch (Throwable t) {
				return null;
			}
		}
		if (type==ECORE.getEDouble()) {
			try {
				double doubleVal = Double.parseDouble(val);
				return String.valueOf(doubleVal);
			} catch (Throwable t) {
				return null;
			}
		}
		if (type==ECORE.getEBoolean()) {
			try {
				boolean boolVal = Boolean.parseBoolean(val);
				return String.valueOf(boolVal);
			} catch (Throwable t) {
				return null;
			}
		}
		if (type==ECORE.getEString()) {
			if ((val.startsWith("\"") && val.endsWith("\"")) ||
				(val.startsWith("'") && val.endsWith("'"))) {
				return val.substring(1, val.length()-1);
			} else {
				return null;
			}
		}
		return null;
	}
	
	/*
	 * Check whether all used EReferences in a transformation systems have a unique name.
	 */
	private static boolean hasUniqueEReferenceNames(TransformationSystem system) {
		Set<String> refNames = new HashSet<String>(); 
		for (EPackage epackage : system.getImports()) {
			for (EClassifier classifier : epackage.getEClassifiers()) {
				if (classifier instanceof EClass) {
					for (EReference ref : ((EClass) classifier).getEReferences()) {
						if (refNames.contains(ref.getName())) return false;
						refNames.add(ref.getName());
					}
				}
			}
		}
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.HenshinModelExporter#getExporterName()
	 */
	@Override
	public String getExporterName() {
		return "AGG";
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.henshin.HenshinModelExporter#getExportFileExtensions()
	 */
	@Override
	public String[] getExportFileExtensions() {
		return new String[] { "ggx" };
	}

}
