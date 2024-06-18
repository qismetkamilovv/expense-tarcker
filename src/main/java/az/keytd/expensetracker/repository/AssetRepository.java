package az.keytd.expensetracker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import az.keytd.expensetracker.entities.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    Optional<Asset> findById(Long id);

    void deleteById(Long id);

    Optional<Asset> findByName(String name);

    // @Query("select a from Asset a where a.user.id=:ownerId")
    @Query(value = "select * from Asset  where owner_id = ?", nativeQuery = true)
    List<Asset> findAllByOwnerId(@Param("ownerId") Long ownerId);

    Asset getById(Long id);
}
