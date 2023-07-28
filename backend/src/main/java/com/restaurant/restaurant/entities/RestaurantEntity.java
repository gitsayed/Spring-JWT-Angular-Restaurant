package com.restaurant.restaurant.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restaurant.category.entities.CategoryEntity;
import com.restaurant.user.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "Restaurant",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name")
        })
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private String name;
    private String address;
    private String location;
    private String mobileNo;
    private String ownerName;
    private String licenceNo;
    private Date openingDate;

    @JsonIgnore
    private String createdBy;
    @JsonIgnore
    private String updatedBy;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
