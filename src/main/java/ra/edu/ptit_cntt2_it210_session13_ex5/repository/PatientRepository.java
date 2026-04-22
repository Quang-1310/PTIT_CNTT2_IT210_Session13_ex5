package ra.edu.ptit_cntt2_it210_session13_ex5.repository;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Patient;

import java.util.List;

@Repository
@Transactional
public class PatientRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Patient> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Patient", Patient.class)
                .list();
    }
}
