package tn.esprit.spring.Service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
 class SkierServiceTest {

    @Mock
    private ISkierRepository skierRepository;

    @InjectMocks
    private SkierServicesImpl skierServices ;

    private Skier skier1;
    private Skier skier2;

    @BeforeEach
    void init() {
        skier1 = new Skier( "test" );
        skier2 = new Skier( "Test" );

    }

    @Test
    void save() {
        Subscription subscription = new Subscription();
        // Set properties for the subscription (startDate, typeSub, etc.)
        subscription.setStartDate(LocalDate.now());
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        skier1.setSubscription(subscription);
        when(skierRepository.save(any(Skier.class))).thenReturn(skier1);
        Skier newSkier = skierServices.addSkier(skier1);
        assertNotNull(newSkier);
        assertThat(newSkier.getFirstName()).isEqualTo("test");
    }

    @Test
    void getSkiers() {
        List<Skier> list = new ArrayList<>();
        list.add(skier1);
        list.add(skier2);

        when(skierRepository.findAll()).thenReturn(list);

        List<Skier> movies = skierServices.retrieveAllSkiers();

        assertEquals(2, movies.size());
        assertNotNull(movies);
    }
}