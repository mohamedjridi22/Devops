package tn.esprit.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class PisteServiceTest {

    @Mock
    private IPisteRepository pisteRepository;

    @InjectMocks
    private PisteServicesImpl pisteServices ;

    private Piste piste1;
    private Piste piste2;

    @BeforeEach
    void init() {
        piste1 = new Piste( "test" );
        piste2 = new Piste( "Test" );
    }

    @Test
    void save() {
        when(pisteRepository.save(any(Piste.class))).thenReturn(piste1);
        Piste newPiste = pisteServices.addPiste(piste1);
        assertNotNull(newPiste);
        assertThat(newPiste.getNamePiste()).isEqualTo("test");
    }

    @Test
    void getEtudiants() {
        List<Piste> list = new ArrayList<>();
        list.add(piste1);
        list.add(piste2);

        when(pisteRepository.findAll()).thenReturn(list);

        List<Piste> movies = pisteServices.retrieveAllPistes();

        assertEquals(2, movies.size());
        assertNotNull(movies);
    }
}