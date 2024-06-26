package furhatos.app.quiz

import furhatos.app.quiz.flow.Init
import furhatos.app.quiz.flow.main.Idle
import furhatos.flow.kotlin.Flow
import furhatos.skills.Skill
import furhatos.skills.HostedGUI

val gui = HostedGUI("PsychTestGui")



class Quiz : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
