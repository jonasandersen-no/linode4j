package com.bjoggis.linode4j.adapter.out.db;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LinodeInstanceJpaRepository extends JpaRepository<LinodeInstanceDbo, Long> {

  Optional<LinodeInstanceDbo> findByLinodeId(Long linodeId);

  void deleteByLinodeId(Long id);
}