package org.apollo
package physics

import java.awt.Rectangle

case class CollisionBox(bounds: Rectangle) {
    def collidesWith(that: CollisionBox): Boolean = {
        bounds.intersects(that.bounds)
    }
}
object CollisionBox {
    def of(position: Position, size: Size): CollisionBox = {
        CollisionBox(
            new Rectangle(
                position.intX,
                position.intY,
                size.width,
                size.height
            )
        )
    }

}
