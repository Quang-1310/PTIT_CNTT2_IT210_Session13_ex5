package ra.edu.ptit_cntt2_it210_session13_ex5.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Prescription;

import java.util.List;

@Repository
@Transactional
public class PrescriptionRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Prescription> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Prescription", Prescription.class)
                .list();
    }

    public void save(Prescription prescription) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.persist(prescription);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Prescription> findByPatientCode(String code) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Prescription p where p.patient.code = :code", Prescription.class)
                .setParameter("code", code)
                .list();
    }
}
