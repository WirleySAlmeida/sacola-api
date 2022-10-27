package me.dio.sacolaapi.service;

import me.dio.sacolaapi.model.Bag;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.resource.dto.ItemDto;

public interface BagService {
    Bag getBag(Long id);
    Bag closeBag(Long id, int paymentMethod);
    Item addItemToTheBag(ItemDto itemDto);
}
