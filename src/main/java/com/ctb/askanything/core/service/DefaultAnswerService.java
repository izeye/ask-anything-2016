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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctb.askanything.core.domain.Answer;
import com.ctb.askanything.core.domain.AnswerHistory;
import com.ctb.askanything.core.domain.Question;
import com.ctb.askanything.core.util.JsonUtils;

/**
 * Default answer service.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultAnswerService implements AnswerService {

	private static final Logger ANSWER_LOG = LoggerFactory.getLogger("ANSWER");

	@Autowired
	private List<AnswerEngine> answerEngines;

	@Override
	public Answer ask(Question question) {
		for (AnswerEngine answerEngine : answerEngines) {
			Answer answer = answerEngine.answer(question);
			if (answer != Answer.NOT_AVAILABLE) {
				log(question, answer);
				return answer;
			}
		}
		throw new IllegalStateException("Unreachable. Fallback answer engine failed?");
	}

	private void log(Question question, Answer answer) {
		AnswerHistory history = new AnswerHistory(question, answer);
		ANSWER_LOG.info(JsonUtils.toJson(history));
	}

}