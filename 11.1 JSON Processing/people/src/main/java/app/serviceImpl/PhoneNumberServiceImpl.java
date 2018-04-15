package app.serviceImpl;

import app.repository.PhoneNumberRepository;
import app.service.PhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {
    private final PhoneNumberRepository phoneNuberRepository;

    @Autowired
    public PhoneNumberServiceImpl(PhoneNumberRepository phoneNuberRepository) {
        this.phoneNuberRepository = phoneNuberRepository;
    }
}
