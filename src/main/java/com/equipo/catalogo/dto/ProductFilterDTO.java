package com.equipo.catalogo.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "categoryName",
        defaultImpl = ProductFilterDTO.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ShirtFilterDTO.class, name = "Shirts"),
        @JsonSubTypes.Type(value = BeverageFilterDTO.class, name = "Beverages"),
        @JsonSubTypes.Type(value = ElectronicFilterDTO.class, name = "Electronics"),
        @JsonSubTypes.Type(value = AccesoryFilterDTO.class, name = "Accesories")
})
public class ProductFilterDTO {
    private String query;

    private String categoryName;

    private Double minPrice;

    private Double maxPrice;

}
