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

import java.util.Date;
import java.util.UUID;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Answer container.
 *
 * @author Johnny Lim
 */
@Data
@NoArgsConstructor
@Document(indexName = "answer")
public class Answer {

	/**
	 * Answer object representing that an answer engine can't answer for the question.
	 */
	public static final Answer NOT_AVAILABLE = new Answer();

	private String id;
	@Field(type = FieldType.Date)
	private Date timestamp;
	private Question question;
	private String body;

	public Answer(Question question, String body) {
		this.id = UUID.randomUUID().toString();
		this.timestamp = new Date();
		this.question = question;
		this.body = body;
	}

}
