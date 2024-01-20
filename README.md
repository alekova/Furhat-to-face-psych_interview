## Description
If you are facing significant emotional difficulties, you can use this open template to conduct a psychological interview with Furhat. Assess your results by filling out the Depression, Anxiety and Stress Scale - 21 Items (DASS-21). This set of three self-report scales is designed to measure emotional states, including depression, anxiety and stress.


## Usage
Max number of users is set to: 1

If you want to use external GUI, open on a tablet or other PC with Internet connection the furhat web page and press the GUI tab next to the skill. Start GUI at the begining.
You can change the name of the GUI in the 'main.kt', raw 9.

## Customisation
The test (DASS-21) can be customized, allowing changes to be made to any psychological test, including the number of statements, answer options, scale, and other parameters.

1. Customizing the Number of Statements:
Open the project and go to 'idle.kt', raw 68.
Change the variable val maxStatements = 21 to the number of statements in your customized test.

2. Changing the Test Description:
In 'newTest.kt', raw 17, update the description of the test.

3. Inserting Statements with Alternatives:
Go to 'questionlist.kt' to insert your statements with the alternatives. You can add or remove Question constructors.

4. Handling Correct Answers:
The correct answer is taken from the resources folder. Check 'nlu.kt', raw 10 for more details: Defining EnumEntities in separate files, https://docs.furhat.io/unstable/nlu/

5. Processing User Responses:
User responses are processed in 'askQuestion.kt', for instance, raw 60 processes onResponse<one> and assigns 1 point to the appropriate score (depression, anxiety, or stress) based on the statement.

6. Assessing Severity:
In 'endTest'.kt, raw 15, you need to change the function assessSeverity with your own assessment. Also, review rows 40-42 for the assessment of severity for each category.
