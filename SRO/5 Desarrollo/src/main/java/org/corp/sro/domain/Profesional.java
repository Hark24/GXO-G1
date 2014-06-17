package org.corp.sro.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Profesional generated by hbm2java
 */
@Entity
@Table(name = "profesional", catalog = "sro")
public class Profesional implements java.io.Serializable {

	private Integer idProfesional;
	private String colegio;
	private String nombres;
	private String apellidos;
	private String tipo;
	private boolean eliminacion=true;
	private Set<Reserva> reservasForProfesionalIdProfesional2 = new HashSet<Reserva>(0);
	private Set<Reserva> reservasForProfesionalIdProfesional1 = new HashSet<Reserva>(0);
	private Set<Reserva> reservasForProfesionalIdProfesional = new HashSet<Reserva>(0);

	public Profesional() {
	}

	public Profesional(String colegio, String nombres, String apellidos,
			String tipo, boolean eliminacion) {
		this.colegio = colegio;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.eliminacion = eliminacion;
	}

	public Profesional(String colegio, String nombres, String apellidos,
			String tipo, boolean eliminacion,
			Set<Reserva> reservasForProfesionalIdProfesional2,
			Set<Reserva> reservasForProfesionalIdProfesional1,
			Set<Reserva> reservasForProfesionalIdProfesional) {
		this.colegio = colegio;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.tipo = tipo;
		this.eliminacion = eliminacion;
		this.reservasForProfesionalIdProfesional2 = reservasForProfesionalIdProfesional2;
		this.reservasForProfesionalIdProfesional1 = reservasForProfesionalIdProfesional1;
		this.reservasForProfesionalIdProfesional = reservasForProfesionalIdProfesional;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idProfesional", unique = true, nullable = false)
	public Integer getIdProfesional() {
		return this.idProfesional;
	}

	public void setIdProfesional(Integer idProfesional) {
		this.idProfesional = idProfesional;
	}

	@Column(name = "Colegio", unique = true, nullable = false, length = 10)
	public String getColegio() {
		return this.colegio;
	}

	public void setColegio(String colegio) {
		this.colegio = colegio;
	}

	@Column(name = "Nombres", nullable = false, length = 45)
	public String getNombres() {
		return this.nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	@Column(name = "Apellidos", nullable = false, length = 45)
	public String getApellidos() {
		return this.apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	@Column(name = "Tipo", nullable = false, length = 30)
	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "Eliminacion", nullable = false)
	public boolean isEliminacion() {
		return this.eliminacion;
	}

	public void setEliminacion(boolean eliminacion) {
		this.eliminacion = eliminacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesionalByProfesionalIdProfesional2")
	public Set<Reserva> getReservasForProfesionalIdProfesional2() {
		return this.reservasForProfesionalIdProfesional2;
	}

	public void setReservasForProfesionalIdProfesional2(
			Set<Reserva> reservasForProfesionalIdProfesional2) {
		this.reservasForProfesionalIdProfesional2 = reservasForProfesionalIdProfesional2;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesionalByProfesionalIdProfesional1")
	public Set<Reserva> getReservasForProfesionalIdProfesional1() {
		return this.reservasForProfesionalIdProfesional1;
	}

	public void setReservasForProfesionalIdProfesional1(
			Set<Reserva> reservasForProfesionalIdProfesional1) {
		this.reservasForProfesionalIdProfesional1 = reservasForProfesionalIdProfesional1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "profesionalByProfesionalIdProfesional")
	public Set<Reserva> getReservasForProfesionalIdProfesional() {
		return this.reservasForProfesionalIdProfesional;
	}

	public void setReservasForProfesionalIdProfesional(
			Set<Reserva> reservasForProfesionalIdProfesional) {
		this.reservasForProfesionalIdProfesional = reservasForProfesionalIdProfesional;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profesional that = (Profesional) o;

        if (idProfesional != null ? !idProfesional.equals(that.idProfesional) : that.idProfesional != null) return false;

        return true;
    }
}
