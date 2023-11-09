package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
@SpringBootTest(properties = "spring.config.name=application-test")
class SubscriptionServicesImplTest {
    @Mock
    private ISubscriptionRepository mocksubscriptionRepository;

    @Autowired
    private ISubscriptionRepository subscriptionRepository;
    @Autowired
    private SubscriptionServicesImpl subscriptionService;

    @InjectMocks
    private SubscriptionServicesImpl mocksubscriptionService;

    @Test
    void addSubscription() {
        Subscription subscription = new Subscription();
        subscription.setStartDate(LocalDate.of(2023, 1, 1));
        subscription.setTypeSub(TypeSubscription.ANNUAL);

        when(mocksubscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription result = mocksubscriptionService.addSubscription(subscription);

        verify(mocksubscriptionRepository, times(1)).save(any(Subscription.class));

    }
    @Test
    void  addSubscriptionA() {
        Subscription subscription = new Subscription() ;
        subscription.setNumSub(1L);
        subscription.setPrice(100.0f);
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        Subscription addedSubscription = subscriptionService.addSubscription(subscription);
        assertNotNull(addedSubscription.getNumSub(), "Subscrption num should not be null");
        assertEquals( addedSubscription.getStartDate(), LocalDate.now());
        assertEquals( addedSubscription.getEndDate(), LocalDate.now().plusYears(1));
        assertEquals(100.0f, addedSubscription.getPrice(), 0.01, "Subscrption price is incorrect");
        assertEquals(TypeSubscription.ANNUAL, addedSubscription.getTypeSub(), "TypeSubscrption is incorrect");
    }


    @Test
    void updateSubscription() {

    }

    @Test
    void retrieveSubscriptionById() {
    }

    @Test
    void getSubscriptionByType() {
    }

    @Test
    void retrieveSubscriptionsByDates() {
    }

    @Test
    void retrieveSubscriptions() {
    }

    @Test
    void showMonthlyRecurringRevenue() {
    }
}