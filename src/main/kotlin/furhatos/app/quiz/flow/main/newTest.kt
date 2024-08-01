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
        furhat.say("This test measures anxiety, stress or depression symptoms!")
        furhat.gesture(Gestures.Nod, async = false)

        furhat.say{
            +"I will ask you about $maxStatements  statements and you have to rate with a number 0, 1, 2 or 3, indicating how much the statement applied to you over the past week. There are no right or wrong answers. Do not spend too much time on any statement."
                  +  "The rating scale is as follows:"

                + delay(200)
                +"Zero is: "
            + delay(100)
                +"Did not apply to me at all, "
                +delay(100)
            +"If you wish, you may reply with the corresponding digits, for instance - zero, one, two or three."
            +delay(100)
               + "One is: Applied to me to some degree, some of the time."
               + delay(100)
                +"Two is: Applied to me to a considerable degree, a good part of time."
               + delay(100)
               + "Three is: Applied to me very much, most of the time. "
            //Uncomment next if you use 5 point Likert scale
            /*+ delay(100)
            + "The highest level is: apply to me Always, Strongly Agree, or digit  four. "*/
            + blocking {
                furhat.gesture(Gestures.BigSmile, async = false)
                }
            +delay(100)
            +"You may ask how the statement was, The rating scale or ask for a break at any time ! "
            }



        furhat.say("Alright, here we go! If you wish, you can use external graphical user interface to visualize the statements and corresponding answers")
        QuestionSet.next()
        furhat.attend(users.playing().first())
        goto(AskQuestion)
    }
}
