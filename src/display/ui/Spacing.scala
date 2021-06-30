package org.apollo
package display.ui

case class Spacing(
                  top: Int,
                  right: Int,
                  bottom: Int,
                  left: Int
                  ) {

    def horizontal: Int = right + left

    def vertical: Int = top + bottom

}

object Spacing {

    def apply(horizontal: Int, vertical: Int): Spacing = {
        assert(horizontal >= 0 && vertical >= 0)
        Spacing(vertical, horizontal, vertical, horizontal)
    }

    def apply(uniformSpacing: Int): Spacing = {
        assert(uniformSpacing >= 0)
        Spacing(uniformSpacing, uniformSpacing, uniformSpacing, uniformSpacing)
    }
}
