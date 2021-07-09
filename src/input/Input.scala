package org.apollo
package input

import display.ui.bridge.UIEventHandler.UIMouseEventHandler
import physics.Position

import java.awt.event._

object Input extends KeyListener with MouseListener with MouseMotionListener {

    private var mousePosition = Position(0,0)
    private var mouseIsClicked = false
    private var mouseIsPressed = false

    private val pressed = new Array[Boolean](255)

    def isPressed(keyCode: Int): Boolean = {
      pressed(keyCode)
    }

    def isMouseClicked: Boolean = {
        mouseIsClicked
    }

    override def keyTyped(e: KeyEvent): Unit = {

    }

    override def keyPressed(e: KeyEvent): Unit = {
        pressed(e.getKeyCode) = true
    }

    override def keyReleased(e: KeyEvent): Unit = {
        pressed(e.getKeyCode) = false
    }

    override def mousePressed(e: MouseEvent): Unit = {
        mouseIsPressed = true
    }

    override def mouseReleased(e: MouseEvent): Unit = {
        mouseIsPressed = false
        mouseIsClicked = true
    }

    //Used to ensure sync with game updates
    def clearMouseClick(): Unit = {
        mouseIsClicked = false
    }

    override def mouseDragged(e: MouseEvent): Unit = {
        mousePosition = Position(e.getX, e.getY)
    }

    override def mouseMoved(e: MouseEvent): Unit = {
        mousePosition = Position(e.getX, e.getY)
    }

    def getMousePosition: Position = mousePosition


    override def mouseClicked(e: MouseEvent): Unit = {
        e.dispatchToUI()
    }
    override def mouseEntered(e: MouseEvent): Unit = {}
    override def mouseExited(e: MouseEvent): Unit = {}
}
