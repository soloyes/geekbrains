package xyz.solovev.enterprise.service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationPath("/api")
public class REST extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(
                ProductsService.class,
                CategoriesService.class
        ).collect(Collectors.toSet());
    }
}
