package com.lyoyang.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @Auther: yangbing
 * @Date: 2019/5/20 11:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingHourDetailReqDto implements Serializable {
    private String workingDate;
    private Integer projectId;
    private String workingHour;
    private String remark;
}
