package com.AluraChallenge.Foro.Controlador;

import com.AluraChallenge.Foro.DTO.Usuarios.*;
import com.AluraChallenge.Foro.Entidades.Usuario;
import com.AluraChallenge.Foro.Security.TokenService;
import com.AluraChallenge.Foro.Servicio.UsuarioServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URI;
@Tag(name="1. Usuarios")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Operation(
            summary = "Registrarse en la aplicacion",
            description = "registrar datosUsuario",
            security = @SecurityRequirement(name = "noAuth"),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authentication successful",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = DatosUsuarioActualizar.class))
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Bad credentials"
                    )
            }
    )

    @SecurityRequirements()
    @PostMapping
    public ResponseEntity<String> guardarUsuario(@RequestBody DatosUsuario datosUsuario, UriComponentsBuilder uriComponentsBuilder) {
        String contrasenaCodificada = passwordEncoder.encode(datosUsuario.contrasena());
        datosUsuario = new DatosUsuario(datosUsuario.nombre(), datosUsuario.email(), contrasenaCodificada);
        Usuario usuario = new Usuario(datosUsuario);
        Usuario guardarUsuario = usuarioServicio.guardarUsuario(usuario);
        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(guardarUsuario.getId()).toUri();
        return ResponseEntity.created(url).body("Usuario registrado exitosamente");
    }


    @Operation(
            summary = "Buscar usuario por ID",
            description = "debes estar logueado para buscar usuarios")
    @GetMapping("/{id}")
    public  ResponseEntity<UsuarioRespuesta> buscarUsuario(@PathVariable Integer id){
        Usuario usuario = usuarioServicio.obtenerUsuarioPorId(id);
        UsuarioRespuesta userRespuesta = new UsuarioRespuesta(usuario.getNombre(),usuario.getEmail());
        return ResponseEntity.ok(userRespuesta);
    }

    @Operation(
            summary = "Login en la aplicacion",
            description = "login para obtener JWT")
    @PostMapping ("/login")
    public ResponseEntity autenticarUsuario (@RequestBody @Valid Datoslogin datoslogin){
        Authentication authToken = new UsernamePasswordAuthenticationToken(datoslogin.nombre(),datoslogin.contrasena());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJWTtoken(JWTtoken));
    }

    @Operation(
            summary = "Actualizar datos de usuario",
            description = "modificar datos usuario")
    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> actualizarUsuario (@PathVariable Integer id,
                                                     @RequestBody DatosUsuario datosUsuario){
        Usuario usuarioexistente = usuarioServicio.obtenerUsuarioPorId(id);
        usuarioexistente.setId(id);
        usuarioexistente.setNombre(datosUsuario.nombre());
        usuarioexistente.setEmail(datosUsuario.email());
        if (datosUsuario.contrasena() != null && !datosUsuario.contrasena().isEmpty()) {
            String contrasenaCodificada = passwordEncoder.encode(datosUsuario.contrasena());
            usuarioexistente.setContrasena(contrasenaCodificada);
        }
        usuarioServicio.actualizarUsuario(usuarioexistente);
        return ResponseEntity.ok("Se ha actualizado el usuario");
    }



}
