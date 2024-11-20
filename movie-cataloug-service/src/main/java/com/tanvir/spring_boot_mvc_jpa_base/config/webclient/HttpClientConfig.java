//package com.tanvir.spring_boot_mvc_jpa_base.config.webclient;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.http.HttpClient;
//import java.time.Duration;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class HttpClientConfig {
//
//    /*@Value("${keystore.instance}")
//    private String KEYSTORE_INSTANCE;
//
//    @Value("${keystore.classpath}")
//    private String KEYSTORE_CLASSPATH;
//
//    @Value("${keystore.password}")
//    private String KEYSTORE_PASSWORD;*/
//    @Value("${gateway.timeout.in.ms}")
//    private int TIMEOUT;
//
//    @Bean
//    public HttpClient reactiveHttpClientWithTimeout() {
//        return HttpClient
//                .create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
//                .responseTimeout(Duration.ofMillis(TIMEOUT))
//                .doOnConnected(connection -> {
//                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                })
//                .wiretap("reactor.netty.http.client.HttpClient",
//                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
//    }
//
//    /*@Bean
//    public HttpClient secureHttpClient() {
//        return HttpClient
//                .create()
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
//                .responseTimeout(Duration.ofMillis(TIMEOUT))
//                .doOnConnected(connection -> {
//                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
//                })
//                .secure(sslContextSpec -> sslContextSpec.sslContext(createSSLContext()))
//                .wiretap("reactor.netty.http.client.HttpClient",
//                        LogLevel.DEBUG, AdvancedByteBufFormat.TEXTUAL);
//    }
//
//    private SslContext createSSLContext() {
//        KeyStore keyStore;
//        try {
//            keyStore = KeyStore.getInstance(KEYSTORE_INSTANCE);
//            ClassPathResource classPathResource = new ClassPathResource(KEYSTORE_CLASSPATH);
//
//            InputStream inputStream = classPathResource.getInputStream();
//            keyStore.load(inputStream, KEYSTORE_PASSWORD.toCharArray());
//
//            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//            keyManagerFactory.init(keyStore, KEYSTORE_PASSWORD.toCharArray());
//
//            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//            trustManagerFactory.init(keyStore);
//
//            return SslContextBuilder.forClient()
//                    .keyManager(keyManagerFactory)
//                    .trustManager(trustManagerFactory)
//                    .build();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Error creating SSL context !!!!");
//        }
//    }
//*/
//}
