-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-01-2025 a las 20:59:41
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parque_salitre_magico`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `atracciones`
--

CREATE TABLE `atracciones` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text NOT NULL,
  `clasificacion_id` int(11) NOT NULL,
  `estatura_minima` decimal(5,2) DEFAULT NULL,
  `condiciones_uso` text DEFAULT NULL,
  `estado` enum('Disponible','No Disponible','Mantenimiento') NOT NULL,
  `empleado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `atracciones`
--

INSERT INTO `atracciones` (`id`, `nombre`, `descripcion`, `clasificacion_id`, `estatura_minima`, `condiciones_uso`, `estado`, `empleado_id`) VALUES
(1, 'Rueda Capital 360', 'Impresionante vista capitalina a 40 metros de altura, panorámica de 360 grados que no te puedes perder.', 3, 1.10, 'N/A', 'Disponible', 15),
(2, 'Apocalipsis', '¡Prepárate para dar vueltas de 360 grados con máxima velocidad y gritos de emoción!', 1, 1.40, 'N/T', 'No Disponible', 25);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clasificaciones_atracciones`
--

CREATE TABLE `clasificaciones_atracciones` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clasificaciones_atracciones`
--

INSERT INTO `clasificaciones_atracciones` (`id`, `nombre`) VALUES
(1, 'Alto Impacto'),
(2, 'Infantiles'),
(3, 'Familiares');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `N_Identificacion` varchar(20) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `estatura` decimal(5,2) NOT NULL,
  `edad` int(11) NOT NULL,
  `nombre_familiar` varchar(100) DEFAULT NULL,
  `telefono_familiar` varchar(15) DEFAULT NULL,
  `visitas` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `nombre`, `N_Identificacion`, `telefono`, `correo`, `estatura`, `edad`, `nombre_familiar`, `telefono_familiar`, `visitas`) VALUES
(1, 'Samuel Lozano', '1056165365', '31065648561', 'SamuelLozano@gmail.com', 1.60, 18, '', '', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `cedula` varchar(20) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `correo` varchar(100) NOT NULL,
  `rol_id` int(11) NOT NULL,
  `horario_laboral` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`id`, `nombre`, `cedula`, `telefono`, `correo`, `rol_id`, `horario_laboral`) VALUES
