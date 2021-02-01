package com.gratus.petservice.model.common

import java.io.Serializable

class UserAdapterModel:Serializable {
    var e_mail: String? = null
    var userID: String? = null
    var name: String? = null
    constructor()
    constructor(e_mail: String?, userID: String?) {
        this.e_mail = e_mail
        this.userID = userID
    }

    constructor(e_mail: String?, userID: String?, name: String?) {
        this.e_mail = e_mail
        this.userID = userID
        this.name = name
    }
}
