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

package com.ctb.askanything.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Documentation for ask APIs.
 *
 * @author Johnny Lim
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs("build/generated-snippets")
public class AskApiDocumentation {

	@Autowired
	MockMvc mockMvc;

	@Test
	public void documentAsk() throws Exception {
		this.mockMvc
				.perform(get("/api/v1/ask")
						.param("question", "How are you?")
						.accept(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isOk())
				.andDo(document("ask",
						requestParameters(
								parameterWithName("question").description("Question to ask")
						),
						responseFields(
								fieldWithPath("id").description("ID for answer"),
								fieldWithPath("timestamp").description("Timestamp for answer"),
								fieldWithPath("question").description("Question for answer"),
								fieldWithPath("question.id").description("ID for question"),
								fieldWithPath("question.timestamp").description("Timestamp for question"),
								fieldWithPath("question.body").description("Body for question"),
								fieldWithPath("question.requestIpAddress").description("Request IP address for question"),
								fieldWithPath("body").description("Body for answer")
						)
				));
	}

}
