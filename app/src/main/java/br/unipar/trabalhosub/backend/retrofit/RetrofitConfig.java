package br.unipar.trabalhosub.backend.retrofit;

import br.unipar.trabalhosub.backend.service.IPaisService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private static final String BASE_URL = "https://falabr.cgu.gov.br/api/";
    private Retrofit retrofit;

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create()).build();

    }

    public IPaisService paisService(){
        return this.retrofit.create(IPaisService.class);
    }
}
