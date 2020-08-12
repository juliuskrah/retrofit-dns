package com.juliuskrah.demo

import java.net.InetAddress

data class ApplicationProperties(
    val hostname: String,
    val customDomains: List<String>,
    val ip4Addresses: List<InetAddress>
)