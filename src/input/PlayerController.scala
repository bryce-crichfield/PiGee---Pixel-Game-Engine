package org.apollo
package input

import java.awt.event.KeyEvent

object PlayerController extends EntityController {

    override def isRequestingUp: Boolean = {
        Input.isPressed(KeyEvent.VK_W)
    }

    override def isRequestingDown: Boolean = {
        Input.isPressed(KeyEvent.VK_S)
    }

    override def isRequestingRight: Boolean = {
        Input.isPressed(KeyEvent.VK_D)
    }

    override def isRequestingLeft: Boolean = {
        Input.isPressed(KeyEvent.VK_A)
    }

    override def isRequestingAttack: Boolean = {
        Input.isPressed(KeyEvent.VK_E)
    }

    override def isRequestingCast: Boolean = {
        Input.isPressed(KeyEvent.VK_Q)
    }

    override def isRequestingSprint: Boolean = {
        Input.isPressed(KeyEvent.VK_SHIFT)
    }

}
