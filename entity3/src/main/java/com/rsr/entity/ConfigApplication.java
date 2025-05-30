//
package com.rsr.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigApplication implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	System.out.println("**************** Config Run ***************");
        registry.addMapping("/**") // "/**" aplica a todos los endpoints
                .allowedOrigins("http://localhost:4200", "http://otrodominio.com") // Orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Métodos HTTP permitidos
                .allowedHeaders("*", "Content-Type", "Authorization") // Cabeceras permitidas
                .allowCredentials(false) // Permite enviar cookies de autenticación
                .maxAge(3600); // Tiempo de vida de la pre-flight response en segundos
    }
}

//