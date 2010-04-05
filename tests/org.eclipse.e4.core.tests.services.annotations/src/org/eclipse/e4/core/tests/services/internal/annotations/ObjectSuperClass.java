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
package org.eclipse.e4.core.tests.services.internal.annotations;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.PostConstruct;
import org.eclipse.e4.core.di.annotations.PreDestroy;

/**
 * Test class to check injection mechanism into classes with inheritance
 */
public class ObjectSuperClass {

	protected IEclipseContext context;
	@Inject
	private String String;
	private String myString;

	public int postConstructSetStringCalled;
	public int setFinalizedCalled = 0;
	public int setStringCalled = 0;
	public int superPostConstructCount;
	public int superPreDestroyCount;
	public int setOverriddenCalled = 0;

	public ObjectSuperClass() {
		// placeholder
	}

	@Inject
	public void contextSet(IEclipseContext context) {
		this.context = context;
		setFinalizedCalled++;
	}

	public IEclipseContext getContext() {
		return context;
	}

	public int getFinalizedCalled() {
		return setFinalizedCalled;
	}

	public String getString() {
		return String;
	}

	public String getStringViaMethod() {
		return myString;
	}

	public void injectOverriddenMethod(Float f) {
		setOverriddenCalled++;
	}

	public void injectStringViaMethod(String string) {
		myString = string;
		setStringCalled++;
	}

	@PostConstruct
	public void superPostConstruct() {
		// record setter invocation counts at time of post construct invocation
		postConstructSetStringCalled = setStringCalled;
		superPostConstructCount++;
	}

	@PreDestroy
	public void superPreDestroy() {
		superPreDestroyCount++;
	}

	@PreDestroy()
	public void overriddenPreDestroy() {
		//
	}

}
