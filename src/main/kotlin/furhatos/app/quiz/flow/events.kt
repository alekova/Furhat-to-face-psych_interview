package furhatos.app.quiz.flow

import furhatos.event.Event

class RandomHeadEvent : Event()
class NoUsersPresentEvent : Event()

//const val SenseButtonClick = "SenseButtonClick"
const val SenseGameReset = "SenseGameReset"
const val SenseSelectDeck = "SenseSelectDeck"
const val SenseCheckbox = "SenseCheckbox"