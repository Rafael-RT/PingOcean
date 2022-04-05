package com.example.pingocean.data

import com.google.gson.annotations.SerializedName


data class AuthResponse (
  @SerializedName("token")
  var token: String? = null,
  @SerializedName("sections")
  var sections: ArrayList<String> = arrayListOf()
)