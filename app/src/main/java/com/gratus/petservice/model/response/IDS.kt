package com.gratus.petservice.model.response

import java.io.Serializable

class IDS : Serializable {
    var info: Map<String, PetDetails>? = null

    constructor(info: Map<String, PetDetails>?) {
        this.info = info
    }

    constructor()

}