package org.apollo
package display.ui.bridge

import org.apollo.display.ui.typeclasses.UIDimensionable.UIDimensioner
import physics.Position
import state.State

import org.apollo.display.ui.core.UIComponent

import java.awt.Rectangle

/** This object allows the immutability of the UISystem to be "bridged" with the mutability of the state system */
object UIBridge {

  private var changeMade: Boolean = false
  private var uiComponents: Map[String, UIComponent] = Map[String, UIComponent]()

  def apply(identifier: String): UIComponent = {
    uiComponents(identifier)
  }

  def apply(identifier: String, component: UIComponent): Unit = {
    uiComponents = uiComponents + (identifier -> component)
  }

  def getUIComponents(): List[UIComponent] = {
    uiComponents.values.toList
  }

  def update(state: State): Unit = {
    if (changeMade) {
      uiComponents = uiComponents.map {
        case (s, c) => s -> c.update(state)
      }
      setChangesMade(false)
    }
  }

  def setChangesMade(b: Boolean): Unit = changeMade = b

  def getChangesMade: Boolean = changeMade

  def query(position: Position): Option[UIComponent] = {
    uiComponents.values.find(component => {
      val posRect = new Rectangle(position.intX, position.intY, 1, 1)
      val compRect = new Rectangle(component.position.intX, component.position.intY, component.size.width, component.size.height)
      if(posRect intersects compRect) true
      else false
    })
  }
  // TODO: Figure out how to make this work


}


