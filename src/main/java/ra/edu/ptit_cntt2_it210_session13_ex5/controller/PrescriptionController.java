package ra.edu.ptit_cntt2_it210_session13_ex5.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Medicine;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.Prescription;
import ra.edu.ptit_cntt2_it210_session13_ex5.model.entity.PrescriptionDetail;
import ra.edu.ptit_cntt2_it210_session13_ex5.service.MedicineService;
import ra.edu.ptit_cntt2_it210_session13_ex5.service.PatientService;
import ra.edu.ptit_cntt2_it210_session13_ex5.service.PrescriptionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService service;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicineService medicineService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.getAll());
        return "list-medicine";
    }


    @GetMapping("/create")
    public String form(Model model) {
        model.addAttribute("prescription", new Prescription());


        model.addAttribute("patients", patientService.getAll());
        model.addAttribute("medicines", medicineService.getAll());

        return "form-medicine";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("prescription") Prescription prescription,
                       BindingResult result,
                       @RequestParam(value = "medicineId", required = false) Integer medicineId,
                       @RequestParam(value = "quantity", required = false) Integer quantity,
                       Model model) {

        if (medicineId == null) {
            model.addAttribute("errorMedicine", "Vui lòng chọn một loại thuốc!");
        }
        if (quantity == null || quantity <= 0) {
            model.addAttribute("errorQuantity", "Số lượng thuốc phải lớn hơn 0!");
        }

        if (result.hasErrors() || medicineId == null || quantity == null || quantity <= 0) {
            model.addAttribute("patients", patientService.getAll());
            model.addAttribute("medicines", medicineService.getAll());
            return "form-medicine";
        }


        Medicine medicine = new Medicine();
        medicine.setId(medicineId);


        PrescriptionDetail detail = new PrescriptionDetail();
        detail.setMedicine(medicine);
        detail.setQuantity(quantity);
        detail.setPrescription(prescription);

        List<PrescriptionDetail> details = new ArrayList<>();
        details.add(detail);

        prescription.setDetails(details);

        service.save(prescription);

        return "redirect:/prescriptions";
    }

    @GetMapping("/search")
    public String search(@RequestParam("code") String code, Model model) {
        model.addAttribute("list", service.search(code));
        return "list-medicine";
    }

}
