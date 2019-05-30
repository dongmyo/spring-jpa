package com.nhn.edu.jpa.dto;

import java.util.List;

public interface MemberDescriptionOnly {
    List<DescriptionOnly> getDetails();

    interface DescriptionOnly {
        String getDescription();
    }

}
