package ra.edu.ptit_cntt2_it210_session13_ex5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Patient;
import ra.edu.ptit_cntt2_it210_session13_ex5.repository.PatientRepository;

import java.util.List;

@Service
@Transactional
public class PatientService {
    @Autowired
    private PatientRepository repository;

    public List<Patient> getAll() {
        return repository.findAll();
    }
}
