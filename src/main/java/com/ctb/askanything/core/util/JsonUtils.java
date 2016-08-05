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

package com.ctb.askanything.core.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utilities for JSON manipulation.
 *
 * @author Johnny Lim
 */
public abstract class JsonUtils {

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	private JsonUtils() {
	}

	public static String toJson(Object o) {
		try {
			return OBJECT_MAPPER.writeValueAsString(o);
		}
		catch (JsonProcessingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return OBJECT_MAPPER.readValue(json, clazz);
		}
		catch (IOException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

}
