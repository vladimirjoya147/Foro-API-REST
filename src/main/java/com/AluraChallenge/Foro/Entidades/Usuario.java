package com.AluraChallenge.Foro.Entidades;


import com.AluraChallenge.Foro.DTO.Usuarios.DatosUsuario;
import com.AluraChallenge.Foro.DTO.Usuarios.DatosUsuarioActualizar;
import com.AluraChallenge.Foro.DTO.Usuarios.UsuarioRespuesta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name="usuario")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="usuarios")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="usuario_id")
    private Integer id;

    private String nombre;
    @Email
    private String email;

    private String contrasena;

    public Usuario (DatosUsuario datosUsuario){
        this.nombre=datosUsuario.nombre();
        this.email=datosUsuario.email();
        this.contrasena=datosUsuario.contrasena();

    }

    @Schema(hidden = true)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER") );
    }
    @Schema(hidden = true)
    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return nombre;
    }
    @Schema(hidden = true)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Schema(hidden = true)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Schema(hidden = true)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Schema(hidden = true)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
