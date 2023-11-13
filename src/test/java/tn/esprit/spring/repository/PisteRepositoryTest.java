package tn.esprit.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
public class PisteRepositoryTest {
    @Autowired
    private IPisteRepository pisteRepository;

    private Piste piste1;
    private Piste piste2;

    @BeforeEach
    void init() {
        piste1 = new Piste( "test1" );
        piste2 = new Piste( "Test"  );
    }

    @Test
    @DisplayName("It should save the Piste to the database")
    void save() {
        Piste newPiste = pisteRepository.save(piste1);
        assertNotNull(newPiste);
        assertThat(newPiste.getNamePiste()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the students list with size of 2")
    void getEtudiants() {
        pisteRepository.save(piste1);
        pisteRepository.save(piste2);

        List<Piste> list = (List<Piste>) pisteRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }
}
