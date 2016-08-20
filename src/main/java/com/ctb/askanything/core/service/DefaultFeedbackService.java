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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctb.askanything.api.domain.Answer;
import com.ctb.askanything.api.domain.Feedback;
import com.ctb.askanything.core.util.JsonUtils;

/**
 * Default feedback service.
 *
 * @author Johnny Lim
 */
@Service
public class DefaultFeedbackService implements FeedbackService {

	private static final Logger FEEDBACK_LOG = LoggerFactory.getLogger("FEEDBACK");

	@Autowired
	private AnswerService answerService;

	@Override
	public void feedback(String answerId, Feedback feedback) {
		Answer answer = this.answerService.findOne(answerId);
		answer.setFeedback(feedback);

		this.answerService.save(answer);

		FEEDBACK_LOG.info(JsonUtils.toJson(answer));
	}

}
