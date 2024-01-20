package furhatos.app.quiz

import furhatos.app.quiz.questions.QuestionSet
import furhatos.nlu.EnumEntity
import furhatos.nlu.EnumItem
import furhatos.nlu.Intent
import furhatos.util.Language


class zero: Intent() //defined in resources folder
class one: Intent()
class two: Intent()
class three: Intent()

class four: Intent()

class DontKnow : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I don't know",
                "don't know",
                "no idea",
                "I have no idea"
        )
    }
}

class RequestRules : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the rules",
                "how does it work"
        )
    }
}

class RequestRepeatQuestion : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what was the statement",
            "what was the question",
                "what was the query",
                "can you repeat the question",
            "can you repeat the statement " +
                    "statement please",
                "what was the question again",
                "query please"
        )
    }
}

class RequestRepeatOptions : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "what are the options",
                "can you repeat the options",
                "what was the options",
                "options please"
        )
    }
}

class AnswerOption : EnumEntity {

    var correct : Boolean = false

    // Every entity and intent needs an empty constructor.
    constructor() {
    }

    // Since we are overwriting the value, we need to use this custom constructor
    constructor(correct : Boolean, value : String) {
        this.correct = correct
        this.value = value
    }

    override fun getEnumItems(lang: Language): List<EnumItem> {
        return QuestionSet.current.options;
    }

}