package com.bjoggis.linode4j.adapter.out.api;

import com.bjoggis.linode4j.adapter.out.api.model.InstanceType;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeInstance;
import com.bjoggis.linode4j.adapter.out.api.model.LinodeVolume;
import com.bjoggis.linode4j.adapter.out.api.model.Page;
import com.bjoggis.linode4j.adapter.out.api.model.Region;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

@Validated
public interface LinodeInterface {

  @GetExchange("/v4/linode/instances")
  Page<LinodeInstance> list(@RequestParam Integer page,
      @RequestParam("page_size") @Min(25) @Max(500) Integer pageSize);

  @GetExchange("/v4/linode/types")
  Page<InstanceType> types();

  @GetExchange("/v4/regions")
  Page<Region> regions();

  @PostExchange("/v4/linode/instances")
  LinodeInstance create(@RequestBody CreateInstanceRequestBody instance);

  @GetExchange("/v4/linode/instances/{linodeId}")
  LinodeInstance getInstance(@PathVariable Long linodeId);

  @DeleteExchange("/v4/linode/instances/{linodeId}")
  void delete(@PathVariable Long linodeId);


  @GetExchange("/v4/volumes")
  Page<LinodeVolume> volumes();

  @PostExchange("/v4/volumes/{volumeId}/attach")
  LinodeVolume attach(@PathVariable Long volumeId, @RequestBody AttachVolumeRequestBody body);

  @PostExchange("/v4/volumes/{volumeId}/detach")
  LinodeVolume detach(@PathVariable Long volumeId);
}
