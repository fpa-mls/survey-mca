package org.test.survey;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.survey.controllers.QuestionsController;
import org.test.survey.controllers.SurveysController;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SurveyApplicationTests {
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private QuestionsController questionsController;
	@Autowired
	private SurveysController surveysController;

	@Test
	public void contextLoads() {
		assertThat(questionsController).isNotNull();
		assertThat(surveysController).isNotNull();
	}

	@Test
	public void addQuestionShouldReturnCreated() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>("{\n" +
				"    \"1\": {\n" +
				"        \"id\": 1,\n" +
				"        \"question\": \"Favorite color?\",\n" +
				"        \"answers\": [\n" +
				"            {\n" +
				"                \"id\": 1,\n" +
				"                \"answer\": \"Black\"\n" +
				"            },\n" +
				"            {\n" +
				"                \"id\": 2,\n" +
				"                \"answer\": \"Red\"\n" +
				"            }\n" +
				"        ]\n" +
				"    }\n" +
				"}",
				headers);

		assertThat(this.restTemplate.postForEntity(
				"http://localhost:" + port + "/questions", entity, String.class).getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}

	@Test
	public void getQuestionsShouldReturnOK() throws Exception {
		assertThat(this.restTemplate.getForEntity(
				"http://localhost:" + port + "/questions", String.class).getStatusCode()).isEqualTo(HttpStatus.OK);
	}

	//TODO write more tests
}
