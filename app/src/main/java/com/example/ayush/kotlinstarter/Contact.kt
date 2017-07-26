package com.example.ayush.kotlinstarter

import io.realm.RealmObject

/**
 * Created by ayush on 7/25/17.
 */
open class Contact : RealmObject(){
    var name : String?=null
    open var address : String?=null
    open var phone : Long?=null
}