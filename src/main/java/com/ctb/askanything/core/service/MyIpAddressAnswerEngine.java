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
 * Answer engine for my IP address.
 *
 * @author Johnny Lim
 */
@Service
@Order(AnswerEngineOrders.MY_IP_ADDRESS)
public class MyIpAddressAnswerEngine implements AnswerEngine {

	private static final String MY_IP_ADDRESS_QUESTION_SIGNATURE = "my ip";

	private static final String TEMPLATE_ANSWER = "Your IP address is \"{requestIpAddress}\".";
	private static final String PLACEHOLDER_REQUEST_IP_ADDRESS = "{requestIpAddress}";

	@Override
	public Answer answer(Question question) {
		if (!isAnswerable(question)) {
			return Answer.NOT_AVAILABLE;
		}

		String answer = TEMPLATE_ANSWER.replace(
				PLACEHOLDER_REQUEST_IP_ADDRESS, question.getRequestIpAddress());
		return new Answer(question, answer);
	}

	private boolean isAnswerable(Question question) {
		return question.getBody().toLowerCase().contains(MY_IP_ADDRESS_QUESTION_SIGNATURE);
	}

}
