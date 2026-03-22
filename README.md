create Question table
-- Create table for Question model
CREATE TABLE question (
    id SERIAL PRIMARY KEY,
    question_title VARCHAR(255),
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    option4 VARCHAR(255),
    right_answer VARCHAR(255),
    difficultylevel VARCHAR(50),
    category VARCHAR(50)
);

-- Insert sample data
INSERT INTO question (id, category, difficultylevel, option1, option2, option3, option4, question_title, right_answer) VALUES
(1, 'Java', 'Easy', 'class', 'interface', 'extends', 'implements', 'Which Java keyword is used to create a subclass?', 'extends'),
(2, 'Java', 'Easy', '4', '5', '6', 'Compile error', 'What is the output of the following Java code snippet?', '5'),
(3, 'Java', 'Easy', 'true', 'false', '0', 'null', 'In Java, what is the default value of an uninitialized boolean variable?', 'false'),
(4, 'Java', 'Easy', 'try', 'throw', 'catch', 'finally', 'Which Java keyword is used to explicitly throw an exception?', 'throw'),
(5, 'Java', 'Easy', 'It indicates that a variable is constant.', 'It indicates that a method can be accessed without creating an instance of the class.', 'It indicates that a class cannot be extended.', 'It indicates that a variable is of primitive type.', 'What does the "static" keyword mean in Java?', 'It indicates that a method can be accessed without creating an instance of the class.'),
(6, 'Java', 'Easy', 'constant int x = 5;', 'final int x = 5;', 'static int x = 5;', 'readonly int x = 5;', 'What is the correct way to declare a constant variable in Java?', 'final int x = 5;'),
(7, 'Java', 'Easy', 'for loop', 'while loop', 'do-while loop', 'switch loop', 'Which loop in Java allows the code to be executed at least once?', 'do-while loop'),
(8, 'Java', 'Easy', 'To terminate a loop or switch statement and transfer control to the next statement.', 'To skip the rest of the code in a loop and move to the next iteration.', 'To define a label for a loop or switch statement.', 'To check a condition and execute a block of code repeatedly.', 'What is the purpose of the "break" statement in Java?', 'To terminate a loop or switch statement and transfer control to the next statement.'),
(9, 'Java', 'Easy', '+', '-', '*', '/', 'Which Java operator is used to concatenate two strings?', '+'),
(10, 'Java', 'Easy', 'HashMap', 'ArrayList', 'LinkedList', 'HashSet', 'In Java, which collection class provides an implementation of a dynamic array?', 'ArrayList'),
(11, 'Python', 'Easy', 'count()', 'size()', 'length()', 'len()', 'Which Python function is used to calculate the length of a list?', 'len()'),
(12, 'Python', 'Easy', '[1, 2, 3]', '[1, 2, 3, 4]', '[4, 3, 2, 1]', 'Error', 'What is the output of the following Python code snippet?', '[1, 2, 3, 4]'),
(13, 'Python', 'Easy', 'break', 'continue', 'pass', 'return', 'Which Python statement is used to exit from a loop prematurely?', 'break'),
(14, 'Python', 'Easy', 'To generate a random number within a given range.', 'To iterate over a sequence of numbers.', 'To sort a list in ascending order.', 'To calculate the length of a string.', 'What is the purpose of the "range()" function in Python?', 'To iterate over a sequence of numbers.'),
(15, 'Python', 'Easy', 'int', 'float', 'str', 'list', 'In Python, which data type is mutable?', 'list'),
(16, 'Python', 'Easy', 'datetime', 'math', 'os', 'sys', 'Which Python module is used for working with dates and times?', 'datetime');

Postman curls
For Question table
1)get all questions
curl --location 'http://localhost:8080/question/allQuestions'

2)get questions by category
curl --location 'localhost:8080/question/category/Java'

3)create new question
curl --location 'localhost:8080/question/addQuestion' \
--header 'Content-Type: application/json' \
--data '  {
        "category": "Java",
        "difficultylevel": "Easy",
        "option1": "100",
        "option2": "127",
        "option3": "255",
        "option4": "999",
        "questionTitle": "Maximum value for byte in java?",
        "rightAnswer": "127"
    }'

4)Delete by question by Id
curl --location --request DELETE 'localhost:8080/question/delete/18' \
--data '' 

For Quiz table
1)create quiz table with category from question table and any title and join table will be created with random question from question table
curl --location --request POST 'localhost:8080/quiz/create?category=Java&numQ=5&title=JQuiz' \
--data ''

2)get quiz by ID - get all details like id,title,options 
curl --location 'localhost:8080/quiz/get/1' \
--data ''
  
3)Calculate correct ans by response ans and question table right ans
curl --location 'localhost:8080/quiz/submit/1' \
--header 'Content-Type: application/json' \
--data '[
    {
        "id":3,
        "response":"false"
    },
    {
        "id":7,
        "response":"do-while loop"
    },
    {
        "id":6,
        "response":"final int x = 5;"
    },
    {
        "id":9,
        "response":"+"
    },
    {
        "id":2,
        "response":"5"
    }
   
]'

