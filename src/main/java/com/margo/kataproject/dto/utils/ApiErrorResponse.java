package com.margo.kataproject.dto.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * ApiErrorResponse
 */
@Validated
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class ApiErrorResponse {

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("label")
    private String label = null;

    @JsonProperty("detail")
    private String detail = null;

    public ApiErrorResponse id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ApiErrorResponse label(String label) {
        this.label = label;
        return this;
    }

    /**
     * Get label
     *
     * @return label
     **/
    @NotNull
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ApiErrorResponse detail(String detail) {
        this.detail = detail;
        return this;
    }

    /**
     * Get detail
     *
     * @return detail
     **/
    @NotNull
    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApiErrorResponse apiErrorResponse = (ApiErrorResponse) o;
        return Objects.equals(this.id, apiErrorResponse.id) &&
                Objects.equals(this.label, apiErrorResponse.label) &&
                Objects.equals(this.detail, apiErrorResponse.detail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, detail);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ApiErrorResponse {\n");

        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    label: ").append(toIndentedString(label)).append("\n");
        sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

