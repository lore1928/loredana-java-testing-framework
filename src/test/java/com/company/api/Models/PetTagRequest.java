package com.company.api.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PetTagRequest {
    @JsonProperty("id")
    Integer id;
    @JsonProperty("name")
    String name;

}
