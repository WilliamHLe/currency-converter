package valutaKalk.restserver;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import valutaKalk.core.Valuta;
import valutaKalk.restapi.ValutaService;

public class ValutaConfig extends ResourceConfig {

  final Valuta valuta;

  public ValutaConfig() {
    this(new Valuta());
  }

  public ValutaConfig(final Valuta valuta) {
    this.valuta = valuta;
    register(ValutaService.class);
    register(JacksonFeature.class);

    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(valuta);
      }
    });
  }
}
