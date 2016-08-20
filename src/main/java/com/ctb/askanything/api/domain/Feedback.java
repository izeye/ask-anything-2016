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

package com.ctb.askanything.api.domain;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Feedback.
 *
 * @author Johnny Lim
 */
@Data
@NoArgsConstructor
public class Feedback {

	/**
	 * Feedback instance indicating that there's no feedback.
	 */
	public static final Feedback NOT_AVAILABLE = new Feedback();

	private String id;
	@Field(type = FieldType.Date)
	private Date timestamp;
	private String body;
	private String requestIpAddress;

	public Feedback(String body, String requestIpAddress) {
		this.id = UUID.randomUUID().toString();
		this.timestamp = new Date();
		this.body = body;
		this.requestIpAddress = requestIpAddress;
	}

}
