package com.example.vankoserver.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * ResponseGarageDTO
 */
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class ResponseGarageDTO {

  private Long id;

  private String name;

  private String location;

  private String city;

  private Integer capacity;

}

