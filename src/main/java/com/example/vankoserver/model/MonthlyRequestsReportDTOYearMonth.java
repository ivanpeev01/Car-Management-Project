package com.example.vankoserver.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * MonthlyRequestsReportDTOYearMonth
 */
@lombok.Data
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor

@JsonTypeName("MonthlyRequestsReportDTO_yearMonth")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.8.0")
public class MonthlyRequestsReportDTOYearMonth {

  private Integer year;

  /**
   * Gets or Sets month
   */
  public enum MonthEnum {
    JANUARY("JANUARY"),
    
    FEBRUARY("FEBRUARY"),
    
    MARCH("MARCH"),
    
    APRIL("APRIL"),
    
    MAY("MAY"),
    
    JUNE("JUNE"),
    
    JULY("JULY"),
    
    AUGUST("AUGUST"),
    
    SEPTEMBER("SEPTEMBER"),
    
    OCTOBER("OCTOBER"),
    
    NOVEMBER("NOVEMBER"),
    
    DECEMBER("DECEMBER");

    private String value;

    MonthEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static MonthEnum fromValue(String value) {
      for (MonthEnum b : MonthEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  private MonthEnum month;

  private Boolean leapYear;

  private Integer monthValue;

}

