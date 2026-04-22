package ra.edu.ptit_cntt2_it210_session13_ex5.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Medicine;
import ra.edu.ptit_cntt2_it210_session13_ex5.repository.MedicineRepository;

import java.util.List;

@Service
@Transactional
public class MedicineService {
    @Autowired
    private MedicineRepository repository;

    public List<Medicine> getAll() {
        return repository.findAll();
    }
}
