package com.esempio.RentalCar.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.esempio.RentalCar.validator.passwordStrong;

@Entity
@Table(name = "utente")
public class Utente implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
private Long id;
@Column(name = "nome")
@NotEmpty(message = "{NotEmpty.Utente.nome.validator}")
private String nome;
@Column(name = "cognome")
@NotEmpty(message = "{NotEmpty.Utente.cognome.validator}")
private String cognome;

@Temporal(TemporalType.DATE)
@DateTimeFormat(pattern = "yyyy-MM-dd")
@Column(name = "dataNascita")
@NotNull(message = "{NotEmpty.Utente.dataNascita.validator}")
private Date dataNascita;

@Column(name = "username")
@NotEmpty(message = "{NotEmpty.Utente.username.validator}")
private String username;
@Column(name = "password")
@NotEmpty(message = "{NotEmpty.Utente.password.validator}")
//@passwordStrong
private String password;
@Enumerated(EnumType.ORDINAL)
@Column(name = "ruolo")
private Ruolo ruolo;

@OneToMany(mappedBy = "utente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
private Set<Prenotazione> prenotazioni = new HashSet<>();

    public Utente() {
    }

    public Utente(String nome, String cognome, Date dataNascita, String username, String password, Ruolo ruolo, Set<Prenotazione> prenotazioni) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
        this.prenotazioni = prenotazioni;
    }

    public Utente(Long id, String nome, String cognome, Date dataNascita, String username, String password, Ruolo ruolo, Set<Prenotazione> prenotazioni) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
        this.prenotazioni = prenotazioni;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public Date getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    public Set<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(Set<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", ruolo=" + ruolo +
                ", prenotazioni=" + prenotazioni +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return Objects.equals(getId(), utente.getId()) && Objects.equals(getNome(), utente.getNome()) && Objects.equals(getCognome(), utente.getCognome()) && Objects.equals(getDataNascita(), utente.getDataNascita()) && Objects.equals(getUsername(), utente.getUsername()) && Objects.equals(getPassword(), utente.getPassword()) && getRuolo() == utente.getRuolo() && Objects.equals(getPrenotazioni(), utente.getPrenotazioni());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getCognome(), getDataNascita(), getUsername(), getPassword(), getRuolo(), getPrenotazioni());
    }
}
