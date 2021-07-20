package com.petStore.config;

import com.petStore.model.Pet;
import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;
import io.cucumber.datatable.DataTableType;

import java.util.*;
import java.util.stream.Collectors;

public class TypeRegistryConfiguration implements TypeRegistryConfigurer {


    @Override
    public Locale locale() {
        return Locale.ENGLISH;
    }

    @Override
    public void configureTypeRegistry(TypeRegistry typeRegistry) {
        typeRegistry.defineParameterType(
                new ParameterType<>(
                        "list", ".+", List.class, (String s) -> Arrays.asList(s.split(","))));
        typeRegistry.defineParameterType(
                new ParameterType<>("map", ".+", Map.class, this::convertTableEntryToMap));
        typeRegistry.defineDataTableType(
                new DataTableType(
                        Pet.class,
                        this::convertTableEntryToPet
                )
        );
    }

    private Pet convertTableEntryToPet(Map<String, String> entry) {
        return Pet.builder()
                .id(Integer.valueOf(entry.get("id")))
                .name(entry.get("name"))
                .photoUrls(Arrays.asList(entry.get("photoUrls").split(",")))
                .status(entry.get("status"))
                .build();
    }

    public Map<String, String> convertTableEntryToMap(String entry) {
        if (entry.equalsIgnoreCase("--")) return new HashMap<>();
        return Arrays.stream(entry.split(",")).map(arrayStream -> arrayStream.split("=")).collect(Collectors.toMap(entries -> entries[0], entries -> entries[1]));
    }
}
