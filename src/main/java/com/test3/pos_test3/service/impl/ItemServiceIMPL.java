package com.test3.pos_test3.service.impl;

import com.test3.pos_test3.dto.paginated.PaginatedResponseItemDTO;
import com.test3.pos_test3.dto.request.ItemSaveRequestDTO;
import com.test3.pos_test3.dto.response.ItemGetResponseDTO;
import com.test3.pos_test3.entity.Customer;
import com.test3.pos_test3.entity.Item;
import com.test3.pos_test3.exception.NotFoundException;
import com.test3.pos_test3.repo.ItemRepo;
import com.test3.pos_test3.service.ItemService;
import com.test3.pos_test3.util.mappers.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item = new Item(
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringUnitType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        return item.getItemName();
        Item item = modelMapper.map(itemSaveRequestDTO, Item.class);
        if(!itemRepo.existsById((long) item.getItemId())) {
            itemRepo.save(item);
            return item.getItemId()+" saved Successfully";
        }
        else {
            throw new DuplicateKeyException("Already Added");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b=true;
        List<Item> items=itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);

        if(items.size()>0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS=modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());
            return itemGetResponseDTOS;
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct(String itemName) {
        boolean b=true;
        List<Item> items=itemRepo.findAllByItemNameEqualsAndActiveStateEquals(itemName,b);

        if(items.size()>0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }
        else {
            throw new RuntimeException("Not Found");
        }
    }

    @Override
    public List<ItemGetResponseDTO> getItemsByActiveStatus(boolean activeStatus) {

        List<Item> items=itemRepo.findAllByActiveStateEquals(activeStatus);

        if(items.size()>0) {
            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items);
            return itemGetResponseDTOS;
        }
        else {
            throw new NotFoundException("Item is not found");
        }
    }

    @Override
    public PaginatedResponseItemDTO getItemsByActiveStatusWithPaginated(boolean activeStatus, int page, int size) {

        Page<Item> items=itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page,size));
        int count=ItemRepo.countAllByActiveStateEquals(activeStatus);

        if(items.getSize()<1) {
            throw new NotFoundException("No Data");
        }
        PaginatedResponseItemDTO paginatedResponseItemDTO=new PaginatedResponseItemDTO(

                itemMapper.ListDTOToPage(items),
                count
        );
        return paginatedResponseItemDTO;
    }

    @Override
    public PaginatedResponseItemDTO getAllActiveItemsPaginated(boolean activeStatus, int page, int size) {


        return null;
    }
}
