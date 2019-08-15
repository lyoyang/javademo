package com.lyoyang.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yangbing
 * @Date: 2019/5/20 11:42
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkingHourReqDto implements Serializable {
    private Long id;

    private String userCd;

    private Integer groupId;

    private String creator;

    private List<WorkingHourDetailReqDto> detailList;

}
