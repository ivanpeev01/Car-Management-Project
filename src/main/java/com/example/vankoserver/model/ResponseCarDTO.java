package com.example.vankoserver.model;

import java.net.URI;
import java.util.Objects;
import com.example.vankoserver.model.ResponseGarageDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ResponseCarDTO
 */
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class ResponseCarDTO {

  private Long id;

  private String make;

  private String model;

  private Integer productionYear;

  private String licensePlate;

  @Valid
  private List<@Valid ResponseGarageDTO> garages = new ArrayList<>();

}

