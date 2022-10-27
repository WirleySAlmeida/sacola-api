package me.dio.sacolaapi.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.model.Bag;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.BagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "/ifood-dev-week/sacolas")
@RestController
@RequestMapping("/ifood-dev-week/sacolas")
@RequiredArgsConstructor
public class BagResource {

    private final BagService bagService;

    @PostMapping
    public ResponseEntity<Item> addItemToTheBag(@RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(bagService.addItemToTheBag(itemDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bag> getBag(@PathVariable Long id) {
        return ResponseEntity.ok(bagService.getBag(id));
    }

    @PatchMapping("/fecharSacola/{id}")
    public ResponseEntity<Bag> closeBag(@PathVariable Long id, @RequestParam int paymentMethod) {
        return ResponseEntity.ok(bagService.closeBag(id, paymentMethod));
    }
}
