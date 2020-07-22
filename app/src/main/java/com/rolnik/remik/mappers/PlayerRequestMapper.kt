package com.rolnik.remik.mappers

import com.rolnik.remik.dtos.PlayerRequest
import com.rolnik.remik.model.Player

class PlayerRequestMapper : Mapper<Player, PlayerRequest> {
    override fun mapFrom(toMap: PlayerRequest): Player =
            Player(
                    0,
                    toMap.firstName,
                    toMap.nickName
            )

    override fun mapTo(mapFrom: Player): PlayerRequest =
            PlayerRequest(
                    mapFrom.firstName,
                    mapFrom.nickname
            )
}