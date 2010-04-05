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
public class ObjectSubClass extends ObjectSuperClass {
	@Inject
	/* package */Integer Integer;

	private Object myObject;

	public int setObjectCalled = 0;
	public int setSubFinalized = 0;
	public int postConstructSetObjectCalled;
	public int postConstructSetOverriddenCalled;
	public int subPostConstructCount;
	public int subPreDestroyCount;
	public int overriddenPreDestroyCount;

	public ObjectSubClass() {
		super();
	}

	public void injectObjectViaMethod(Float f) {
		myObject = f;
		setObjectCalled++;
	}

	public void injectOverriddenMethod(Float f) {
		setOverriddenCalled++;
	}

	@Inject
	public void contextSet(IEclipseContext context) {
		super.contextSet(context);
		setSubFinalized++;
	}

	public Integer getInteger() {
		return Integer;
	}

	public Object getObjectViaMethod() {
		return myObject;
	}

	public int getFinalizedCount() {
		return setSubFinalized;
	}

	public int getOverriddenCount() {
		return setOverriddenCalled;
	}

	@PostConstruct
	public void subPostConstruct() {
		postConstructSetObjectCalled = setObjectCalled;
		postConstructSetStringCalled = setStringCalled;
		postConstructSetOverriddenCalled = setOverriddenCalled;
		subPostConstructCount++;
	}

	@PreDestroy
	public void subPreDestroy() {
		subPreDestroyCount++;
	}

	@PreDestroy
	public void overriddenPreDestroy() {
		overriddenPreDestroyCount++;
	}

}
