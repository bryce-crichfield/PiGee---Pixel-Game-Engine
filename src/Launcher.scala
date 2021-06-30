package org.apollo

import core.GameLoop

object Launcher extends App {

    new Thread(GameLoop).start()

}
