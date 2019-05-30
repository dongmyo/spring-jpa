package com.nhn.edu.jpa.dto;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface MemberDto {
    String getName();
    List<MemberDetailDto> getDetails();

    interface MemberDetailDto {
        @Value("#{target.pk.type}")
        String getType();

        String getDescription();
        
    }

}
