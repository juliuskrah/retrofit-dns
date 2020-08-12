package com.juliuskrah.demo

import android.util.Log
import okhttp3.Dns
import java.net.InetAddress

class ExampleDns : Dns {
    override fun lookup(hostname: String): MutableList<InetAddress> {
        val addresses = mutableListOf<InetAddress>()
        addresses.addAll(Dns.SYSTEM.lookup(hostname))
        val customDomains = listOf(
            "theshop.com",
            "bar.jaesoft.com",
            "foo.jaesoft.com",
            "dulcet.jaesoft.com",
            "aparel.jaesoft.com"
        )
        if (BuildConfig.DEBUG && customDomains.contains(hostname)) {
            Log.i("CustomDns", "Adding custom dns")
            addresses.add(InetAddress.getByName("192.168.100.6"))
            addresses.add(InetAddress.getByName("10.200.108.131"))
        }
        return addresses
    }
}