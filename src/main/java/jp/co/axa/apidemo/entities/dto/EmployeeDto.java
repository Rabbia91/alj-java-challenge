package jp.co.axa.apidemo.entities.dto;

import jp.co.axa.apidemo.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    private String name;

    private Integer salary;

    private String department;

}