(1, 'Maria Gonzalez', '1012345678', '3001234567', 'maria.gonzalez@salitremagico.com.co', 1, '08:00 - 17:00'),
(2, 'Luis Fernandez', '1023456789', '3102345678', 'luis.fernandez@salitremagico.com.co', 1, '09:00 - 18:00'),
(3, 'Andrea Martinez', '1034567890', '3203456789', 'andrea.martinez@salitremagico.com.co', 1, '10:00 - 19:00'),
(4, 'Carlos Perez', '2012345678', '3111234567', 'carlos.perez@salitremagico.com.co', 2, '10:00 - 19:00'),
(5, 'Diana Lopez', '2023456789', '3012345678', 'diana.lopez@salitremagico.com.co', 2, '09:00 - 18:00'),
(6, 'Sergio Ramirez', '2034567890', '3023456789', 'sergio.ramirez@salitremagico.com.co', 2, '11:00 - 20:00'),
(7, 'Natalia Vargas', '2045678901', '3123456789', 'natalia.vargas@salitremagico.com.co', 2, '10:00 - 19:00'),
(8, 'Javier Gomez', '2056789012', '3034567890', 'javier.gomez@salitremagico.com.co', 2, '09:00 - 18:00'),
(9, 'Karen Torres', '3012345678', '3131234567', 'karen.torres@salitremagico.com.co', 3, '09:00 - 18:00'),
(10, 'Pablo Castro', '3023456789', '3142345678', 'pablo.castro@salitremagico.com.co', 3, '10:00 - 19:00'),
(11, 'Sofia Rojas', '3034567890', '3153456789', 'sofia.rojas@salitremagico.com.co', 3, '11:00 - 20:00'),
(12, 'Andres Morales', '3045678901', '3164567890', 'andres.morales@salitremagico.com.co', 3, '09:00 - 18:00'),
(14, 'Paola Castillo', '3056789012', '3175678901', 'paola.castillo@salitremagico.com.co', 3, '08:00 - 17:00'),
(15, 'Diego Herrera', '4012345678', '3181234567', 'diego.herrera@salitremagico.com.co', 4, '11:00 - 20:00'),
(16, 'Laura Espinosa', '4023456789', '3192345678', 'laura.espinosa@salitremagico.com.co', 4, '12:00 - 21:00'),
(17, 'Juan Camilo Díaz', '4034567890', '3203456789', 'juan.diaz@salitremagico.com.co', 4, '10:00 - 19:00'),
(18, 'Valentina Rincón', '4045678901', '3014567890', 'valentina.rincon@salitremagico.com.co', 4, '13:00 - 21:00'),
(20, 'Sebastián López', '4056789012', '3025678901', 'sebastian.lopez@salitremagico.com.co', 4, '11:00 - 20:00'),
(21, 'Daniela Cruz', '4067890123', '3036789012', 'daniela.cruz@salitremagico.com.co', 4, '09:00 - 18:00'),
(22, 'Andrés Beltran', '4078901234', '3047890123', 'andres.beltran@salitremagico.com.co', 4, '12:00 - 21:00'),
(23, 'Diana Vargas', '4089012345', '3058901234', 'diana.vargas@salitremagico.com.co', 4, '10:00 - 19:00'),
(24, 'Camilo Ramirez', '4090123456', '3069012345', 'camilo.ramirez@salitremagico.com.co', 4, '13:00 - 21:00'),
(25, 'Natalia Perez', '4101234567', '3070123456', 'natalia.perez@salitremagico.com.co', 4, '11:00 - 20:00'),
(26, 'Juan Ortega', '5012345678', '3201234567', 'juan.ortega@salitremagico.com.co', 5, '08:00 - 17:00'),
(27, 'Andrea Salazar', '5023456789', '3212345678', 'andrea.salazar@salitremagico.com.co', 5, '07:00 - 16:00'),
(28, 'Felipe Torres', '5034567890', '3223456789', 'felipe.torres@salitremagico.com.co', 5, '06:00 - 15:00'),
(29, 'Carolina Vega', '5045678901', '3234567890', 'carolina.vega@salitremagico.com.co', 5, '08:00 - 17:00'),
(30, 'Nicolas Castro', '5056789012', '3245678901', 'santiago.castro@salitremagico.com.co', 5, '07:00 - 16:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estaciones`
--

CREATE TABLE `estaciones` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `habilitada` tinyint(1) DEFAULT 1,
  `empleado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `estaciones`
--

INSERT INTO `estaciones` (`id`, `nombre`, `habilitada`, `empleado_id`) VALUES
(6, 'Estación Norte', 1, 4),
(7, 'Estación Sur', 1, 5),
(8, 'Estación Este', 1, 6),
(9, 'Estación Oeste', 1, 7),
(10, 'Estación Central', 1, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_empleados`
--

CREATE TABLE `roles_empleados` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles_empleados`
--

INSERT INTO `roles_empleados` (`id`, `nombre`) VALUES
(1, 'Administrativos'),
(2, 'Logística'),
(3, 'Publicidad'),
(4, 'Operadores'),
(5, 'Mantenimiento');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_tiquete`
--

CREATE TABLE `tipo_tiquete` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo_tiquete`
--

INSERT INTO `tipo_tiquete` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Kids', 'Pasaporte diseñado para los más chiquitos de nuestras familias, incluye una amplia variedad de atracciones infantiles y familiares.'),
(2, 'Nitro', 'Pasaporte que incluye todas las atracciones del parque excepto Castillo del Terror, Karts, Chocones, Juegos de Destreza, Polo Norte y Decoración de Galletas.'),
(3, 'Nitro Plus', 'Pasaporte para que Vivas la máxima experiencia de Salitre Mágico con todas las atracciones del parque incluyendo el ingreso por 1 vez a Castillo del Terror, Carros Chocones y Pista de Karts');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiquetes`
--

CREATE TABLE `tiquetes` (
  `id` int(11) NOT NULL,
  `cliente_id` int(11) NOT NULL,
  `estacion_id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `tipo_tiquete_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiquetes`
--

INSERT INTO `tiquetes` (`id`, `cliente_id`, `estacion_id`, `fecha`, `tipo_tiquete_id`) VALUES
(4, 1, 6, '2025-01-03', 2),
(6, 1, 6, '2025-01-04', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tiquetes_atracciones`
--

CREATE TABLE `tiquetes_atracciones` (
  `id` int(11) NOT NULL,
  `tiquete_id` int(11) NOT NULL,
  `atraccion_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tiquetes_atracciones`
--

INSERT INTO `tiquetes_atracciones` (`id`, `tiquete_id`, `atraccion_id`) VALUES
(1, 4, 1),
(2, 6, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `atracciones`
--
ALTER TABLE `atracciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `clasificacion_id` (`clasificacion_id`),
  ADD KEY `empleado_id` (`empleado_id`);

--
-- Indices de la tabla `clasificaciones_atracciones`
--
ALTER TABLE `clasificaciones_atracciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`N_Identificacion`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`),
  ADD KEY `rol_id` (`rol_id`);

--
-- Indices de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `empleado_id` (`empleado_id`);

--
-- Indices de la tabla `roles_empleados`
--
ALTER TABLE `roles_empleados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipo_tiquete`
--
ALTER TABLE `tipo_tiquete`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tiquetes`
--
ALTER TABLE `tiquetes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_tiquete_fecha` (`cliente_id`,`fecha`),
  ADD KEY `estacion_id` (`estacion_id`),
  ADD KEY `fk_tipo_tiquete` (`tipo_tiquete_id`);

--
-- Indices de la tabla `tiquetes_atracciones`
--
ALTER TABLE `tiquetes_atracciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `tiquete_id` (`tiquete_id`),
  ADD KEY `atraccion_id` (`atraccion_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `atracciones`
--
ALTER TABLE `atracciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `clasificaciones_atracciones`
--
ALTER TABLE `clasificaciones_atracciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `empleados`
--
ALTER TABLE `empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `estaciones`
--
ALTER TABLE `estaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `roles_empleados`
--
ALTER TABLE `roles_empleados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `tipo_tiquete`
--
ALTER TABLE `tipo_tiquete`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tiquetes`
--
ALTER TABLE `tiquetes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `tiquetes_atracciones`
--
ALTER TABLE `tiquetes_atracciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `atracciones`
--
ALTER TABLE `atracciones`
  ADD CONSTRAINT `atracciones_ibfk_1` FOREIGN KEY (`clasificacion_id`) REFERENCES `clasificaciones_atracciones` (`id`),
  ADD CONSTRAINT `atracciones_ibfk_2` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`id`);

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`rol_id`) REFERENCES `roles_empleados` (`id`);

--
-- Filtros para la tabla `estaciones`
--
ALTER TABLE `estaciones`
  ADD CONSTRAINT `estaciones_ibfk_1` FOREIGN KEY (`empleado_id`) REFERENCES `empleados` (`id`);

--
-- Filtros para la tabla `tiquetes`
--
ALTER TABLE `tiquetes`
  ADD CONSTRAINT `fk_tipo_tiquete` FOREIGN KEY (`tipo_tiquete_id`) REFERENCES `tipo_tiquete` (`id`),
  ADD CONSTRAINT `tiquetes_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`),
  ADD CONSTRAINT `tiquetes_ibfk_2` FOREIGN KEY (`estacion_id`) REFERENCES `estaciones` (`id`);

--
-- Filtros para la tabla `tiquetes_atracciones`
--
ALTER TABLE `tiquetes_atracciones`
  ADD CONSTRAINT `tiquetes_atracciones_ibfk_1` FOREIGN KEY (`tiquete_id`) REFERENCES `tiquetes` (`id`),
  ADD CONSTRAINT `tiquetes_atracciones_ibfk_2` FOREIGN KEY (`atraccion_id`) REFERENCES `atracciones` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
