package com.company.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Builder
public class PetRequest {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("category")
    private PetCategoryRequest petCategoryRequest;
    @JsonProperty("name")
    private String name;
    @JsonProperty("photoUrls")
    private List<String> photoUrls;
    @JsonProperty("tags")
    private List<PetTagRequest> petTagRequests;
    @JsonProperty("status")
    private String status;

}

