package com.shvk.springbootmongodb.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String addressLine1;
    private String addressLine2;
    private String city;
}
