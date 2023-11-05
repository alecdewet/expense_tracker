package expense_tracker

import groovy.json.JsonSlurper
import grails.gorm.transactions.Transactional
import java.nio.charset.StandardCharsets
import java.time.ZonedDateTime
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClient.*
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

@Transactional
class CurrencyConverterService {

    double getExchangeRate() {
        // To be honest, I need to learn more about this, because I am currently not sure how this works.
        // I do know the HttpClient and the WebClient should be a shared resource during a session to facilitate multiple requests from the same client,
        // but currently not sure how to do that right now since I am still quite a beginner with this framework
        HttpClient httpClient = HttpClient.create()
        WebClient client = WebClient.builder()
                                .clientConnector(new ReactorClientHttpConnector(httpClient))
                                .build()
                            
        UriSpec<RequestBodySpec> uriSpec = client.get();
        RequestBodySpec bodySpec = uriSpec.uri("https://api.freecurrencyapi.com/v1/latest?apikey=fca_live_vZ9PPBQ3ebn4EnCW34JvJm2a59AUn5jhXy6C8j3a&currencies=USD&base_currency=ZAR")
        RequestHeadersSpec<?> headersSpec = bodySpec.body(BodyInserters.fromValue("data"))
        ResponseSpec responseSpec = headersSpec.header(
            HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .ifModifiedSince(ZonedDateTime.now())
            .retrieve()
        Mono<String> response = headersSpec.retrieve().bodyToMono(String.class)

        def slurper = new JsonSlurper()
        def result = slurper.parseText(response.block())
        result.data.USD
    }
}