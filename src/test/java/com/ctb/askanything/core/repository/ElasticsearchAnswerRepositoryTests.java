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
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.ctb.askanything.core.domain.Answer;
import com.ctb.askanything.core.domain.Question;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for ElasticsearchAnswerRepository.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ElasticsearchAnswerRepositoryTests {

	@Autowired
	ElasticsearchAnswerRepository elasticsearchAnswerRepository;

	@Test
	public void test() {
		String questionBody = "questionBody";
		String requestIpAddress = "1.2.3.4";
		Question question = new Question(questionBody, requestIpAddress);
		String answerBody = "answerBody";
		Answer answer = new Answer(question, answerBody);
		this.elasticsearchAnswerRepository.save(answer);

		Answer found = this.elasticsearchAnswerRepository.findOne(answer.getId());
		System.out.println(found);
		assertThat(found.getQuestion()).isEqualTo(question);
		assertThat(found.getBody()).isEqualTo(answerBody);
	}

	@Test
	public void testFindAll() {
		this.elasticsearchAnswerRepository
				.findAll(new Sort(Sort.Direction.DESC, "timestamp")).forEach(System.out::println);
	}

}
