package com.ascri.test.data.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ImageResponse(
    var id: Long = 0,
    var email: String = "",
    var name: String = "",
    var role: String = "",

    @JsonProperty("identity_reference")
    var identityReference: String = "",

    @JsonProperty("crm_id")
    var crmId: String = "",

    @JsonProperty("is_confirmed")
    var isConfirmed: Boolean = false,

    @JsonProperty("failed_login_count")
    var failedLoginCount: Int = 0,

    @JsonProperty("company_crm_id")
    var companyCrmId: String = "",

    @JsonProperty("company_name")
    var companyName: String = "",

    @JsonProperty("phone_number")
    var phoneNumber: String = "",

    @JsonProperty("session_token")
    var sessionToken: String = "",

    @JsonProperty("token_expires_at")
    var tokenExpiresAt: Date? = null,

    @JsonProperty("permission_groups")
    var permissionGroups: MutableList<String> = mutableListOf()
)