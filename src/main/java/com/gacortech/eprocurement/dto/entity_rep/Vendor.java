package com.gacortech.eprocurement.dto.entity_rep;

import com.gacortech.eprocurement.constant.ResponseMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Vendor {
    String id;

    @NotBlank(message = ResponseMessages.ERROR_EMPTY_NAME)
    String name;
}