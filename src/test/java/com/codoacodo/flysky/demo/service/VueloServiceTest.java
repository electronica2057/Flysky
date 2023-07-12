import com.codoacodo.flysky.demo.DemoApplication;
import com.codoacodo.flysky.demo.dto.response.VueloDTO;
import com.codoacodo.flysky.demo.repository.UsuarioRepository;
import com.codoacodo.flysky.demo.service.VueloService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest
@Transactional
@ContextConfiguration(classes = {DemoApplication.class})
public class VueloServiceTest {
    @Autowired
    private VueloService vueloService;

    @BeforeEach
    void setUp() {
        // Realizar configuraciones adicionales si es necesario
    }

    @Test
    @DisplayName("Obtener vuelos disponibles - Éxito")
    void obtenerVuelosDisponiblesExito() {
        // Arrange
        String nombreUsuario = "Juan";

        // Act
        List<VueloDTO> vuelos = vueloService.obtenerVuelosDisponibles(nombreUsuario);

        // Assert
        assertEquals(2, vuelos.size());

        VueloDTO vuelo1 = vuelos.get(0);
        assertEquals("Aerolineas Argentinas", vuelo1.getAerolinea());
        assertEquals(5, vuelo1.getButacas().size());


        VueloDTO vuelo2 = vuelos.get(1);
        assertEquals("Aerolineas Uruguayas", vuelo2.getAerolinea());
        assertEquals(0, vuelo2.getButacas().size());
    }
}
