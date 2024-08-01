package furhatos.app.quiz.flow.main

import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.nextPlaying
import furhatos.app.quiz.setting.notQuestioned
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.RequestRepeat
import furhatos.app.quiz.gui

val AskQuestion: State = state(parent = Parent) {
    var failedAttempts = 0

    onEntry {
        failedAttempts = 0

        // Set speech rec phrases based on the current question's answers
        furhat.setSpeechRecPhrases(QuestionSet.current.speechPhrases)

        furhat.say{
            + "Statement ${statement+1}"
            +delay(200)
            + blocking {
                furhat.gesture(Gestures.BrowRaise, async = false)
            }
        }
        gui.append("S${statement+1}: ${QuestionSet.current.text}")

        // Ask the question and not followed by the options 
        furhat.ask(QuestionSet.current.text )

    }


    // Here we re-state the question
    onReentry {
        failedAttempts = 0
        furhat.ask("The statement was, ${QuestionSet.current.text} ${QuestionSet.current.getOptionsString()}")
    }


    onResponse<zero> {

        println("answer zero" )
        gui.append("zero")

        // Check if the game has ended and if not, goes to a new question
        if (++statement >= maxStatements) {
            furhat.say("That was the last statement")
            goto(EndTest)
        } else {
            goto(NewQuestion)
        }
    }

    onResponse<one> {

        println("answer one" )
        gui.append("one")
        when (statement) {
            1, 6, 8, 11, 12, 14, 18 -> users.current.quiz.scoreS+= 1
        }
        when (statement) {
            2, 4, 7, 9, 15, 19, 20 -> users.current.quiz.scoreA+= 1
        }
        when (statement) {
            3, 5, 10, 13, 16, 17, 21 -> users.current.quiz.scoreD+= 1
        }
        // Check if the game has ended and if not, goes to a new question
        if (++statement >= maxStatements) {
            furhat.say("That was the last statement")
            goto(EndTest)
        } else {
            goto(NewQuestion)
        }
    }

    onResponse<two> {

        println("answer two" )
        gui.append("two")
        when (statement) {
            1, 6, 8, 11, 12, 14, 18 -> users.current.quiz.scoreS+= 2
        }
        when (statement) {
            2, 4, 7, 9, 15, 19, 20 -> users.current.quiz.scoreA+= 2
        }
        when (statement) {
            3, 5, 10, 13, 16, 17, 21 -> users.current.quiz.scoreD+= 2
        }
        // Check if the game has ended and if not, goes to a new question
        if (++statement >= maxStatements) {
            furhat.say("That was the last statement")
            goto(EndTest)
        } else {
            goto(NewQuestion)
        }
    }

    onResponse<three> {

        println("answer three" )
        gui.append("three")
        when (statement) {
            1, 6, 8, 11, 12, 14, 18 -> users.current.quiz.scoreS+= 3
        }
        when (statement) {
            2, 4, 7, 9, 15, 19, 20 -> users.current.quiz.scoreA+= 3
        }
        when (statement) {
            3, 5, 10, 13, 16, 17, 21 -> users.current.quiz.scoreD+= 3
        }
        // Check if the game has ended and if not, goes to a new question
        if (++statement >= maxStatements) {
            furhat.say("That was the last statement")
            goto(EndTest)
        } else {
            goto(NewQuestion)
        }
    }

//Uncomment next if you use 5 point Likert scale
    /* 
    onResponse<four> {
        println("answer four" )
        //put your code here if you use 5 Likert Scale

        gui.append("we accept answer four as three")
        when (statement) {
            1, 6, 8, 11, 12, 14, 18 -> users.current.quiz.scoreS+= 3
        }
        when (statement) {
            2, 4, 7, 9, 15, 19, 20 -> users.current.quiz.scoreA+= 3
        }
        when (statement) {
            3, 5, 10, 13, 16, 17, 21 -> users.current.quiz.scoreD+= 3
        }
        // Check if the game has ended and if not, goes to a new question
        if (++statement >= maxStatements) {
            furhat.say("That was the last statement")
            goto(EndTest)
        } else {
            goto(NewQuestion)
        }

    }*/



    // The users answers that they don't know
    onResponse<DontKnow> {
        furhat.say("Ok, lets assume to a certain degree. Here comes the next statement")
        goto(NewQuestion)
    }

    onResponse<RequestRepeat> {
        reentry()
    }

    onResponse<RequestRepeatQuestion> {
        furhat.gesture(Gestures.BrowRaise)
        furhat.ask(QuestionSet.current.text)
    }

    // The user wants to hear the options again
    onResponse<RequestRepeatOptions> {
        furhat.gesture(Gestures.Surprise)
        random(
                { furhat.ask("They are ${QuestionSet.current.getOptionsString()}") },
                { furhat.ask(QuestionSet.current.getOptionsString()) }
        )
    }

    // If we don't get any response, we assume the user was too slow
    onNoResponse {
       

        furhat.say(QuestionSet.current.text)
        furhat.ask(QuestionSet.current.getOptionsString())
    }

    /* If we get a response that doesn't map to any alternative or any of the above handlers,
        we track how many times this has happened in a row and give them two more attempts and
        finally moving on if we still don't get it.
     */
    onResponse {
        failedAttempts++
        when (failedAttempts) {
            1 -> furhat.ask("I didn't get that, sorry. Try again!")
            2 -> {
                furhat.say("Sorry, I still didn't get that")
                furhat.ask("The options are ${QuestionSet.current.getOptionsString()}")
            }
            else -> {
                furhat.say("Still couldn't get that. Let's try a new question")
                shouldChangeUser = false
                goto(NewQuestion)
            }
        }
    }
}

val NewQuestion = state(parent = Parent) {
    onEntry {
        /*
            If more than one player, we determine what user to target next here, based on the shouldChangeUser boolean
         */
        if (users.playing().count() > 1) {
            if (shouldChangeUser) {
                val nextUser = users.nextPlaying()
                furhat.attend(nextUser)
                random(
                        { furhat.say("The next one is for you") },
                        { furhat.say("For you now") },
                        { furhat.say("Now for you") }
                )
            } else {
                shouldChangeUser = true
                random(
                        { furhat.say("You get to continue") },
                        { furhat.say("Next one coming up") },
                        { furhat.say("Here's another one") }
                )
            }
        }
        if (!users.current.isAttendingFurhat) {
            furhat.say {
                random {
                    block {
                        +"But then I do want you to pay attention"
                        +Gestures.BigSmile
                    }
                    +"Look at me, I'm captain now"
                    +"Could you pay some attention to me"
                }
            }
        }
        // Ask new question
        QuestionSet.next()
        goto(AskQuestion)
    }
}
