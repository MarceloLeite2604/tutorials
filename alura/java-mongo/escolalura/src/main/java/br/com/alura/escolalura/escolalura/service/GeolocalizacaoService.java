package br.com.alura.escolalura.escolalura.service;

import br.com.alura.escolalura.escolalura.models.Contato;
import br.com.alura.escolalura.escolalura.repositories.AlunoRepository;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class GeolocalizacaoService {

    public List<Double> obterLatLongPor(Contato contato) throws InterruptedException, ApiException, IOException {

        String googleApiKey = System.getenv("GOOGLE_API_KEY");
        GeoApiContext context = new GeoApiContext.Builder().apiKey(googleApiKey)
                .build();

        GeocodingApiRequest request = GeocodingApi.newRequest(context)
                .address(contato.getEndereco());

        GeocodingResult[] results = request.await();

        GeocodingResult result = results[0];

        Geometry geometry = result.geometry;

        LatLng location = geometry.location;

        return Arrays.asList(location.lat, location.lng);
    }
}
