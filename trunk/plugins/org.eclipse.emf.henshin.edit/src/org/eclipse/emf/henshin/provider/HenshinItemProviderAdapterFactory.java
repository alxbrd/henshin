/**
 * <copyright>
 * Copyright (c) 2010-2012 Henshin developers. All rights reserved. 
 * This program and the accompanying materials are made available 
 * under the terms of the Eclipse Public License v1.0 which 
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * </copyright>
 */
package org.eclipse.emf.henshin.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.henshin.model.util.HenshinAdapterFactory;
import org.eclipse.emf.henshin.provider.filter.IFilterProvider;
import org.eclipse.emf.henshin.provider.util.IItemToolTipProvider;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc --> 
 * <!-- end-user-doc -->
 * @generated
 */
public class HenshinItemProviderAdapterFactory extends HenshinAdapterFactory implements
		ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;
	
	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();
	
	/**
	 * This keeps track of all the item providers created, so that they can be {@link #dispose disposed}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Disposable disposable = new Disposable();
	
	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();
	
	/**
	 * This constructs an instance. 
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public HenshinItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
		supportedTypes.add(IItemColorProvider.class);
		supportedTypes.add(IItemToolTipProvider.class);
		supportedTypes.add(IItemFontProvider.class);
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Module} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleItemProvider moduleItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Module}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createModuleAdapter() {
		if (moduleItemProvider == null) {
			moduleItemProvider = new ModuleItemProvider(this);
		}

		return moduleItemProvider;
	}

	public HenshinItemProviderAdapterFactory(IFilterProvider filterProvider) {
		this();
		this.filterProvider = filterProvider;
	}
	
	protected IFilterProvider filterProvider;
	
	public IFilterProvider getFilterProvider() {
		return filterProvider;
	}
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Rule}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRuleAdapter() {
		return new RuleItemProvider(this);
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.AttributeCondition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeConditionItemProvider attributeConditionItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.AttributeCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeConditionAdapter() {
		if (attributeConditionItemProvider == null) {
			attributeConditionItemProvider = new AttributeConditionItemProvider(this);
		}

		return attributeConditionItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Parameter} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterItemProvider parameterItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Parameter}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParameterAdapter() {
		if (parameterItemProvider == null) {
			parameterItemProvider = new ParameterItemProvider(this);
		}

		return parameterItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Graph} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphItemProvider graphItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Graph}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createGraphAdapter() {
		if (graphItemProvider == null) {
			graphItemProvider = new GraphItemProvider(this);
		}

		return graphItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Mapping} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MappingItemProvider mappingItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Mapping}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMappingAdapter() {
		if (mappingItemProvider == null) {
			mappingItemProvider = new MappingItemProvider(this);
		}

		return mappingItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Node} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeItemProvider nodeItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Node}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNodeAdapter() {
		if (nodeItemProvider == null) {
			nodeItemProvider = new NodeItemProvider(this);
		}

		return nodeItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Attribute} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeItemProvider attributeItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Attribute}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAttributeAdapter() {
		if (attributeItemProvider == null) {
			attributeItemProvider = new AttributeItemProvider(this);
		}

		return attributeItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Edge} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeItemProvider edgeItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Edge}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEdgeAdapter() {
		if (edgeItemProvider == null) {
			edgeItemProvider = new EdgeItemProvider(this);
		}

		return edgeItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.IndependentUnit} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IndependentUnitItemProvider independentUnitItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.IndependentUnit}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIndependentUnitAdapter() {
		if (independentUnitItemProvider == null) {
			independentUnitItemProvider = new IndependentUnitItemProvider(this);
		}

		return independentUnitItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.SequentialUnit} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SequentialUnitItemProvider sequentialUnitItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.SequentialUnit}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSequentialUnitAdapter() {
		if (sequentialUnitItemProvider == null) {
			sequentialUnitItemProvider = new SequentialUnitItemProvider(this);
		}

		return sequentialUnitItemProvider;
	}
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.ConditionalUnit}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createConditionalUnitAdapter() {
		return new ConditionalUnitItemProvider(this);
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.PriorityUnit} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PriorityUnitItemProvider priorityUnitItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.PriorityUnit}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPriorityUnitAdapter() {
		if (priorityUnitItemProvider == null) {
			priorityUnitItemProvider = new PriorityUnitItemProvider(this);
		}

		return priorityUnitItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.IteratedUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IteratedUnitItemProvider iteratedUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.IteratedUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIteratedUnitAdapter() {
		if (iteratedUnitItemProvider == null) {
			iteratedUnitItemProvider = new IteratedUnitItemProvider(this);
		}

		return iteratedUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.LoopUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LoopUnitItemProvider loopUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.LoopUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createLoopUnitAdapter() {
		if (loopUnitItemProvider == null) {
			loopUnitItemProvider = new LoopUnitItemProvider(this);
		}

		return loopUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.NestedCondition} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NestedConditionItemProvider nestedConditionItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.NestedCondition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNestedConditionAdapter() {
		if (nestedConditionItemProvider == null) {
			nestedConditionItemProvider = new NestedConditionItemProvider(this);
		}

		return nestedConditionItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.And} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AndItemProvider andItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.And}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAndAdapter() {
		if (andItemProvider == null) {
			andItemProvider = new AndItemProvider(this);
		}

		return andItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Or} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrItemProvider orItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Or}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOrAdapter() {
		if (orItemProvider == null) {
			orItemProvider = new OrItemProvider(this);
		}

		return orItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Not} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NotItemProvider notItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Not}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNotAdapter() {
		if (notItemProvider == null) {
			notItemProvider = new NotItemProvider(this);
		}

		return notItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.Xor} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XorItemProvider xorItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.Xor}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createXorAdapter() {
		if (xorItemProvider == null) {
			xorItemProvider = new XorItemProvider(this);
		}

		return xorItemProvider;
	}
	
	/**
	 * This keeps track of the one adapter used for all {@link org.eclipse.emf.henshin.model.ParameterMapping} instances.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterMappingItemProvider parameterMappingItemProvider;
	
	/**
	 * This creates an adapter for a {@link org.eclipse.emf.henshin.model.ParameterMapping}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createParameterMappingAdapter() {
		if (parameterMappingItemProvider == null) {
			parameterMappingItemProvider = new ParameterMappingItemProvider(this);
		}

		return parameterMappingItemProvider;
	}
	
	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}
	
	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}
	
	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}
	
	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}
	
	/**
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}
	
	/**
	 * Associates an adapter with a notifier via the base implementation, then records it to ensure it will be disposed.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void associate(Adapter adapter, Notifier target) {
		super.associate(adapter, target);
		if (adapter != null) {
			disposable.add(adapter);
		}
	}
	
	/**
	 * This adds a listener.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}
	
	/**
	 * This removes a listener.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}
	
	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}
	
	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc --> 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void disposeGen() {
		disposable.dispose();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.emf.edit.provider.IDisposable#dispose()
	 */
	@Override
	public void dispose() {
		try {
			disposeGen();
		} catch (Throwable t) {
			System.err.println(t + " occured while disposing item provider adapter factory");
		}
	}

}
