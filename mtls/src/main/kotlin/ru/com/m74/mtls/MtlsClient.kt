package ru.com.m74.mtls

import org.apache.http.conn.ssl.NoopHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.impl.client.HttpClients
import org.apache.http.ssl.SSLContextBuilder
import org.apache.http.ssl.SSLContexts
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.util.ResourceUtils
import org.springframework.web.client.RestTemplate
import java.io.File
import java.io.FileInputStream
import java.security.KeyStore
import java.security.cert.X509Certificate


fun main(args: Array<String>) {

    val url = "https://localhost:8443"
    val keyStoreLocation = "certs/client.p12"
    val keyStoreType = "PKCS12"
    val keyStorePassword = "changeit".toCharArray()
    val keyPassword = "changeit".toCharArray()

    val clientBuilder = HttpClients.custom()
    val sslContextBuilder: SSLContextBuilder = SSLContexts.custom()
        .loadKeyMaterial(keyStore(keyStoreLocation, keyStoreType, keyStorePassword), keyPassword)
        .loadTrustMaterial(null) { _: Array<X509Certificate?>?, s: String? -> true }

    val sslContext = sslContextBuilder.build()
    val csf = SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier())

    clientBuilder.setSSLSocketFactory(csf)

    val requestFactory = HttpComponentsClientHttpRequestFactory()
    requestFactory.httpClient = clientBuilder.build()

    val restTemplate = RestTemplate(requestFactory)


    println("url: $url")
    val resp = restTemplate.getForEntity(url, String::class.java)

    println(resp.body)
}


private fun keyStore(file: String, type: String, password: CharArray): KeyStore {
    val keyStore = KeyStore.getInstance(type)
    val key: File = ResourceUtils.getFile(file)
    FileInputStream(key).use { keyStore.load(it, password) }
    return keyStore
}