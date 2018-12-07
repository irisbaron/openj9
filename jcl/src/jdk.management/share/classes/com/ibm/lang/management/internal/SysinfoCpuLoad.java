//*[INCLUDE-IF Sidecar16]*/
/*******************************************************************************
 * Copyright (c) 2001, 2016 IBM Corp. and others
 *
 * This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License 2.0 which accompanies this
 * distribution and is available at https://www.eclipse.org/legal/epl-2.0/
 * or the Apache License, Version 2.0 which accompanies this distribution and
 * is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * This Source Code may also be made available under the following
 * Secondary Licenses when the conditions for such availability set
 * forth in the Eclipse Public License, v. 2.0 are satisfied: GNU
 * General Public License, version 2 with the GNU Classpath
 * Exception [1] and GNU General Public License, version 2 with the
 * OpenJDK Assembly Exception [2].
 *
 * [1] https://www.gnu.org/software/classpath/license.html
 * [2] http://openjdk.java.net/legal/assembly-exception.html
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0 OR GPL-2.0 WITH Classpath-exception-2.0 OR LicenseRef-GPL-2.0 WITH Assembly-exception
 *******************************************************************************/
package com.ibm.lang.management.internal;

final class SysinfoCpuLoad {

	private double cpuLoad; /* in percentage  */
	private int status;

	/**
	 * @param cpuLoad cpu utilization in percentage between 0 and 1.
	 * @param status status as reported by the JNI native
	 */
	SysinfoCpuLoad(long cpuLoad, int status) {
		super();
		this.cpuLoad = cpuLoad;
		this.status = status;
	}
	
	/**
	 * calls the constructor for this class and initializes the fields
	 * @return null if the JNI code could not look up the class or constructor.
	 */
	static native SysinfoCpuLoad getCpuLoadImpl();

	/**
	 * @see com.ibm.lang.management.internal.CpuUtilizationHelper for error code values
	 * @return status of the JNI native.
	 */
	int getStatus() {
		return status;
	}

	/**
	 * @return cumulative CPU load in percentage between 0 and 1
	 */
	double getCpuLoad() {
		return cpuLoad;
	}

    
}
