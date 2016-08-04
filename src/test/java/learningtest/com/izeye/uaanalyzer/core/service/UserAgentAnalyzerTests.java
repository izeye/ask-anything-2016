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

package learningtest.com.izeye.uaanalyzer.core.service;

import com.izeye.uaanalyzer.core.domain.BrowserInfo;
import com.izeye.uaanalyzer.core.domain.BrowserType;
import com.izeye.uaanalyzer.core.domain.OsInfo;
import com.izeye.uaanalyzer.core.domain.OsType;
import com.izeye.uaanalyzer.core.domain.UserAgent;
import com.izeye.uaanalyzer.core.service.DefaultUserAgentAnalyzer;
import com.izeye.uaanalyzer.core.service.UserAgentAnalyzer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for UserAgentAnalyzer.
 *
 * @author Johnny Lim
 */
public class UserAgentAnalyzerTests {

	UserAgentAnalyzer userAgentAnalyzer = new DefaultUserAgentAnalyzer();

	@Test
	public void testAnalyze() {
		String userAgentString = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		UserAgent userAgent = this.userAgentAnalyzer.analyze(userAgentString);
		System.out.println(userAgent);

		OsInfo osInfo = userAgent.getOsInfo();
		assertThat(osInfo.getOsType()).isEqualTo(OsType.MAC_OS_X);
		assertThat(osInfo.getOsVersion()).isEqualTo("10.10.5");
		assertThat(osInfo.getDescription()).isNull();

		BrowserInfo browserInfo = userAgent.getBrowserInfo();
		assertThat(browserInfo.getBrowserType()).isEqualTo(BrowserType.CHROME);
		assertThat(browserInfo.getBrowserVersion()).isEqualTo("51.0.2704.103");
	}

}
