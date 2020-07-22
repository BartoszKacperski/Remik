package com.rolnik.remik.mappers

import com.rolnik.remik.dtos.PlayerResponse
import com.rolnik.remik.model.Player

class PlayerResponseMapper : Mapper<Player, PlayerResponse> {
    override fun mapFrom(toMap: PlayerResponse): Player =
            Player(
                    toMap.id,
                    toMap.firstName,
                    toMap.nickName
            )


    override fun mapTo(mapFrom: Player): PlayerResponse =
            PlayerResponse(
                    mapFrom.id,
                    mapFrom.firstName,
                    mapFrom.nickname
            )
}