package ro.unibuc.helpdesk.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.unibuc.helpdesk.exception.ValidationException;
import ro.unibuc.helpdesk.repository.CustomerRepository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository repository;

    @Mock
    private AuditService audit;

    @InjectMocks
    private CustomerService service;

    @Test
    void addCustomerShouldThrowExceptionWhenNameIsBlank() {
        assertThrows(ValidationException.class, () ->
                service.addCustomer("", "ana@test.com")
        );

        verifyNoInteractions(repository);
        verifyNoInteractions(audit);
    }

    @Test
    void addCustomerShouldThrowExceptionWhenEmailIsBlank() {
        assertThrows(ValidationException.class, () ->
                service.addCustomer("Ana", "")
        );

        verifyNoInteractions(repository);
        verifyNoInteractions(audit);
    }

    @Test
    void addCustomerShouldThrowExceptionWhenEmailIsInvalid() {
        assertThrows(ValidationException.class, () ->
                service.addCustomer("Ana", "invalid-email")
        );

        verifyNoInteractions(repository);
        verifyNoInteractions(audit);
    }

    @Test
    void addCustomerShouldNotThrowExceptionForValidData() {
        assertDoesNotThrow(() ->
                service.addCustomer("Ana", "ana@test.com")
        );

        verify(repository).create(any());
        verify(audit).log("ADD_CUSTOMER");
    }
}