package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.setting.interested
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.User
import furhatos.app.quiz.flow.SenseGameReset
import furhatos.app.quiz.flow.SenseSelectDeck
import furhatos.app.quiz.flow.SenseCheckbox

val Idle: State = state {
    onEntry {
        /*
            Loop through all (potentially) interested users.
            Goto calls are used since users may enter and leave
            while we are querying other users and we want to
            ask all users before moving on. I.e we want to update the
            users.interested() list of users.
          */
        users.interested().forEach {
            furhat.attend(it)
            goto(QueryPerson(it))
        }
        // Once no more user, start the game with all interested users
        if (users.playing().isNotEmpty()) {
            furhat.attendAll()
            goto(NewGame)
        }
    }

    onUserEnter(instant = true) {
        /* Say hi and query a new user if it's the only interested user.
            Other interested users would already be greeted at this point.
            If not, we glance at the user and keep doing whatever we were doing.
         */
        if (users.interested().count() == 1) {
            furhat.attend(it.id)
            furhat.say("Hello there")
            goto(QueryPerson(it))
        } else {
            furhat.glance(it.id, async=true)
        }
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }

    onResponse{
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

// Variables

val maxStatements = 21
var statement = 0
var shouldChangeUser = false
var playing = false

fun QueryPerson(user: User) = state(parent = Parent) {
    onEntry {
        if (!user.quiz.played) {
            furhat.ask("Do you want to start the DASS21 test?")

        } else {
            furhat.ask("Do you want to test again? Maybe you can have different score ")
        }
    }
    onEvent(SenseGameReset) {
        furhat.say("Event 1 received")

        println(it.get("param1")) // "paramValue" or "null" if param1 is not set
    }

    onEvent(SenseSelectDeck) {
        furhat.say("Event received")

        println(it.get("param1")) // "paramValue" or "null" if param1 is not set
    }

    onEvent(SenseCheckbox) {
        furhat.say("Event SenseCheckbox")
        val params=it.get("params")?.toString()
        //val params = it.get("params") as? Map<String, Any> // Assuming params is a Map<String, Any>

        // Check if params is not null and contains the "param1" key
        //val param1Value = params?.get("param1")?.toString()
       // println(param1Value)

        //println("Value of param1: $qi, Value of param2: $value")
    }



    onResponse<Yes> {
        user.quiz.playing = true
        furhat.say("great!")
        goto(Idle)
    }

    onResponse<No> {
        user.quiz.interested = false
        furhat.say("oh well")
        goto(Idle)
    }
}
