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

package com.ctb.askanything.core.service;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.ctb.askanything.core.domain.Answer;
import com.ctb.askanything.core.domain.AnswerEngineOrders;
import com.ctb.askanything.core.domain.Question;

/**
 * Fallback answer engine.
 *
 * This is an answer engine questioning the original question.
 *
 * @author Johnny Lim
 */
@Service
@Order(AnswerEngineOrders.FALLBACK)
public class FallbackAnswerEngine implements AnswerEngine {

	private static final String TEMPLATE_ANSWER = "Are you asking \"{body}\"?";
	private static final String PLACEHOLDER_BODY = "{body}";

	@Override
	public Answer answer(Question question) {
		String answer = TEMPLATE_ANSWER.replace(PLACEHOLDER_BODY, question.getBody());
		return new Answer(answer);
	}

}
