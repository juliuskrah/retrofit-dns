package com.juliuskrah.demo

import android.util.Log
import okhttp3.Dns
import java.net.InetAddress

class CustomDns : Dns {
    override fun lookup(hostname: String): MutableList<InetAddress> {
        val addresses = mutableListOf<InetAddress>()
        addresses.addAll(Dns.SYSTEM.lookup(hostname))
        val customDomains = listOf(
            "theshop.com",
            "foo.theshop.com",
            "bar.theshop.com",
            "dulcet.theshop.com",
            "aparel.theshop.com"
        )
        if (BuildConfig.DEBUG && customDomains.contains(hostname)) {
            Log.i("CustomDns", "Adding custom dns")
            addresses.add(InetAddress.getByName("192.168.100.6"))
        }
        return addresses
    }
}