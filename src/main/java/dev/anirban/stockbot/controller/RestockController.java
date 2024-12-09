package dev.anirban.stockbot.controller;

import dev.anirban.stockbot.dto.common.RestockDto;
import dev.anirban.stockbot.dto.response.ResponseWrapper;
import dev.anirban.stockbot.entity.Employee;
import dev.anirban.stockbot.entity.Restock;
import dev.anirban.stockbot.service.RestockService;
import dev.anirban.stockbot.util.UrlConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestockController {

    private final RestockService service;

    @PostMapping(UrlConstants.CREATE_RESTOCK)
    public ResponseWrapper<RestockDto> create(
            @AuthenticationPrincipal Employee employee,
            @RequestBody RestockDto restockDto
    ) {
        RestockDto data = service.create(employee, restockDto).toRestockDto();
        return new ResponseWrapper<>(data);
    }

    @GetMapping(UrlConstants.FIND_ALL_RESTOCK)
    public ResponseWrapper<List<RestockDto>> findAll() {
        List<RestockDto> data = service
                .findAll()
                .stream()
                .map(Restock::toRestockDto)
                .toList();

        return new ResponseWrapper<>(data);
    }
}