//package com.bjoggis.linode4j.adapter.out.db;
//
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import com.bjoggis.linode4j.domain.Instance;
//import com.bjoggis.linode4j.domain.LinodeId;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//import java.util.Optional;
//import org.assertj.core.data.TemporalUnitWithinOffset;
//import org.junit.jupiter.api.Test;
//
//class LinodeInstanceDboTest {
//
//  @Test
//  void fromInstance() {
//    Instance instance = new Instance();
//    instance.setId(LinodeId.of(1L));
//    instance.setStatus("running");
//    instance.setLabel("test");
//    instance.setIp("127.0.0.1");
//    instance.setCreated(LocalDateTime.now());
//
//    LinodeInstanceDbo dbo = LinodeInstanceDbo.fromInstance(instance);
//
//    assertThat(dbo.getLinodeId()).isEqualTo(instance.getId().id());
//    assertThat(dbo.getLabel()).isEqualTo(instance.getLabel());
//    assertThat(dbo.getIp()).isEqualTo(instance.getIp());
//    assertThat(dbo.getStatus()).isEqualTo(instance.getStatus());
//    assertThat(dbo.getCreated()).isCloseTo(new Date(), 1000);
//
//  }
//
//  @Test
//  void toInstanceOptional() {
//    LinodeInstanceDbo dbo = new LinodeInstanceDbo();
//    dbo.setLinodeId(1L);
//    dbo.setLabel("test");
//    dbo.setIp("127.0.0.1");
//    dbo.setStatus("running");
//    dbo.setCreated(new Date());
//
//    Optional<Instance> instanceOptional = dbo.toInstanceOptional();
//
//    assertThat(instanceOptional).isPresent();
//  }
//
//  @Test
//  void toInstance() {
//    LinodeInstanceDbo dbo = new LinodeInstanceDbo();
//    dbo.setLinodeId(1L);
//    dbo.setLabel("test");
//    dbo.setIp("127.0.0.1");
//    dbo.setStatus("running");
//    dbo.setCreated(new Date());
//
//    Instance instance = dbo.toInstance();
//
//    assertThat(instance.getId()).isEqualTo(LinodeId.of(dbo.getLinodeId()));
//    assertThat(instance.getLabel()).isEqualTo(dbo.getLabel());
//    assertThat(instance.getIp()).isEqualTo(dbo.getIp());
//    assertThat(instance.getStatus()).isEqualTo(dbo.getStatus());
//    assertThat(instance.getCreated()).isCloseTo(LocalDateTime.now(),
//        new TemporalUnitWithinOffset(1, ChronoUnit.SECONDS));
//  }
//}