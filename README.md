# TP3_Torneos_ProgramacionAvanzada2017

- Los archivos de Equipos con el detalle de jugadores se levantan para el torneo actual.
- La asignacion de un id a los torneos es para evitar ambiguedades, ya que, aunque sean anuales los torneos, estos pueden empezar un año y finalizar en el siguiente.
- Al registrar un nuevo costo de inscripcion, se inicializa un nuevo torneo.
- Al generar el fixture, se asume que el torneo tiene ida y vuelta.
- Si se quiere consultar el ganador del torneo, pero aun no se han regitrado los resultados de todos los partidos, los partidos de los cuales 	no se ha registrado el resultado se tomaran como empatados 0-0.

----------------------------------------------------------------------------------------------------------------
ARCHIVOS:

EQUIPO1.txt, EQUIPO2.txt, EQUIPO3.txt, etc.

El primer renglón del archivo de equipos contiene la palabra “Equipo: ” y luego, el
nombre fantasía del mismo. Los siguientes 7 renglones indican los integrantes del
mismo (jugadores, empleados de la empresa). Cada renglón está formado por el
CUIL, apellido y nombre del empleado separado por “;”.

Ejemplo:

Equipo: SuperTeam
00000000;WAYNE;BRUCE
00000001;PARKER;PETER
00000002;KENT;CLARK
00000003;WOMAN;WONDER
00000004;MAN;SUPER
00000005;VADER;DARTH
00000006;SOLO;HAN




---------------------------------------------------------------------------------------------

TorneosEquipos.txt

cada linea va a tener idTorneo + equipos participantes de ese torneo, separados por ;

Ejemplo:

254;EQUIPO1;EQUIPO2;EQUIPO3;EQUIPO4

---------------------------------------------------------------------------------------------

Fixture.txt

El archivo, separado por TAB, contiene la fecha del partido
(dd/mm/yyyy) y el nombre de los equipos

Ejemplo:

02/11/2017	EQUIPO_LOCAL	EQUIPO_VISITANTE

---------------------------------------------------------------------------------------------

Resultados.txt

formato de ancho fijo. El archivo incluye la fecha del partido (dd/mm/yyyy),
nombre del equipo local, nombre del equipo visitante y los goles de cada uno de
ellos. Se reservan 45 posiciones para el nombre de cada uno de los equipos y 2
posiciones para los goles de cada equipo. + idTorneo

fechaPartido(10)
equipoLocal (45)
equipoVisitante (45)
golesLocal (2)
golesVisitante (2)
idTorneo (4)

Ejemplo:

02/11/2017EQUIPO_LOCAL                                 EQUIPO_VISITANTE                             03010254

---------------------------------------------------------------------------------------------

Torneos.txt

Para información estadística, se guarda en un archivo general, separado por
espacios, el año de inicio del campeonato, cantidad de equipos participantes,
puntuación máxima, puntuación mínima y cantidad total de goles. + id + mesFinTorneo + añoFinTorneo

Ejemplo:

254 2016 4 75 23 52 04 2017

---------------------------------------------------------------------------------------------

ID_TORNEOS.txt

254

---------------------------------------------------------------------------------------------

InscripcionesValorAnual.txt

año_inicio_torneo + costo_inscripcion separados por ;

Ejemplo:

2016;850

---------------------------------------------------------------------------------------------

TablaPosicionesyyyyMM.txt

La tabla de posiciones finales se guardará en otro archivo, también separado por
TAB llamado con TablaPosiciones más el año y mes en que finalizó el torneo con
dos dígitos. Este archivo tendrá: nombre del equipo, puntos obtenidos, cantidad de
partidos ganados, de partidos empatados, de partidos perdidos, de goles a favor y
en contra y diferencia de estos.

nombre  "\t"
puntos + "\t" +
cantidadPartidosGanados + "\t" +
cantidadPartidosEmpatados + "\t" +
cantidadPartidosPerdidos + "\t" +
goles + "\t" +
golesEnContra + "\t" +
diferenciaGoles		

Ejemplo:

EQUIPO2	23	7	2	1	17	7	10

