package me.dio.sacolaapi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaapi.enumeration.PaymentMethod;
import me.dio.sacolaapi.model.Bag;
import me.dio.sacolaapi.model.Item;
import me.dio.sacolaapi.model.Restaurant;
import me.dio.sacolaapi.repository.BagRepository;
import me.dio.sacolaapi.repository.ItemRepository;
import me.dio.sacolaapi.repository.ProductRepository;
import me.dio.sacolaapi.resource.dto.ItemDto;
import me.dio.sacolaapi.service.BagService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    @Override
    public Bag getBag(Long id) {
        return bagRepository.findById(id).orElseThrow(
            () -> {
                throw new RuntimeException("Esta sacola não existe!");
            }
        );
    }

    @Override
    public Bag closeBag(Long id, int paymentMethodNumber) {
        Bag bag = getBag(id);

        if (bag.getItens().isEmpty()) {
            throw new RuntimeException("Inclua itens na sacola");
        }

        PaymentMethod paymentMethod = paymentMethodNumber == 0 ? PaymentMethod.CASH : PaymentMethod.PAYMENT_TERMINAL;

        bag.setPaymentMethod(paymentMethod);

        bag.setClosed(true);

        return bagRepository.save(bag);
    }

    @Override
    public Item addItemToTheBag(ItemDto itemDto) {
        Bag bag = getBag(itemDto.getBagId());

        if (bag.isClosed()) {
            throw new RuntimeException("Esta sacola está fechada.");
        }

        Item itemToBeAdded = Item.builder()
                .quantity(itemDto.getQuantity())
                .bag(bag)
                .product(productRepository.findById(itemDto.getProductId()).orElseThrow(
                    () -> {
                        throw new RuntimeException("Esse produto não existe!");
                    }
                ))
                .build();

        List<Item> itens = bag.getItens();

        if (itens.isEmpty()) {
            itens.add(itemToBeAdded);
        } else {
            Restaurant currentRestaurant = itens.get(0).getProduct().getRestaurant();
            Restaurant itemToBeAddedRestaurant = itemToBeAdded.getProduct().getRestaurant();
            if (currentRestaurant.equals(itemToBeAddedRestaurant)) {
                itens.add(itemToBeAdded);
            } else {
                throw new RuntimeException("Não é possível adicionar produtos de restaurantes diferentes. " +
                        "Feche a sacola ou esvazie");
            }
        }

        List<Double> valueOfItens = new ArrayList<>();

        for (Item itemOfBag : itens) {
            double itemTotalValue = itemOfBag.getProduct().getUnitValue() * itemOfBag.getQuantity();
            valueOfItens.add(itemTotalValue);
        }

        double bagTotalValue = valueOfItens.stream().mapToDouble(eachItemTotalValue -> eachItemTotalValue).sum();

        bag.setTotalValue(bagTotalValue);

        bagRepository.save(bag);

        return itemToBeAdded;
    }
}
