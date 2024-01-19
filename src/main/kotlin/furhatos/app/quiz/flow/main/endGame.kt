package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
//import furhatos.app.quiz.gui2
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.flow.kotlin.users
import furhatos.skills.HostedGUI

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
val EndGame: State = state(parent = Parent) {
    var skipNext = false

    onEntry {
        playing = false

        // If multiple players, we rank the players on points and congratulate the winner. A special case is when we have a tie.
        if (users.playing().count() > 1) {
            // Sort users by score
            users.playing().sortedByDescending {
                it.quiz.scoreA
            }.forEachIndexed { index, user ->
                // The winner(s)
                if (index == 0) {
                    val highestScore = user.quiz.scoreA
                    val usersWithHighestScore = users.playing().filter {
                        it.quiz.scoreA == highestScore
                    }
                    // Check if we have more than one user with the highest score and if so announce a tie
                    if (usersWithHighestScore.count() > 1) {
                        furhat.say("Wow, we have a tie! You each have $highestScore points", async = true)
                        delay(500)
                        usersWithHighestScore.forEach {
                            furhat.attend(it)
                            delay(700)
                        }
                        // If a tie, we skip the next user since we've already adressed them above
                        skipNext = true
                    }
                    // A single winner exists
                    else {
                        furhat.attend(user)
                        furhat.say("Congratulations! You won with ${user.quiz.scoreA} points")
                    }
                }
                // Every other subsequent player
                else {
                    if (!skipNext) {
                        skipNext = false
                        furhat.attend(user)
                        furhat.say("And you got ${user.quiz.scoreA} points")
                    }
                }
                delay(300)
            }
        }
        // Only one player, we simply announce the score
        else {
            println("You got ${users.current.quiz.scoreD*2} points.")
            println("You got ${users.current.quiz.scoreA*2} points.")
            println("You got ${users.current.quiz.scoreS*2} points.")
            //gui2.write("depression")
            //gui2.append("stress")

            // Assess the severity for each category
            val depressionSeverity = assessSeverity(users.current.quiz.scoreD * 2, 0..9, 10..13, 14..20, 21..27, 28..Int.MAX_VALUE)
            val anxietySeverity = assessSeverity(users.current.quiz.scoreA * 2, 0..7, 8..9, 10..14, 15..19, 20..Int.MAX_VALUE)
            val stressSeverity = assessSeverity(users.current.quiz.scoreS * 2, 0..14, 15..18, 19..25, 26..33, 34..Int.MAX_VALUE)

            // Furhat responses based on severity
            furhat.say("For Depression, you got ${users.current.quiz.scoreD*2} points that means ${depressionSeverity} severity.")
            furhat.say("For Anxiety, you got ${users.current.quiz.scoreA*2} points that means ${anxietySeverity} severity.")
            furhat.say("For Stress, you got ${users.current.quiz.scoreS*2} points that means ${stressSeverity} severity.")





        }

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