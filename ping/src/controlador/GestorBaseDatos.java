package controlador;

import modelo.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;


public class GestorBaseDatos {

    private static final String DB_URL    = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DB_USER   = "julenputoamo";
    private static final String DB_PASS   = "tumadre33";
    private static final byte[] CLAVE_AES = "PinguinoJuego128".getBytes();

    public GestorBaseDatos() {
        inicializarBD();
    }


    private void inicializarBD() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement()) {

            crearSiNoExiste(stmt,
                "CREATE TABLE partida (" +
                "  id           NUMBER PRIMARY KEY," +
                "  turno_actual VARCHAR2(500)," +
                "  estado       VARCHAR2(500))"
            );
            crearSiNoExiste(stmt,
                "CREATE SEQUENCE seq_partida START WITH 1 INCREMENT BY 1 NOCACHE"
            );
            crearSiNoExiste(stmt,
                "CREATE TABLE jugadores (" +
                "  id          NUMBER PRIMARY KEY," +
                "  partida_id  NUMBER," +
                "  nombre      VARCHAR2(100)," +
                "  color       VARCHAR2(50)," +
                "  posicion    VARCHAR2(500)," +
                "  dados       VARCHAR2(500)," +
                "  peces       VARCHAR2(500)," +
                "  bolas_nieve VARCHAR2(500))"
            );
            crearSiNoExiste(stmt,
                "CREATE SEQUENCE seq_jugadores START WITH 1 INCREMENT BY 1 NOCACHE"
            );

            System.out.println("Base de datos Oracle inicializada correctamente.");

        } catch (SQLException e) {
            System.err.println("Error inicializando base de datos: " + e.getMessage());
        }
    }

    private void crearSiNoExiste(Statement stmt, String sql) {
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            if (!e.getMessage().contains("ORA-00955")) {
                System.err.println("Aviso BD: " + e.getMessage());
            }
        }
    }


    public void guardarPartida(Partida partida) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
            conn.setAutoCommit(false);

            try (Statement stmt = conn.createStatement()) {
                stmt.execute("DELETE FROM jugadores");
                stmt.execute("DELETE FROM partida");
            }

            int partidaId;
            String sqlP = "INSERT INTO partida (id, turno_actual, estado) VALUES (seq_partida.NEXTVAL, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlP, new String[]{"id"})) {
                ps.setString(1, encriptar(String.valueOf(partida.getTurnoActual())));
                ps.setString(2, encriptar("ACTIVA"));
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                partidaId = rs.next() ? rs.getInt(1) : 1;
            }

            String sqlJ =
                "INSERT INTO jugadores (id, partida_id, nombre, color, posicion, dados, peces, bolas_nieve) " +
                "VALUES (seq_jugadores.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlJ)) {
                for (Jugador j : partida.getJugadores()) {
                    Inventario inv = j.getInventario();
                    ps.setInt(1, partidaId);
                    ps.setString(2, j.getNombre());
                    ps.setString(3, j.getColor());
                    ps.setString(4, encriptar(String.valueOf(j.getPosicion())));
                    ps.setString(5, encriptar(String.valueOf(inv.getDados())));
                    ps.setString(6, encriptar(String.valueOf(inv.getPeces())));
                    ps.setString(7, encriptar(String.valueOf(inv.getBolasDenieve())));
                    ps.addBatch();
                }
                ps.executeBatch();
            }

            conn.commit();
            System.out.println("Partida guardada correctamente en Oracle.");

        } catch (SQLException e) {
            System.err.println("Error al guardar la partida: " + e.getMessage());
        }
    }


    public Partida cargarPartida() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {

            int turnoActual = 0;
            String sqlP = "SELECT * FROM partida ORDER BY id DESC FETCH FIRST 1 ROWS ONLY";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sqlP)) {
                if (rs.next()) {
                    turnoActual = Integer.parseInt(desencriptar(rs.getString("turno_actual")));
                } else {
                    System.out.println("No hay ninguna partida guardada.");
                    return null;
                }
            }

            ArrayList<Jugador> jugadores = new ArrayList<>();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM jugadores")) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String color  = rs.getString("color");
                    int posicion  = Integer.parseInt(desencriptar(rs.getString("posicion")));
                    int numDados  = Integer.parseInt(desencriptar(rs.getString("dados")));
                    int numPeces  = Integer.parseInt(desencriptar(rs.getString("peces")));
                    int numBolas  = Integer.parseInt(desencriptar(rs.getString("bolas_nieve")));

                    Jugador j = new Pinguino(nombre, color);
                    j.setPosicion(posicion);
                    Inventario inv = j.getInventario();

                    for (int i = 1; i < numDados && i < Inventario.MAX_DADOS; i++)
                        inv.agregarDado(new Dado(TipoDado.NORMAL));
                    for (int i = 0; i < numPeces; i++) inv.agregarPez();
                    for (int i = 0; i < numBolas; i++) inv.agregarBolaNieve();

                    jugadores.add(j);
                }
            }

            if (jugadores.isEmpty()) {
                System.out.println("No se encontraron jugadores en la partida guardada.");
                return null;
            }

            Tablero tablero = new Tablero();
            Partida partida = new Partida(tablero, jugadores);
            for (int i = 0; i < turnoActual; i++) partida.siguienteTurno();

            System.out.println("Partida cargada correctamente desde Oracle.");
            return partida;

        } catch (SQLException e) {
            System.err.println("Error al cargar la partida: " + e.getMessage());
            return null;
        }
    }


    private String encriptar(String texto) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_AES, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] encriptado = cipher.doFinal(texto.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encriptado);
        } catch (Exception e) {
            System.err.println("Error encriptando: " + e.getMessage());
            return texto;
        }
    }

    private String desencriptar(String textoEncriptado) {
        try {
            SecretKeySpec clave = new SecretKeySpec(CLAVE_AES, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] desencriptado = Base64.getDecoder().decode(textoEncriptado);
            return new String(cipher.doFinal(desencriptado), "UTF-8");
        } catch (Exception e) {
            System.err.println("Error desencriptando: " + e.getMessage());
            return textoEncriptado;
        }
    }
}
