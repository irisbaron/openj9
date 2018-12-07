/*[INCLUDE-IF Sidecar16]*/
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

import com.ibm.lang.management.CpuLoadCalculationConstants;

final class CpuUtilizationHelper implements CpuLoadCalculationConstants {

	SysinfoCpuLoad cpuLoadObj = null;
	

	/**
	 * Returns the CPU utilization, i.e. fraction of the time spent in user or system/privileged mode, since the last call to getSystemCpuLoad() on this object.  
	 * To compute load over different concurrent intervals, create separate objects.
	 * If insufficient time (less than 10 ms) has elapsed since a previous call on this object, or
	 * if an operating system error has occurred, e.g. clock rollover, ERROR_VALUE is returned.
	 * If this operation is not supported due to insufficient user privilege, return INSUFFICIENT_PRIVILEGE. 
	 * If this operation is not supported on this platform, return UNSUPPORTED_VALUE. 
	 * INTERNAL_ERROR indicates an internal problem.
	 * This is not guaranteed to return the same value as reported by operating system
	 * utilities such as Unix "top" or Windows task manager.
	 * @return value between 0 and 1.0, or a negative value in the case of an error.
	 */
	synchronized double getSystemCpuLoad() {
		double cpuLoad = ERROR_VALUE;

		cpuLoadObj = SysinfoCpuTime.getCpuLoadImpl();
		/* the native had problems creating the object */
		if (null == cpuLoadObj) {
			return INTERNAL_ERROR;
		}

		/* not supported on this platform or user does not have sufficient rights */
		int status = cpuLoadObj.getStatus();
		if (status < 0) {
			return status;
		}

        
		return cpuLoadObj.cpuLoad;
	}


}
