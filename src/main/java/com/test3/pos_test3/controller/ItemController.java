package com.test3.pos_test3.controller;

import com.test3.pos_test3.dto.paginated.PaginatedResponseItemDTO;
import com.test3.pos_test3.dto.request.ItemSaveRequestDTO;
import com.test3.pos_test3.dto.response.ItemGetResponseDTO;
import com.test3.pos_test3.service.ItemService;
import com.test3.pos_test3.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping(
            path= {"/save"}
    )
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO ) {
        String message=itemService.saveItem(itemSaveRequestDTO);

//        ResponseEntity<StandardResponse> response=new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message), HttpStatus.CREATED
//        );
//        return response;
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",message), HttpStatus.CREATED
        );

    }

    @GetMapping(
            path = "/get-by-name",
            params="name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus( @RequestParam(value = "name") String itemName ) {
        List <ItemGetResponseDTO> itemDTOS=itemService.getItemByNameAndStatus(itemName);
        return itemDTOS;

    }

    @GetMapping(
            path = "/get-by-name-with-mapstruct",
            params="name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatusByMapstruct( @RequestParam(value = "name") String itemName ) {
        List <ItemGetResponseDTO> itemDTOS=itemService.getItemByNameAndStatusByMapstruct(itemName);
        return itemDTOS;

    }

    @GetMapping(
            path = "/get-all-item-by-status",
            params={"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getItemsByActiveStatus(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {
       // List <ItemGetResponseDTO> itemDTOS=itemService.getItemsByActiveStatus(activeStatus);
        PaginatedResponseItemDTO paginatedResponseItemDTO=itemService.getItemsByActiveStatusWithPaginated(activeStatus,page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",paginatedResponseItemDTO),
                HttpStatus.CREATED
        );

    }

    @GetMapping(
            path = "/get-all-active-items-paginated",
            params={"activeStatus","page","size"}
    )
    public ResponseEntity<StandardResponse> getAllActiveItemsPaginated(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") @Max(50) int size
    ) {
        // List <ItemGetResponseDTO> itemDTOS=itemService.getItemsByActiveStatus(activeStatus);
        PaginatedResponseItemDTO paginatedResponseItemDTO=itemService.getAllActiveItemsPaginated(activeStatus,page,size);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",paginatedResponseItemDTO),
                HttpStatus.CREATED
        );

    }
}
