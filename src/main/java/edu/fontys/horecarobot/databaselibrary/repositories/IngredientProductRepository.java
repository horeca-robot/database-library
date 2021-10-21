package edu.fontys.horecarobot.databaselibrary.repositories;

import edu.fontys.horecarobot.databaselibrary.models.IngredientProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IngredientProductRepository extends JpaRepository<IngredientProduct, UUID> {

}
