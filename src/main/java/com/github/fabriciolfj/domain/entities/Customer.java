package com.github.fabriciolfj.domain.entities;

import java.time.LocalDate;

public record Customer(String document, LocalDate birtDate, String email) { }
