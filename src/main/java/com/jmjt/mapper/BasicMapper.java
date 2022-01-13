package com.jmjt.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.jmjt.dto.BasicDto;
import com.jmjt.model.Basic;
import com.jmjt.request.CreateRequest;

@Component
public class BasicMapper {

    public BasicDto mapBasic(Basic basic) {
        BasicDto basicDto = new BasicDto();
        basicDto.setId(basic.getId());
        basicDto.setName(basic.getName());
        return basicDto;
    }

    public Basic mapBasicDto(BasicDto basicDto) {
        Basic basic = new Basic();
        basic.setId(basicDto.getId());
        basic.setName(basicDto.getName());
        return basic;
    }

    public Basic mapCreateRequest(CreateRequest createRequest) {
        Basic basic = new Basic();
        basic.setName(createRequest.getName());
        return basic;
    }

    public List<BasicDto> map(List<Basic> basics) {
      return basics
                .stream()
                .map(basic -> mapBasic(basic))
                .collect(Collectors.toList());
    }
}