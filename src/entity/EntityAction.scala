package org.apollo
package entity

import display.gfx.spritesheet.{LPCSpriteInfo, LPCSubSheet}
import physics.{East, North, South, West}

trait EntityAction {
    val lpcSubsheet: LPCSubSheet
}
case object Standing extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(1, 0, 64),
        West -> LPCSpriteInfo(1, 1, 64),
        South -> LPCSpriteInfo(1, 2, 64),
        East -> LPCSpriteInfo(1, 3, 64)
    )
}
case object Casting extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(7, 0, 64),
        West -> LPCSpriteInfo(7, 1, 64),
        South -> LPCSpriteInfo(7, 2, 64),
        East -> LPCSpriteInfo(7, 3, 64)
    )
}
case object Thrusting extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(8, 4, 64),
        West -> LPCSpriteInfo(8, 5, 64),
        South -> LPCSpriteInfo(8, 6, 64),
        East -> LPCSpriteInfo(8, 7, 64)
    )
}
case object Walking extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(9, 8, 64),
        West -> LPCSpriteInfo(9, 9, 64),
        South -> LPCSpriteInfo(9, 10, 64),
        East -> LPCSpriteInfo(9, 11, 64)
    )
}
case object Slashing extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(6, 12, 64),
        West -> LPCSpriteInfo(6, 13, 64),
        South -> LPCSpriteInfo(6, 14, 64),
        East -> LPCSpriteInfo(6, 15, 64)
    )
}
case object Drawing extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(13, 16, 64),
        West -> LPCSpriteInfo(13, 17, 64),
        South -> LPCSpriteInfo(13, 18, 64),
        East -> LPCSpriteInfo(13, 19, 64)
    )
}
case object Dead extends EntityAction {
    val lpcSubsheet: LPCSubSheet = LPCSubSheet(
        North -> LPCSpriteInfo(6, 20, 64),
        West -> LPCSpriteInfo(6, 20, 64),
        South -> LPCSpriteInfo(6, 20, 64),
        East -> LPCSpriteInfo(6, 20, 64)
    )
}