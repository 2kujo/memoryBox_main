package com.memorybox.domain.coreBank.service;

import com.memorybox.dto.response.CoreBankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class CoreBankAPIService {
    private final String defaultApiUrl = "http://memory-external:8080/";
    private final RestTemplate restTemplate;

    public CoreBankResponseDto fetchCashBoxDataFromCoreBankAPI(long userId, String productName) {
        String urlWithParameters = UriComponentsBuilder.fromUriString(defaultApiUrl)
                .queryParam("userId", userId)
                .queryParam("productName", productName)
                .build()
                .toUriString();
        return restTemplate.getForObject(urlWithParameters, CoreBankResponseDto.class);
    }
}
