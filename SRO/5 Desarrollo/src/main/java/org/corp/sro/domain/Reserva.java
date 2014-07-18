package org.corp.sro.domain;

// Generated 02-ago-2013 21:51:03 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Reserva generated by hbm2java
 */
@Entity
@Table(name = "reserva", catalog = "sro")
public class Reserva implements java.io.Serializable {

	private Integer idReserva;
	private Reserva reserva;
	private Sala sala;
	private Profesional profesionalByProfesionalIdProfesional1;
	private Profesional profesionalByProfesionalIdProfesional2;
	private Profesional profesionalByProfesionalIdProfesional;
	private Procedimiento procedimiento;
	private Paciente paciente;
	private Date fechAviso;
	private Date fechaIni;
	private Date fechaFin;
	private Date duracionReal;
	private Date duracionEst;
	private Boolean aprobado;
	private Boolean cancelado;
	private Boolean realizado;
	private String observacion;
	private String compania;
	private String ubicacion;
	private boolean vigente=true;
	private boolean eliminacion=true;
	private Set<Reserva> reservas = new HashSet<Reserva>(0);

	public Reserva() {
	}

	public Reserva(Sala sala,
			Profesional profesionalByProfesionalIdProfesional1,
			Profesional profesionalByProfesionalIdProfesional2,
			Profesional profesionalByProfesionalIdProfesional,
			Procedimiento procedimiento, Paciente paciente, Date fechAviso,
			Date fechaIni, Date fechaFin, Date duracionEst, boolean vigente,
			boolean eliminacion) {
		this.sala = sala;
		this.profesionalByProfesionalIdProfesional1 = profesionalByProfesionalIdProfesional1;
		this.profesionalByProfesionalIdProfesional2 = profesionalByProfesionalIdProfesional2;
		this.profesionalByProfesionalIdProfesional = profesionalByProfesionalIdProfesional;
		this.procedimiento = procedimiento;
		this.paciente = paciente;
		this.fechAviso = fechAviso;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.duracionEst = duracionEst;
		this.vigente = vigente;
		this.eliminacion = eliminacion;
	}

	public Reserva(Reserva reserva, Sala sala,
			Profesional profesionalByProfesionalIdProfesional1,
			Profesional profesionalByProfesionalIdProfesional2,
			Profesional profesionalByProfesionalIdProfesional,
			Procedimiento procedimiento, Paciente paciente, Date fechAviso,
			Date fechaIni, Date fechaFin, Date duracionReal, Date duracionEst,
			Boolean aprobado, Boolean cancelado, Boolean realizado, String observacion,
			String compania, String ubicacion, boolean vigente,
			boolean eliminacion, Set<Reserva> reservas) {
		this.reserva = reserva;
		this.sala = sala;
		this.profesionalByProfesionalIdProfesional1 = profesionalByProfesionalIdProfesional1;
		this.profesionalByProfesionalIdProfesional2 = profesionalByProfesionalIdProfesional2;
		this.profesionalByProfesionalIdProfesional = profesionalByProfesionalIdProfesional;
		this.procedimiento = procedimiento;
		this.paciente = paciente;
		this.fechAviso = fechAviso;
		this.fechaIni = fechaIni;
		this.fechaFin = fechaFin;
		this.duracionReal = duracionReal;
		this.duracionEst = duracionEst;
		this.aprobado = aprobado;
		this.cancelado = cancelado;
		this.realizado = realizado;
		this.observacion = observacion;
		this.compania = compania;
		this.ubicacion = ubicacion;
		this.vigente = vigente;
		this.eliminacion = eliminacion;
		this.reservas = reservas;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idReserva", unique = true, nullable = false)
	public Integer getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reserva_idReserva")
	public Reserva getReserva() {
		return this.reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Sala_idSala", nullable = false)
	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional_idProfesional1", nullable = false)
	public Profesional getProfesionalByProfesionalIdProfesional1() {
		return this.profesionalByProfesionalIdProfesional1;
	}

	public void setProfesionalByProfesionalIdProfesional1(
			Profesional profesionalByProfesionalIdProfesional1) {
		this.profesionalByProfesionalIdProfesional1 = profesionalByProfesionalIdProfesional1;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional_idProfesional2", nullable = false)
	public Profesional getProfesionalByProfesionalIdProfesional2() {
		return this.profesionalByProfesionalIdProfesional2;
	}

	public void setProfesionalByProfesionalIdProfesional2(
			Profesional profesionalByProfesionalIdProfesional2) {
		this.profesionalByProfesionalIdProfesional2 = profesionalByProfesionalIdProfesional2;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional_idProfesional", nullable = false)
	public Profesional getProfesionalByProfesionalIdProfesional() {
		return this.profesionalByProfesionalIdProfesional;
	}

	public void setProfesionalByProfesionalIdProfesional(
			Profesional profesionalByProfesionalIdProfesional) {
		this.profesionalByProfesionalIdProfesional = profesionalByProfesionalIdProfesional;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "procedimiento_idProcedimiento", nullable = false)
	public Procedimiento getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(Procedimiento procedimiento) {
		this.procedimiento = procedimiento;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "paciente_idPaciente", nullable = false)
	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FechAviso", nullable = false, length = 10)
	public Date getFechAviso() {
		return this.fechAviso;
	}

	public void setFechAviso(Date fechAviso) {
		this.fechAviso = fechAviso;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaIni", nullable = false, length = 19)
	public Date getFechaIni() {
		return this.fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FechaFin", nullable = false, length = 19)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DuracionReal", length = 19)
	public Date getDuracionReal() {
		return this.duracionReal;
	}

	public void setDuracionReal(Date duracionReal) {
		this.duracionReal = duracionReal;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DuracionEst", nullable = false, length = 19)
	public Date getDuracionEst() {
		return this.duracionEst;
	}

	public void setDuracionEst(Date duracionEst) {
		this.duracionEst = duracionEst;
	}

	@Column(name = "Aprobado")
	public Boolean getAprobado() {
		return this.aprobado;
	}

	public void setAprobado(Boolean aprobado) {
		this.aprobado = aprobado;
	}

	@Column(name = "Cancelado")
	public Boolean getCancelado() {
		return this.cancelado;
	}

	public void setCancelado(Boolean cancelado) {
		this.cancelado = cancelado;
	}

	@Column(name = "Realizado")
	public Boolean getRealizado() {
		return this.realizado;
	}

	public void setRealizado(Boolean realizado) {
		this.realizado = realizado;
	}
	
	@Column(name = "Observacion", length = 45)
	public String getObservacion() {
		return this.observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	@Column(name = "Compania", length = 45)
	public String getCompania() {
		return this.compania;
	}

	public void setCompania(String compania) {
		this.compania = compania;
	}

	@Column(name = "Ubicacion", length = 45)
	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Column(name = "Vigente", nullable = false)
	public boolean isVigente() {
		return this.vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}

	@Column(name = "Eliminacion", nullable = false)
	public boolean isEliminacion() {
		return this.eliminacion;
	}

	public void setEliminacion(boolean eliminacion) {
		this.eliminacion = eliminacion;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reserva")
	public Set<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	@Override
    public String toString() {
        return "Reserva{" +
                "idReserva=" + idReserva +
                ", reserva=" + reserva +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reserva reserva = (Reserva) o;

        if (idReserva != null ? !idReserva.equals(reserva.idReserva) : reserva.idReserva != null) return false;

        return true;
    }
}
