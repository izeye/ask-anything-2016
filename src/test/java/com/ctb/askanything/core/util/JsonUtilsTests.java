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

import org.junit.Test;

import com.ctb.askanything.core.domain.Answer;
import com.ctb.askanything.core.domain.AnswerHistory;
import com.ctb.askanything.core.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for JsonUtils.
 *
 * @author Johnny Lim
 */
public class JsonUtilsTests {

	@Test
	public void test() {
		String questionBody = "body";
		String requestIpAddress = "requestIpAddress";
		Question question = new Question(questionBody, requestIpAddress);

		String answerBody = "answerBody";
		Answer answer = new Answer(answerBody);

		AnswerHistory history = new AnswerHistory(question, answer);
		String json = JsonUtils.toJson(history);
		System.out.println(json);

		AnswerHistory unmarshalled = JsonUtils.fromJson(json, AnswerHistory.class);
		assertThat(unmarshalled).isEqualTo(history);
	}

}
