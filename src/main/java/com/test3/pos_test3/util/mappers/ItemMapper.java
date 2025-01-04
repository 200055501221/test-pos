package com.test3.pos_test3.util.mappers;

import com.test3.pos_test3.dto.request.ItemSaveRequestDTO;
import com.test3.pos_test3.dto.response.ItemGetResponseDTO;
import com.test3.pos_test3.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    List<ItemGetResponseDTO> entityListToDtoList(List<Item> items);
    ItemGetResponseDTO entityToDto(Item item);
    Item dtoToEntity(ItemSaveRequestDTO itemSaveRequestDTO);

    //Page<Item> items ------->   List<ItemGetResponseDTO> list
    List<ItemGetResponseDTO>ListDTOToPage(Page<Item> items);
}
