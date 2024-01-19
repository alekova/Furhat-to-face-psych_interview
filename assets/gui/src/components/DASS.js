import React from 'react';


class DASS extends React.Component {
  constructor(props) {
    super(props);

    // Object to store scores for each question
    this.questionScores = {};

    const INSTANCE = {}; // Create an empty instance or initialize it as needed


    }



  assessQuestion(questionNumber, checkbox) {
    const value = parseInt(checkbox.value);

    // Store the score for the specified question
    this.questionScores[questionNumber] = value;
  }

  calculateScores() {
    // Get all checkboxes
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');

    // Initialize scores for each scale
    let depressionScore = 0;
    let anxietyScore = 0;
    let stressScore = 0;

    // Calculate scores
    checkboxes.forEach((checkbox) => {
      const value = parseInt(checkbox.value);
      const questionNumber = parseInt(checkbox.name.replace('q', ''));

      if (questionNumber >= 1 && questionNumber <= 7) {
        depressionScore += value;
      } else if (questionNumber >= 8 && questionNumber <= 14) {
        anxietyScore += value;
      } else if (questionNumber >= 15 && questionNumber <= 21) {
        stressScore += value;
      }
    });

    // Multiply scores by 2
    depressionScore *= 2;
    anxietyScore *= 2;
    stressScore *= 2;

    // Display results
    alert(`Depression Score: ${depressionScore}\nAnxiety Score: ${anxietyScore}\nStress Score: ${stressScore}`);

    // Display scores for each question and overall scores dynamically
    this.displayQuestionScores(depressionScore, anxietyScore, stressScore);

    // Perform assessment based on the provided categories
    //this.assessSeverity('Depression', depressionScore);
    //this.assessSeverity('Anxiety', anxietyScore);
    //this.assessSeverity('Stress', stressScore);

    this.sendEvent("SenseSelectDeck", {});

  }

  displayQuestionScores(depressionScore, anxietyScore, stressScore) {
    // Implement the logic to display scores in the React component
    // For example, you might want to update state or use other React features
    // This is a placeholder implementation
    console.log('Displaying question scores:', this.questionScores);
  }

  render() {
    return React.createElement('div', null,
      React.createElement('h2', null, 'DASS Test21'),
      React.createElement('form', { id: 'dasForm' },
        // Render each question using createElement
        React.createElement('div', { className: 'question' },
          React.createElement('label', { htmlFor: 'q1' }, '1. I found it hard to wind down'),
           React.createElement('input', {
                      type: 'checkbox',
                      name: 'q1',
                      value: '0',
                      onChange: (e) => this.props.handleCheckbox({"param1":'q1', "param2": e.target.value}),
                    }), ' 0',
                    React.createElement('input', {
                      type: 'checkbox',
                      name: 'q1',
                      value: '1',
                      onChange: (e) => this.props.handleCheckbox({param1:'q1', param2: e.target.value}),
                    }), ' 1',
                    React.createElement('input', {
                      type: 'checkbox',
                      name: 'q1',
                      value: '2',
                      onChange: (e) => this.props.handleCheckbox({param1:'q1', param2: e.target.value}),
                    }), ' 2',
                    React.createElement('input', {
                      type: 'checkbox',
                      name: 'q1',
                      value: '3',
                      onChange: (e) => this.props.handleCheckbox({param1:'q1', param2: e.target.value}),
                    }), ' 3'

        ),
        // Continue rendering other questions...
        // Submit button
        //React.createElement('button', { type: 'button', onClick: () => this.calculateScores() }, 'Submit')



        <button type="button" onClick={() => this.props.handleCheckbox()}>
                          Checkbox
                        </button>


      )
    );
  }
}

export default DASS
