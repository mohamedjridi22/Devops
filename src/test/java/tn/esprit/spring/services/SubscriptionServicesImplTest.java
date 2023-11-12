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
import java.util.*;

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

        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setPrice(100.0f);
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setEndDate(LocalDate.now());
        subscription.setStartDate(LocalDate.now());


        when(mocksubscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);


        Subscription result = mocksubscriptionService.updateSubscription(subscription);


        verify(mocksubscriptionRepository, times(1)).save(eq(subscription));




    }
    @Test
    void updateSubscriptionA() {
        Subscription subscription = new Subscription();
        subscription.setNumSub(1L);
        subscription.setPrice(101.0f);
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());
        subscription.setEndDate(LocalDate.now().plusYears(1));
        Subscription updatedSubscription = subscriptionService.updateSubscription(subscription);
        assertNotNull(updatedSubscription.getNumSub(), "Subscription num should not be null");
        assertEquals(updatedSubscription.getStartDate(), LocalDate.now());
        assertEquals(updatedSubscription.getEndDate(), LocalDate.now().plusYears(1));
        assertEquals(101.0f, updatedSubscription.getPrice(), 0.01, "Subscription price is incorrect");
        assertEquals(TypeSubscription.ANNUAL, updatedSubscription.getTypeSub(), "TypeSubscription is incorrect");


    }

    @Test
    void retrieveSubscriptionById() {


        Subscription mockSubscription = new Subscription();
        mockSubscription.setNumSub(1L);
        when(mocksubscriptionRepository.findById(1L)).thenReturn(Optional.of(mockSubscription));
        Subscription retrievedSubscription = mocksubscriptionService.retrieveSubscriptionById(1L);
        verify(mocksubscriptionRepository, times(1)).findById(eq(1L));



    }
   @Test
   void retrieveSubscriptionAById(){

       Subscription subscription = new Subscription();
       subscription.setNumSub(1L);

       Subscription retrievedSubscription = subscriptionService.retrieveSubscriptionById(1L);
       assertNotNull(retrievedSubscription, "Retrieved subscription should not be null");
       assertEquals(1L, retrievedSubscription.getNumSub(), "Subscription ID should match");

}
    @Test
    void getSubscriptionByType() {
        // Mock data
        Subscription annualSubscription = new Subscription();
        annualSubscription.setTypeSub(TypeSubscription.ANNUAL);
        Set<Subscription> mockSet = new HashSet<>();
        mockSet.add(annualSubscription);
        when(mocksubscriptionRepository.findByTypeSubOrderByStartDateAsc(TypeSubscription.ANNUAL))
                .thenReturn(mockSet);
        Set<Subscription> annualSubscriptions = mocksubscriptionService.getSubscriptionByType(TypeSubscription.ANNUAL);
        verify(mocksubscriptionRepository, times(1)).findByTypeSubOrderByStartDateAsc(eq(TypeSubscription.ANNUAL));
    }
    @Test
    void getSubscriptionAByType() {
        Subscription annualSubscription = new Subscription();

        annualSubscription.setTypeSub(TypeSubscription.ANNUAL);
        Set<Subscription> annualSubscriptions = subscriptionService.getSubscriptionByType(TypeSubscription.ANNUAL);
        assertNotNull(annualSubscriptions, "Annual subscriptions list should not be null");
        assertEquals(1, annualSubscriptions.size(), "There should be one annual subscription");
        assertFalse(annualSubscriptions.contains(annualSubscription), "Annual subscription is not found in the set");


    }

    @Test
    void retrieveSubscriptionsByDates() {

        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 1);
        List<Subscription> mockSubscriptions = Arrays.asList(
                new Subscription(1L, LocalDate.of(2023, 2, 1), LocalDate.of(2023, 2, 28), 50.0f, TypeSubscription.MONTHLY),
                new Subscription(2L, LocalDate.of(2023, 5, 1), LocalDate.of(2023, 10, 31), 120.0f, TypeSubscription.SEMESTRIEL)

        );


        when(mocksubscriptionRepository.getSubscriptionsByStartDateBetween(startDate, endDate)).thenReturn(mockSubscriptions);

        List<Subscription> retrievedSubscriptions = mocksubscriptionService.retrieveSubscriptionsByDates(startDate, endDate);

        verify(mocksubscriptionRepository, times(1)).getSubscriptionsByStartDateBetween(eq(startDate), eq(endDate));

    }
    @Test
    void retrieveSubscriptionsAByDates(){

        LocalDate startDate = LocalDate.of(2023, 11, 9);
        LocalDate endDate = LocalDate.of(2024, 11, 9);

        List<Subscription> retrievedSubscriptions = subscriptionService.retrieveSubscriptionsByDates(startDate, endDate);

        assertNotNull(retrievedSubscriptions, "Retrieved subscriptions list should not be null");
        assertEquals(1, retrievedSubscriptions.size(), "Number of retrieved subscriptions is incorrect");
    }
    @Test
    void retrieveSubscriptions() {
    }

    @Test
    void showMonthlyRecurringRevenue() {
    }
}