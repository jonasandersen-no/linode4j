package com.bjoggis.linode4j.adapter.out.db;

import com.bjoggis.linode4j.application.port.InstanceRepository;
import com.bjoggis.linode4j.domain.Instance;
import com.bjoggis.linode4j.domain.LinodeId;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
class LinodeInstanceDataAdapter implements InstanceRepository {

  private final LinodeInstanceJpaRepository repository;

  public LinodeInstanceDataAdapter(LinodeInstanceJpaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Optional<Instance> findByLinodeId(LinodeId linodeId) {
    LinodeInstanceDbo dbo = repository.findByLinodeId(linodeId.id()).orElseThrow();
    return dbo.toInstanceOptional();
  }

  @Override
  public void deleteByLinodeId(LinodeId linodeId) {
    repository.deleteByLinodeId(linodeId.id());
  }

  @Override
  public void save(Instance linodeInstance) {
    LinodeInstanceDbo dbo = LinodeInstanceDbo.fromInstance(linodeInstance);
    repository.save(dbo);
  }

}
