package org.apollo
package physics

trait Direction {
    val index: Int

    /*** Given a specified motion, will return the enumerated Direction */
    def apply(motion: Motion): Direction = {
        val x: Double = motion.vector.x
        val y: Double = motion.vector.y
        if(x == 0 && y < 0) North
        else if(x < 0 && y == 0) West
        else if(x == 0 && y > 0) South
        else if(x > 0 && y == 0) East
        else this
    }
}

case object North extends Direction {
    override val index: Int = 0
}
case object West extends Direction {
    override val index: Int = 1
}
case object South extends Direction {
    override val index: Int = 2
}
case object East extends Direction {
    override val index: Int = 3
}
