package com.test3.pos_test3.dto.paginated;

import com.test3.pos_test3.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class PaginatedResponseItemDTO {
    List<ItemGetResponseDTO> item;
    private long dataCount;
}
