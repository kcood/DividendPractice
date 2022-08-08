package com.example.divined.persist;

import com.example.divined.persist.entity.CompanyEntity;
import com.example.divined.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
}
