/*******************************************************************************
 * Copyright (c) 2010 CWI Amsterdam, Technical University Berlin, 
 * Philipps-University Marburg and others. All rights reserved. 
 * This program and the accompanying materials are made 
 * available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Technical University Berlin - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.henshin.model.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * Resource implementation for Henshin resources.
 * @author Christian Krause
 */
public class HenshinResource extends XMIResourceImpl {
	
	/**
	 * Default constructor.
	 */
	public HenshinResource() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param uri URI of a Henshin resource.
	 */
	public HenshinResource(URI uri) {
		super(uri);
	}

	/* 
	 * (non-Javadoc)
	 * @see org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl#useUUIDs()
	 */
	@Override
	protected boolean useUUIDs() {
		return true;
	}
	
}
