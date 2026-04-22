package ra.edu.ptit_cntt2_it210_session13_ex5.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Prescription;
import ra.edu.ptit_cntt2_it210_session13_ex5.repository.PrescriptionRepository;

import java.util.List;

@Service
@Transactional
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository repository;

    public List<Prescription> getAll() {
        return repository.findAll();
    }

    public void save(Prescription prescription) {
        repository.save(prescription);
    }

    public List<Prescription> search(String code) {
        return repository.findByPatientCode(code);
    }
}
