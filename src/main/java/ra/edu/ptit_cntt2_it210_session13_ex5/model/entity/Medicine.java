package ra.edu.ptit_cntt2_it210_session13_ex5.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "medicines")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;


    @OneToMany(mappedBy = "medicine")
    private List<PrescriptionDetail> details;

}
