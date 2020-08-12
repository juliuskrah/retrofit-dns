package com.juliuskrah.demo

import android.util.Log
import okhttp3.Dns
import java.net.InetAddress

class ExampleDns : Dns {
    override fun lookup(hostname: String): MutableList<InetAddress> {
        val addresses = mutableListOf<InetAddress>()
        val properties = Singleton.properties()
        val customDomains = properties.customDomains
        Log.d(TAG, "Found: ${customDomains.joinToString()}")
        if (BuildConfig.DEBUG && customDomains.contains(hostname)) {
            Log.d(TAG, "Adding custom dns")
            addresses.addAll(properties.ip4Addresses)
        }
        addresses.addAll(Dns.SYSTEM.lookup(hostname))
        Log.d(TAG, "Resolved addresses $addresses")
        return addresses
    }

    companion object{
        val TAG = ExampleDns::class.simpleName
    }
}