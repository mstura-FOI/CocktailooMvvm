package com.kaiba.coctailoo.model

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmCocktail: RealmObject{
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    var username: String = ""
    var cocktailName: String = ""
    var ingredients: RealmList<String> = realmListOf()
    var instructions: String = ""
    var comments: RealmList<Comment> = realmListOf()
}

