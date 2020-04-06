package com.ascri.test.data.models

import com.fasterxml.jackson.annotation.JsonProperty

data class User(
    @JsonProperty("name")
    var name: String? = null,
    @JsonProperty("image")
    var image: String? = null,
    @JsonProperty("items")
    var items: List<String>? = null
)