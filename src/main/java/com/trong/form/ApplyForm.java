package com.trong.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplyForm {
    private Long recruitmentId;
    private MultipartFile cv;
}
