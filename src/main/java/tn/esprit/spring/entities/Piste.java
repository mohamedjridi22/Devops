package tn.esprit.spring.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@Entity
@Table(name = "piste")
public class Piste implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long numPiste;
	String namePiste;
	@Enumerated(EnumType.STRING)
	Color color;
	int length;
	int slope;

	public Piste(String namePiste) {
		this.namePiste = namePiste;
	}

	@ManyToMany(mappedBy= "pistes")
	Set<Skier> skiers;
	
}
