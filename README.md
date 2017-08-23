# How to build and run

Build: 
```
mvn clean install
```
Run:  
```
mvn spring-boot:run
```

# Usage
Will start on default port 8080. To see questions, perform a GET on http://localhost:8080/questions. To answer a question perform a POST 
to http://localhost:8080/surveys/surveyId/answerQuestion

Question JSON format:
```
{
	"question": "Favorite color?",
	"answers":
	[
		{"answer": "Black" }, {"answer": "Red" }
	]
}
```

Answer a question JSON format:
```
{
	"questionId": "1",
	"answerIds": ["2"]
}
```

# Examples
## Create new question
```
curl -H "Content-Type: application/json" -X POST -d '{"question":"Favorite color?","answers":[{"answer":"Black" },{"answer":"Red" }]}' http://localhost:8080/questions
```
## Answer question
```
curl -H "Content-Type: application/json" -X POST -d '{"questionId":"1","answerIds":["1"]}' http://localhost:8080/surveys/answerQuestion
```

## Get question statistics
```
curl -X GET http://localhost:8080/surveys/questions/1
```

# TODO
Lots. Many more tests, add logging, etc.