package com.kaiba.coctailoo.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId

 class Comment: RealmObject {
     @PrimaryKey
    var _id: ObjectId = ObjectId()
    var userId: String = ""
    var comment: String = ""
}