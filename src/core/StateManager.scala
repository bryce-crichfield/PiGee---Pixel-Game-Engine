package org.apollo
package core

import display.Display
import state.{GameState, State}

object StateManager {

    // TODO: This should be moved to somewhere more appropriate
    val SPRITE_SIZE: Int = 64;

    private var currentState: State = new GameState()

    def update(): Unit = {
        currentState.update()

    }

    def render(): Unit = Display.render(currentState)

    def setState(nextState: State): Unit = {
        currentState = nextState
    }

}
