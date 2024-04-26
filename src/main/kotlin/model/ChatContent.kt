package net.joshuabrandes.model

import com.fasterxml.jackson.annotation.JsonProperty

abstract class ChatContent @JvmOverloads
constructor(
    @JsonProperty("max_tokens") var maxTokens: Int = 500,
){

}