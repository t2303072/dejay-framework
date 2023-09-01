package com.dejay.framework;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.security.config.BeanIds;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.test.web.servlet.setup.MockMvcConfigurerAdapter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@Transactional
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@ActiveProfiles("local")
@SpringBootTest(classes = FrameworkApplication.class)
public class FrameworkApplicationTests {

	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@BeforeEach
	void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) throws ServletException {

		// Spring Security Filter Chain 추가 설정
		DelegatingFilterProxy proxyFilter = new DelegatingFilterProxy();
		proxyFilter.init(new MockFilterConfig(webApplicationContext.getServletContext(), BeanIds.SPRING_SECURITY_FILTER_CHAIN));

		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.addFilter(new CharacterEncodingFilter("UTF-8", true))
				.addFilter(proxyFilter)
				.apply(documentationConfiguration(restDocumentation))
				.build();
	}

	/**
	 * Object -> JSON 문자열 변환
	 * @param obj
	 * @return
	 * @throws JsonProcessingException
	 */
	protected String convertObjectToJsonString(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * JSON -> Map 변환
	 * @param json {@link String}
	 * @return
	 * @throws JsonProcessingException
	 */
	protected HashMap convertJsonToMap(String json) throws JsonProcessingException {
		return objectMapper.readValue(json, HashMap.class);
	}

	/**
	 * JSON -> Map 변환
	 * @param mvcResult {@link MvcResult}
	 * @return
	 * @throws JsonProcessingException
	 * @throws UnsupportedEncodingException
	 */
	protected HashMap convertJsonToMap(MvcResult mvcResult) throws JsonProcessingException, UnsupportedEncodingException {
		return objectMapper.readValue(mvcResult.getResponse().getContentAsString(), HashMap.class);
	}

	/**
	 * Map -> Object 변환
	 * @param responseBody
	 * @param objKey
	 * @param clazz
	 * @return
	 * @param <T>
	 */
	protected <T> T convertMapToObject(Map responseBody, String objKey, Class<T> clazz) {
		return objectMapper.convertValue(((Map<Object, Object>) responseBody.get("data")).get(objKey), clazz);
	}

	@Test
	void contextLoads() {
	}

}
