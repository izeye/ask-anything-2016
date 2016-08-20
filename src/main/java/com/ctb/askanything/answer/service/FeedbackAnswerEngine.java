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

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.ctb.askanything.api.domain.Answer;
import com.ctb.askanything.api.domain.Question;
import com.ctb.askanything.api.service.AnswerEngine;
import com.ctb.askanything.core.repository.ElasticsearchAnswerRepository;

/**
 * Feedback-based answer engine.
 *
 * @author Johnny Lim
 */
public class FeedbackAnswerEngine implements AnswerEngine {

	@Autowired
	private ElasticsearchAnswerRepository elasticsearchAnswerRepository;

	@Override
	public Answer answer(Question question) {
		MatchQueryBuilder query =
				QueryBuilders.matchQuery("feedback.body", question.getBody());
		Iterable<Answer> answers = this.elasticsearchAnswerRepository.search(query);
		Iterator<Answer> iterator = answers.iterator();
		if (!iterator.hasNext()) {
			return Answer.NOT_AVAILABLE;
		}
		return new Answer(question, iterator.next().getFeedback().getBody());
	}

}
