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

package com.ctb.askanything.answer.service;

import java.util.Collections;

import org.springframework.beans.DirectFieldAccessor;

import org.elasticsearch.index.query.QueryBuilder;
import org.junit.Test;

import com.ctb.askanything.api.domain.Answer;
import com.ctb.askanything.api.domain.Question;
import com.ctb.askanything.api.service.AnswerEngine;
import com.ctb.askanything.core.repository.ElasticsearchAnswerRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;
import static org.mockito.Matchers.any;

/**
 * Tests for the feedback-based answer engine.
 *
 * @author Johnny Lim
 */
public class FeedbackAnswerEngineTests {

	private AnswerEngine feedbackAnswerEngine = new FeedbackAnswerEngine();

	@Test
	public void testAnswer() {
		setFallbackAnswerOnly();

		Question question = new Question();
		question.setBody("AngularJS");
		assertThat(this.feedbackAnswerEngine.answer(question)).isSameAs(Answer.NOT_AVAILABLE);
	}

	private void setFallbackAnswerOnly() {
		Question question = new Question();
		question.setBody("React");
		Answer fallbackAnswer = new Answer(question, "Are you asking \"React\"?");

		ElasticsearchAnswerRepository elasticsearchAnswerRepository =
				mock(ElasticsearchAnswerRepository.class);
		when(elasticsearchAnswerRepository.search(any(QueryBuilder.class)))
				.thenReturn(Collections.singletonList(fallbackAnswer));

		DirectFieldAccessor dfa = new DirectFieldAccessor(this.feedbackAnswerEngine);
		dfa.setPropertyValue("elasticsearchAnswerRepository", elasticsearchAnswerRepository);
	}

}
