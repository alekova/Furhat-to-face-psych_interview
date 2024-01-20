package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.gui
import furhatos.app.quiz.questions.QuestionSet
//import furhatos.app.quiz.gui2
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users

// Function to assess severity based on the provided ranges
fun assessSeverity(score: Int, normalRange: IntRange, mildRange: IntRange, moderateRange: IntRange, severeRange: IntRange, extremelySevereRange: IntRange): String {
    return when (score) {
        in normalRange -> "Normal"
        in mildRange -> "Mild"
        in moderateRange -> "Moderate"
        in severeRange -> "Severe"
        in extremelySevereRange -> "Extremely Severe"
        else -> "Invalid Score Range"
    }
}

// End of game, announcing results
val EndTest: State = state(parent = Parent) {
    var skipNext = false

    onEntry {
        playing = false

            println("You got ${users.current.quiz.scoreD*2} points.")
            println("You got ${users.current.quiz.scoreA*2} points.")
            println("You got ${users.current.quiz.scoreS*2} points.")
            //gui2.write("depression")
            //gui2.append("stress")

            // Assess the severity for each category
            val depressionSeverity = assessSeverity(users.current.quiz.scoreD * 2, 0..9, 10..13, 14..20, 21..27, 28..Int.MAX_VALUE)
            val anxietySeverity = assessSeverity(users.current.quiz.scoreA * 2, 0..7, 8..9, 10..14, 15..19, 20..Int.MAX_VALUE)
            val stressSeverity = assessSeverity(users.current.quiz.scoreS * 2, 0..14, 15..18, 19..25, 26..33, 34..Int.MAX_VALUE)

            // GUI responses based on severity
            gui.append("For Depression, you got ${users.current.quiz.scoreD*2} points that means ${depressionSeverity} severity.")
            gui.append("For Anxiety, you got ${users.current.quiz.scoreA*2} points that means ${anxietySeverity} severity.")
            gui.append("For Stress, you got ${users.current.quiz.scoreS*2} points that means ${stressSeverity} severity.")



            // Furhat responses based on severity
            furhat.say("For Depression, you got ${users.current.quiz.scoreD*2} points that means ${depressionSeverity} severity.")
            furhat.say("For Anxiety, you got ${users.current.quiz.scoreA*2} points that means ${anxietySeverity} severity.")
            furhat.say("For Stress, you got ${users.current.quiz.scoreS*2} points that means ${stressSeverity} severity.")



        furhat.say("Thanks for allow me to test you according DASS!")

        // Resetting user state variables
        users.playing().forEach {
            it.quiz.playing = false
            it.quiz.played = true
            it.quiz.lastScore = it.quiz.scoreA
        }

        delay(1000)

        goto(Idle)
    }
}