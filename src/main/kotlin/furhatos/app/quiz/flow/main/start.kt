package furhatos.app.quiz.flow.main

import furhatos.app.quiz.AnswerOption
import furhatos.app.quiz.DontKnow
import furhatos.app.quiz.RequestRepeatOptions
import furhatos.app.quiz.RequestRepeatQuestion
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.nextPlaying
import furhatos.app.quiz.setting.notQuestioned
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.nlu.common.Yes
import furhatos.records.User
import furhatos.app.quiz.zero
import furhatos.app.quiz.one
import furhatos.app.quiz.two
import furhatos.app.quiz.three
//Uncomment next if you use 5 point Likert scale
//import furhatos.app.quiz.four






