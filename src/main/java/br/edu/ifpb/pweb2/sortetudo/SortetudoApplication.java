package br.edu.ifpb.pweb2.sortetudo;

import br.edu.ifpb.pweb2.sortetudo.filtro.LoginFiltro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SortetudoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SortetudoApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<LoginFiltro> filterRegistrationBean() {
        FilterRegistrationBean<LoginFiltro> registrationBean = new FilterRegistrationBean<LoginFiltro>();
        LoginFiltro customURLFilter = new LoginFiltro();

        registrationBean.setFilter(customURLFilter);

        registrationBean.addUrlPatterns("/apostas/*");
        registrationBean.addUrlPatterns("/apostasFavoritas/*");
        registrationBean.addUrlPatterns("/sorteios/*");

        registrationBean.setOrder(1);
        return registrationBean;


    }
}
