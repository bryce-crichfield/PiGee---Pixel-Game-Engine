package org.apollo
package display.ui.bridge

import display.ui.typeclasses.UICommandable
import display.ui.typeclasses.UICommandable.nullCommand
import physics.Position

import java.awt.event.{InputEvent, MouseEvent}


/** receives event calls, determines appropriate action, and then dispatches that command to the UISystem (or State???) */
// Right now this is really acting more in terms of an adapter rather than a standalone component
trait UIEventHandler[A <: InputEvent] {
  def dispatchToUI(): Unit
  protected def receive: Option[UIEvent]
  protected def applyCommand(uiEvent: Option[UIEvent]): Unit
}

object UIEventHandler {

  implicit class UIMouseEventHandler(event: MouseEvent) extends UIEventHandler[MouseEvent] {
    def dispatchToUI(): Unit = {
      val received = receive
      applyCommand(received)
    }
    protected def receive: Option[UIEvent] = {
      val eventPosition = Position(event.getX, event.getY)
      val queriedComponent = UISystem.query(eventPosition)
      queriedComponent match {
        case Some(_) => Some(UIEvent(queriedComponent.get, eventPosition))
        case None => None
      }
    }
    protected def applyCommand(uiEvent: Option[UIEvent]): Unit = {
      if(uiEvent.isEmpty) nullCommand()
      else {
        val componentType = uiEvent.get
        uiEvent.get.component match {
          case commandable: UICommandable => commandable.applyCommand()
          case _ => nullCommand()
        }
      }
    }
  }





}
