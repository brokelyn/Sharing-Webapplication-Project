package de.hhu.propra.sharingplatform.dao;

import de.hhu.propra.sharingplatform.model.ItemRental;
import java.util.List;
import java.util.Optional;

public interface ItemRentalRepo extends ItemRepo<ItemRental> {
    ItemRental findOneById(long id);

    Optional<ItemRental> findById(long id);

    List<ItemRental> findAllByNameContainsIgnoreCase(String search);
}
