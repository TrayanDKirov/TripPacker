package bg.sofia.uni.fmi.tdkirov.trippacker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageResponseDto {
    String userMessage;
    String developerMessage;
}
