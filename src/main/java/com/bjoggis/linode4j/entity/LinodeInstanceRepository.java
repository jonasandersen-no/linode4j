package com.bjoggis.linode4j.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinodeInstanceRepository extends JpaRepository<LinodeInstance, Long> {

  Optional<LinodeInstance> findByLinodeId(Long linodeId);


  void deleteByLinodeId(Long id);
}