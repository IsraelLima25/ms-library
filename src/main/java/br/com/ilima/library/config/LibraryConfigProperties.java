package br.com.ilima.library.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
public class LibraryConfigProperties {

    @ConfigProperty(name = "app.store.location")
    String locationNow;

    public String getLocationNow() {
        return locationNow;
    }
}
