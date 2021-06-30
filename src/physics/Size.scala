package org.apollo
package physics

case class Size (width: Int, height: Int)
object Size {
    def apply(uniformSize: Int): Size = {
        Size(uniformSize, uniformSize)
    }
}
