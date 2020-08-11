# Using a Custom DNS Resolver in Retrofit

This project contains a demo that illustrates using a custom DNS resolver in Retrofit2.

In this demo are two applications:

-[x] Service - A Spring Boot Server Side app
-[x] Android - An Android app that communicates with the Server Side app using a custom domain name

## Installation and running

You need to install Java, and Android Studio

Run the backend with the following command

```bash
> ./mvnw spring-boot:run
```

You need to configure your PC's firewall port to allow traffic on port 8080. You also need to get the IP address given to you by your local
network e.g. `192.168.100.6`

```kotlin
class CustomDns : Dns {
    override fun lookup(hostname: String): MutableList<InetAddress> {
        val addresses = mutableListOf<InetAddress>()
        addresses.addAll(Dns.SYSTEM.lookup(hostname))
        val customDomains = listOf(
            "theshop.com",
            "foo.theshop.com",
            "bar.theshop.com",
            "dulcet.theshop.com",
            "aparel.theshop.com" // add your custom domain here
        )
        if (BuildConfig.DEBUG && customDomains.contains(hostname)) {
            Log.i("CustomDns", "Adding custom dns")
            addresses.add(InetAddress.getByName("192.168.100.6")) // replace the IP here with your actual IP
        }
        return addresses
    }
}
```

Import the project `android` into Android Studio and start the project. Voila, you're done