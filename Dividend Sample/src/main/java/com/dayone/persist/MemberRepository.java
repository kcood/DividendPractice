package com.dayone.persist;

<<<<<<< HEAD
import com.dayone.model.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

=======
import com.dayone.persist.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
>>>>>>> c283280 (Initial commit)
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findByUsername(String username);

    boolean existsByUsername(String username);

<<<<<<< HEAD

=======
>>>>>>> c283280 (Initial commit)
}
