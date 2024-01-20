package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.gui
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.playing
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.gestures.Gestures

val NewTest = state(parent = Parent) {
    onEntry {
        playing = true
        statement = 0
        gui.append("DASS21")

        furhat.say{
            +"I will ask you about $maxStatements  statements and you rate how much the statement applied to you over the past week. There are no right or wrong answers. Do not spend too much time on any statement."
                  +  "The rating scale is as follows:"

                + delay(200)
                +"The lowest level is: "
            + delay(100)
                +"Did not apply to me at all, "
                +delay(100)
            +"If you wish, you may respond with a digit, such as zero."
            +delay(100)
               + "The next is Applied to me to some degree, some of the time, or digit one."
               + delay(100)
                +"The next is Applied to me to a considerable degree, a good part of time, or digit  two."
               + delay(100)
               + "The highest level is: Applied to me very much, most of the time, or digit  three. "
            + blocking {
                furhat.gesture(Gestures.BigSmile, async = false)
                }
            }


        furhat.say("Alright, here we go! If you wish, you can use external graphical user interface to visualize the statements and corresponding answers")
        QuestionSet.next()
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
}