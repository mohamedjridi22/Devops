package tn.esprit.spring.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ActiveProfiles("test") // Specify the "test" profile
public class SkierRepositoryTest {
    @Autowired
    private ISkierRepository skierRepository;

    private Skier skier1;
    private Skier skier2;

    @BeforeEach
    void init() {
        skier1 = new Skier("test1" );
        skier2 = new Skier("Test" );;
    }

    @Test
    @DisplayName("It should save the student to the database")
    void save() {
        Skier newSkier = skierRepository.save(skier2);
        assertNotNull(newSkier);
        assertThat(newSkier.getFirstName()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the students list with size of 2")
    void getSkiers() {
        skierRepository.save(skier1);
        skierRepository.save(skier2);

        List<Skier> list = (List<Skier>) skierRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(2, list.size());
    }
}