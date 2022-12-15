package org.example.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String email;
    private String password;
    private String card;
    private String cardDate;
    private String cvv;
    private int zip;
}
