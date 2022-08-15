package com.dayone.persist;

import com.dayone.persist.entity.DividendEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
<<<<<<< HEAD
=======
import org.springframework.transaction.annotation.Transactional;
>>>>>>> c283280 (Initial commit)

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
<<<<<<< HEAD

    List<DividendEntity> findAllByCompanyId(Long id);
=======
    List<DividendEntity> findAllByCompanyId(Long companyId);

    @Transactional
    void deleteAllByCompanyId(Long id);
>>>>>>> c283280 (Initial commit)

    boolean existsByCompanyIdAndDate(Long companyId, LocalDateTime date);
}
