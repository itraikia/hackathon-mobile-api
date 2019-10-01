package com.ibram.hackathon


import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MyPlace (
    val latLng: MyLatLng ,
    val address : String
) : Serializable