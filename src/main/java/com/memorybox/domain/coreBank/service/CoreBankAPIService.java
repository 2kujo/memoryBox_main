package com.memorybox.domain.coreBank.service;

import com.memorybox.dto.request.AccountCreateRequestDto;
import com.memorybox.dto.request.BalanceUpdateRequestDto;
import com.memorybox.dto.response.CoreBankResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class CoreBankAPIService {
    private final String defaultApiUrl = "http://memorybox-external:8080/core-bank";
    private final RestTemplate restTemplate;

    public CoreBankResponseDto fetchCashBoxDataFromCoreBankAPI(long userId, String productName) {
        String urlWithParameters = UriComponentsBuilder.fromUriString(defaultApiUrl)
                .path("/account")
                .build()
                .toUriString();
        AccountCreateRequestDto requestDto = new AccountCreateRequestDto(userId, productName);
        return restTemplate.postForObject(urlWithParameters, requestDto, CoreBankResponseDto.class);
    }

    public Integer depositMoney(long coreBankId, int depositAmount) {
        String url = UriComponentsBuilder.fromUriString(defaultApiUrl)
                .path("/balance")
                .build()
                .toUriString();
        BalanceUpdateRequestDto requestDto = new BalanceUpdateRequestDto(coreBankId, depositAmount);

        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());

        return restTemplate.patchForObject(url, requestDto, Integer.class);
    }
}
