/*
 * Copyright 2016 the original author or authors.
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

package com.ctb.askanything.core.domain;

import org.springframework.core.Ordered;

/**
 * Order constants for answer engines.
 *
 * @author Johnny Lim
 */
public abstract class AnswerEngineOrders {

	/**
	 * Order for my IP address answer engine.
	 */
	public static final int MY_IP_ADDRESS = 1;

	/**
	 * Order for the fallback answer engine.
	 */
	public static final int FALLBACK = Ordered.LOWEST_PRECEDENCE;

	private AnswerEngineOrders() {
	}

}
