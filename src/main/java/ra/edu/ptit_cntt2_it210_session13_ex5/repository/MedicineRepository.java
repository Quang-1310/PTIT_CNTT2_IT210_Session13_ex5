package ra.edu.ptit_cntt2_it210_session13_ex5.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Medicine;

import java.util.List;

@Repository
@Transactional
public class MedicineRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Medicine> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Medicine", Medicine.class)
                .list();
    }
}
