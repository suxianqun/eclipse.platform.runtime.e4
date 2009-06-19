/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.e4.core.services.context;

import org.eclipse.e4.core.services.context.spi.IContextConstants;
import org.eclipse.e4.core.services.context.spi.IEclipseContextStrategy;
import org.eclipse.e4.core.services.context.spi.ILookupStrategy;
import org.eclipse.e4.core.services.internal.context.EclipseContext;
import org.eclipse.e4.internal.core.services.osgi.OSGiContextStrategy;
import org.osgi.framework.BundleContext;

/**
 * A factory for creating a simple context instance. Simple contexts must be
 * filled in programmatically by calling
 * {@link IEclipseContext#set(String, Object)} to provide context values, or by
 * providing an {@link ILookupStrategy} to be used to initialize values not
 * currently defined in the context.
 */
public final class EclipseContextFactory {

	/**
	 * Creates and returns a new empty context with no parent, using the default
	 * context strategy.
	 * 
	 * @return A new empty context with no parent context.
	 */
	static public IEclipseContext create() {
		return new EclipseContext(null, null);
	}

	/**
	 * Creates and returns a new empty context with the given parent and
	 * strategy.
	 * 
	 * @param parent
	 *            The context parent to delegate lookup of values not defined in
	 *            the returned context.
	 * @param strategy
	 *            The context strategy to use in this context
	 * @return A new empty context with the given parent and strategy
	 */
	static public IEclipseContext create(IEclipseContext parent, IEclipseContextStrategy strategy) {
		return new EclipseContext(parent, strategy);
	}

	/**
	 * Returns a context that can be used to lookup OSGi services.
	 * 
	 * @param bundleContext
	 *            The bundle context to use for service lookup
	 * @return A context containing all OSGi services
	 */
	public static IEclipseContext createServiceContext(BundleContext bundleContext) {
		IEclipseContext result = new EclipseContext(null, new OSGiContextStrategy(bundleContext));
		result.set(IContextConstants.DEBUG_STRING,
				"OSGi context for bundle: " + bundleContext.getBundle().getSymbolicName()); //$NON-NLS-1$
		return result;
	}
}