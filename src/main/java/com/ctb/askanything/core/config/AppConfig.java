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

package com.ctb.askanything.core.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ctb.askanything.api.service.AnswerEngine;
import com.ctb.askanything.core.util.ClassUtils;

/**
 * Configuration for this application.
 *
 * @author Johnny Lim
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
public class AppConfig {

	@Autowired
	private AppProperties properties;

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public List<AnswerEngine> answerEngines() {
		AutowireCapableBeanFactory factory =
				this.applicationContext.getAutowireCapableBeanFactory();
		List<AppProperties.AnswerEngineSpec> answerEngineSpecs =
				this.properties.getAnswerEngineSpecs();
		Collections.sort(
				answerEngineSpecs,
				(o1, o2) -> Integer.compare(o1.getEngineOrder(), o2.getEngineOrder()));
		List<AnswerEngine> answerEngines = new ArrayList<>();
		for (AppProperties.AnswerEngineSpec spec : answerEngineSpecs) {
			AnswerEngine answerEngine =
					(AnswerEngine) ClassUtils.createInstance(spec.getEngineClass());
			factory.autowireBean(answerEngine);
			answerEngines.add(answerEngine);
		}
		return answerEngines;
	}

}
