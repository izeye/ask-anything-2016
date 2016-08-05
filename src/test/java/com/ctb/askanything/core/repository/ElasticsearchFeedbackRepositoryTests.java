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

package com.ctb.askanything.core.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ctb.askanything.core.domain.Feedback;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for ElasticsearchFeedbackRepository.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ElasticsearchFeedbackRepositoryTests {

	@Autowired
	ElasticsearchFeedbackRepository elasticsearchFeedbackRepository;

	@Test
	public void test() {
		String answerId = "answerId";
		String body = "body";
		String requestIpAddress = "1.2.3.4";
		Feedback feedback = new Feedback(answerId, body, requestIpAddress);
		this.elasticsearchFeedbackRepository.save(feedback);

		Feedback found = this.elasticsearchFeedbackRepository.findOne(feedback.getId());
		System.out.println(found);
		assertThat(found.getAnswerId()).isEqualTo(answerId);
		assertThat(found.getBody()).isEqualTo(body);
		assertThat(found.getRequestIpAddress()).isEqualTo(requestIpAddress);
	}

}
