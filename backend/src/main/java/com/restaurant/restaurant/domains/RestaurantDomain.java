package com.restaurant.restaurant.domains;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RestaurantDomain {


    private Integer id;
    @NotNull
    private Long userId;
    @NotNull
    private String name;
    private String address;
    private String location;
    @NotNull
    private String mobileNo;
    private String ownerName;
    private String licenceNo;
    @NotNull
    private Date openingDate;
    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;


}
