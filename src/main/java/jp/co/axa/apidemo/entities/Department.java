package jp.co.axa.apidemo.entities;

/**
 * This class represents a Department entity that is mapped to a database table.
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "department")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Column(name = "name", unique = true)
    private String name;
    
/*    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employees
    
    public Department(Long id, String name){
    	this.id = id;
    	this.name = name;
    }*/
}

