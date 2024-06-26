package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer is taken from the resources folder as intent, not from here
 *  see nlu.kt raw 10. for more details: Defining EnumEntities in separate files, https://docs.furhat.io/unstable/nlu/
 *  -A list of  alternatives
 */
val questionsEnglish = mutableListOf(
        Question("I found it hard to wind down",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),

        Question("I was aware of dryness of my mouth",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),

        Question("I couldn’t seem to experience any positive feeling at all ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I experienced breathing difficulty (e.g. excessively rapid breathing, breathlessness in the absence of physical exertion) ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I found it difficult to work up the initiative to do things",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I tended to over-react to situations",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I experienced trembling (e.g. in the hands)",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt that I was using a lot of nervous energy ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I was worried about situations in which I might panic and make a fool of myself",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt that I had nothing to look forward to",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I found myself getting agitated ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I found it difficult to relax ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt down-hearted and blue",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I was intolerant of anything that kept me from getting on with what I was doing",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt I was close to panic",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I was unable to become enthusiastic about anything",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt I wasn’t worth much as a person",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt that I was rather touchy",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I was aware of the action of my heart in the absence of physical exertion (e.g. sense of heart rate increase, heart missing a beat) ",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt scared without any good reason",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
        Question("I felt that life was meaningless",
                answer = listOf("0", "1", "2", "3"),
                alternatives = listOf(listOf("Did not apply to me at all"),
                        listOf("Applied to me to some degree"),
                        listOf("Applied to me to a considerable degree"),
                        listOf("Applied to me very much, or most of the time"))),
)