-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Czas generowania: 01 Kwi 2019, 18:03
-- Wersja serwera: 10.1.37-MariaDB-0+deb9u1
-- Wersja PHP: 7.0.30-0+deb9u1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `pomidory`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pracownicy`
--

CREATE TABLE `pracownicy` (
  `id_pracownika` int(11) NOT NULL,
  `imie` varchar(255) NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `stanowisko` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `haslo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `przypisane_zad`
--

CREATE TABLE `przypisane_zad` (
  `id_zad` int(11) NOT NULL,
  `id_pracownika` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `zadania`
--

CREATE TABLE `zadania` (
  `id_zadania` int(11) NOT NULL,
  `tytul` varchar(255) NOT NULL,
  `tresc` varchar(255) NOT NULL,
  `data_rozp` varchar(255) NOT NULL,
  `data_ukon` varchar(255) NOT NULL,
  `aktywne` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indexes for table `pracownicy`
--
ALTER TABLE `pracownicy`
  ADD PRIMARY KEY (`id_pracownika`);

--
-- Indexes for table `przypisane_zad`
--
ALTER TABLE `przypisane_zad`
  ADD KEY `id_zad` (`id_zad`),
  ADD KEY `id_pracownika` (`id_pracownika`);

--
-- Indexes for table `zadania`
--
ALTER TABLE `zadania`
  ADD PRIMARY KEY (`id_zadania`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT dla tabeli `pracownicy`
--
ALTER TABLE `pracownicy`
  MODIFY `id_pracownika` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT dla tabeli `zadania`
--
ALTER TABLE `zadania`
  MODIFY `id_zadania` int(11) NOT NULL AUTO_INCREMENT;
--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `przypisane_zad`
--
ALTER TABLE `przypisane_zad`
  ADD CONSTRAINT `przypisane_zad_ibfk_1` FOREIGN KEY (`id_zad`) REFERENCES `pracownicy` (`id_pracownika`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `przypisane_zad_ibfk_2` FOREIGN KEY (`id_pracownika`) REFERENCES `zadania` (`id_zadania`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
