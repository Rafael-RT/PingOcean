package com.example.pingocean.data

import com.google.gson.annotations.SerializedName

data class Profile (
  @SerializedName("id")
  var id: String?= null,
  @SerializedName("name")
  var name: String?= null,
  @SerializedName("email")
  var email: String?= null,
  @SerializedName("phone")
  var phone: String?= null,
  @SerializedName("avatar")
  var avatar: String?= null,
  @SerializedName("position")
  var position: String?= null,
  @SerializedName("company_name")
  var companyName: String?= null,
  @SerializedName("name_eng")
  var nameEng: String?= null,
  @SerializedName("timezone")
  var timezone: String?= null,
  @SerializedName("sections")
  var sections: ArrayList<String> = arrayListOf(),
  @SerializedName("alert_email")
  var alertEmail: String?= null,
  @SerializedName("send_system_notifications")
  var sendSystemNotifications: Boolean?= null

)