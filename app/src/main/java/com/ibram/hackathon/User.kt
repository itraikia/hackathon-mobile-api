package com.ibram.hackathon

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User (
    @SerializedName("_id")
    val email : String ,
    val username : String? = "",
    val token : String? = ""
) : Serializable