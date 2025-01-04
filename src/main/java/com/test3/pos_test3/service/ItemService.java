package com.test3.pos_test3.service;

import com.test3.pos_test3.dto.paginated.PaginatedResponseItemDTO;
import com.test3.pos_test3.dto.request.ItemSaveRequestDTO;
import com.test3.pos_test3.dto.response.ItemGetResponseDTO;
import jakarta.validation.constraints.Max;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName);

    List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus);

    PaginatedResponseItemDTO getItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size);

    PaginatedResponseItemDTO getAllActiveItemsPaginated(boolean activeStatus, int page, @Max(50) int size);
}
