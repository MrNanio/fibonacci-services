package com.nankiewic.serverapp.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KeyValueDTO {

    String value;
    String key;
}

