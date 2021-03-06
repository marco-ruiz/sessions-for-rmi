/*
 * Copyright 2007-2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bop.srmi.test;

import java.sql.Timestamp;

import org.bop.srmi.exception.SRMIException;

/**
 * @author Marco Ruiz
 * @since Nov 5, 2008
 */
public class SessionTestImpl implements SessionTest {

	private int requestCount = 0;
	private String creationTime = new Timestamp(System.currentTimeMillis()).toString();
	
	private Integer id = 0;

	public SessionTestImpl(Integer id) {
		this.id = id;
		output("CREATING '" + id + "'");
	}

	public Integer getId() {
    	return id;
    }

	public String getTime() throws SRMIException {
		long result = System.currentTimeMillis();
		return output(new Timestamp(result).toString());
	}

	public String divideSessionIdBy(int divisor) throws SRMIException, ArithmeticException {
		if (divisor < 0) throw new ArithmeticException();
		float result = ((Integer)getId()).floatValue() / (float)divisor;
		return output(getId() + " / " + divisor + " = " + result);
	}
	
	public void onDestroy() {
		output("DESTROYING '" + id + "'");
	}

	public void onJolt() {
		output("JOLTING '" + id + "'");
    }

	public String output(Object result) {
		requestCount++;
		String resultStr = "[" + requestCount + " - " + getId() + " @ " + creationTime + " - " + new Timestamp(System.currentTimeMillis()) + "] Remote: " + result;
		System.out.println("Server Side: " + resultStr);
		return resultStr;
	}

	public void onDecay() {
    }
}

