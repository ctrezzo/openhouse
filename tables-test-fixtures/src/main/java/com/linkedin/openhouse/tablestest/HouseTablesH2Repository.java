package com.linkedin.openhouse.tablestest;

import com.linkedin.openhouse.internal.catalog.model.HouseTable;
import com.linkedin.openhouse.internal.catalog.model.HouseTablePrimaryKey;
import com.linkedin.openhouse.internal.catalog.repository.HouseTableRepository;
import java.util.Optional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * The {@link org.springframework.context.annotation.Bean} injected into /tables e2e tests when
 * communication to the implementation of {@link HouseTableRepository} is not needed. With {@link
 * Primary} annotation, this repository will be the default injection.
 */
@Repository
@Primary
public interface HouseTablesH2Repository extends HouseTableRepository {
  Optional<HouseTable> findByDatabaseIdIgnoreCaseAndTableIdIgnoreCase(
      String databaseId, String tableId);

  @Override
  default Optional<HouseTable> findById(HouseTablePrimaryKey houseTablePrimaryKey) {
    return this.findByDatabaseIdIgnoreCaseAndTableIdIgnoreCase(
        houseTablePrimaryKey.getDatabaseId(), houseTablePrimaryKey.getTableId());
  }
}
