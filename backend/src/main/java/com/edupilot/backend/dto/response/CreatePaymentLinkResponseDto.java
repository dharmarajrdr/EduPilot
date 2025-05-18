package com.edupilot.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentLinkResponseDto {

    private String url;

    private Long orderId;

    private boolean paymentRequired;

    private String message;
}
